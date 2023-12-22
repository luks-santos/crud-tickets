import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { catchError, Observable, of, switchMap } from 'rxjs';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { InputDialogComponent } from 'src/app/shared/components/input-dialog/input-dialog.component';

import { Category } from '../../../model/category';
import { Topic } from '../../../model/topic';
import { CategoriesService } from '../../service/categories.service';
import { TopicService } from '../../service/topic.service';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';

@Component({
	selector: 'app-topics',
	templateUrl: './topics.component.html',
	styleUrls: ['./topics.component.scss']
})
export class TopicsComponent {

	topics$: Observable<Topic[]> | null = null;
	categories: Category[] = [];

	constructor(
		private topicService: TopicService,
		private categoriesService: CategoriesService,
		private dialog: MatDialog,
		private snackBar: MatSnackBar,
	) {
		this.refresh();
	}

	onError(errorMsg: string) {
		this.dialog.open(ErrorDialogComponent, {
			data: errorMsg
		});
	}

	private refresh() {
		this.topics$ = this.topicService.findAll()
			.pipe(
				catchError(() => {
					this.onError('Error ao carregar categorias.')
					return of([])
				})
			);
	}

	private openDialog(topic: Topic, isNew: boolean): void {
		this.categoriesService.findAll()
			.pipe(
				switchMap(categories => {
					
					this.categories = categories;
					
					const dialogRef = this.dialog.open(InputDialogComponent, {
						data: {
							ref: 'Assunto',
							name: topic.name,
							selectedOption: topic.categoryId,
							options: this.categories,
						},
					});

					return dialogRef.afterClosed();
				})
			)
			.subscribe(result => {
				if (result) {
					topic.name = result.name;
					topic.categoryId = result.selectedOption;
					
					if (isNew) {
						this.topicService.create(topic).subscribe(() => this.refresh());
					} else {
						this.topicService.update(topic).subscribe(() => this.refresh());
					}
				}
			});
	}

	onAdd() {
		const newTopic: Topic = {
			id: '',
			name: '',
			categoryId: ''
		};
		this.openDialog(newTopic, true);
	}

	onEdit(topic: any) { 
		const newTopic: Topic = {
			id: topic.id,
			name: topic.name,
			categoryId: topic.category.id
		};
		this.openDialog(newTopic, false);
	}

	onDelete(topic: Topic) { 
		const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
			data: "Deseja realmente excluir o assunto?"
		});

		dialogRef.afterClosed().subscribe((result: boolean) => {
			if (result) {
				this.topicService.delete(topic.id).subscribe(
					{
						next: () => {
							this.snackBar.open("Categoria deletada com Sucesso", 'X', {
								duration: 5000,
								verticalPosition: 'top',
								horizontalPosition: 'center'
							});
						},
						error: () => this.onError("Erro ao tentar remover assunto."),
						complete: () => this.refresh()
					}
				);
			}
		});
	}
}
