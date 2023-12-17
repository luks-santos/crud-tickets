import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { catchError, Observable, of } from 'rxjs';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

import { Category } from '../../../model/category';
import { CategoriesService } from '../../service/categories.service';
import { InputDialogComponent } from 'src/app/shared/components/input-dialog/input-dialog.component';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
	selector: 'app-categories',
	templateUrl: './categories.component.html',
	styleUrls: ['./categories.component.scss']
})
export class CategoriesComponent {

	categories$: Observable<Category[]> | null = null;

	constructor(
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
		this.categories$ = this.categoriesService.findAll()
			.pipe(
				catchError(() => {
					this.onError('Error ao carregar categorias.')
					return of([])
				})
			);
	}

	private openDialog(category: Category, isNew: boolean): void {
		const dialogRef = this.dialog.open(InputDialogComponent, {
			data: {
				ref: 'Categorias',
				name: category.name,
				options: []
			},
		});

		dialogRef.afterClosed().subscribe(result => {
			if (result) {			
				category.name = result.name;

				if (isNew) {
					this.categoriesService.create(category).subscribe(() => this.refresh());
				} else {
					this.categoriesService.update(category).subscribe(() => this.refresh());
				}
			}
		});
	}

	onAdd(): void {
		const newCategory: Category = {
			id: '',
			name: '',
		};
		this.openDialog(newCategory, true);
	}

	onEdit(category: Category): void {
		this.openDialog(category, false);
	}

	onDelete(category: Category) {
		const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
			data: "Deseja realmente excluir a categoria?"
		});

		dialogRef.afterClosed().subscribe((result: boolean) => {
			if (result) {
				this.categoriesService.delete(category.id).subscribe(
					{
						next: () => {
							this.snackBar.open("Categoria deletada com Sucesso", 'X', {
								duration: 5000,
								verticalPosition: 'top',
								horizontalPosition: 'center'
							});
						},
						error: () => this.onError("Erro ao tentar remover categoria."),
						complete: () => this.refresh()
					}
				);
			}
		});
	}
}
