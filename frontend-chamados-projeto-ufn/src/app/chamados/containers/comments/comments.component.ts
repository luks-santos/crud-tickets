import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { catchError, Observable, of } from 'rxjs';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { InputDialogComponent } from 'src/app/shared/components/input-dialog/input-dialog.component';

import { Comment } from '../../../model/comment';
import { CommentService } from '../../service/comments.service';

@Component({
	selector: 'app-comments',
	templateUrl: './comments.component.html',
	styleUrls: ['./comments.component.scss']
})
export class CommentsComponent {

	comments$: Observable<Comment[]> | null = null;

	constructor(
		private commentService: CommentService,
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
		this.comments$ = this.commentService.findAll()
			.pipe(
				catchError(() => {
					this.onError('Error ao carregar categorias.')
					return of([])
				})
			);
	}

	private openDialog(comment: Comment, isNew: boolean): void {
		const dialogRef = this.dialog.open(InputDialogComponent, {
			data: {
				ref: 'Categorias',
				name: comment.name,
				options: []
			},
		});

		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				comment.name = result.name;

				if (isNew) {
					this.commentService.create(comment).subscribe(() => this.refresh());
				} else {
					this.commentService.update(comment).subscribe(() => this.refresh());
				}
			}
		});
	}

	onAdd(): void {
		const newComment: Comment = {
			id: '',
			name: '',
		};
		this.openDialog(newComment, true);
	}

	onEdit(comment: Comment): void {
		this.openDialog(comment, false);
	}

	onDelete(comment: Comment) {
		const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
			data: "Deseja realmente excluir a categoria?"
		});

		dialogRef.afterClosed().subscribe((result: boolean) => {
			if (result) {
				this.commentService.delete(comment.id).subscribe(
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
