import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { catchError, Observable } from 'rxjs';
import { Ticket } from 'src/app/model/ticket/ticket';
import { TicketDTO } from 'src/app/model/ticket/ticketDTO';
import { AuthService } from 'src/app/services/auth.service';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

import { TicketsService } from '../../../service/tickets.service';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';
import { TicketRegisterDialogComponent } from '../ticket-register-dialog/ticket-register-dialog.component';
import { TicketEditDialogComponent } from '../ticket-edit-dialog/ticket-edit-dialog.component';
import { tick } from '@angular/core/testing';

@Component({
	selector: 'app-tickets',
	templateUrl: './tickets.component.html',
	styleUrls: ['./tickets.component.scss']
})
export class TicketsComponent {

	tickets$: Observable<Ticket[]> | null = null;

	constructor(
		private dialog: MatDialog,
		private snackBar: MatSnackBar,
		private service: TicketsService,
		private authService: AuthService
	) {
		this.refresh();
	}

	onError(errorMsg: string) {
		this.dialog.open(ErrorDialogComponent, {
			data: errorMsg
		});
	}

	private refresh(status?: string) {
		const isAdmin = this.authService.hasRole('ADMIN');
		if (!status) {
			this.tickets$ = isAdmin ?
				this.service.findAll() :
				this.service.findByUser()
					.pipe(
						catchError((error) => {
							this.onError(error);
							return [];
						})
					);
		} 
		else {
			this.tickets$ = isAdmin ?
				this.service.findAll(status) :
				this.service.findByUser(status)
					.pipe(
						catchError((error) => {
							this.onError(error);
							return [];
						})
					);
		}
	}

	onAdd() {
		const dialogRef = this.dialog.open(TicketRegisterDialogComponent, {});
		
		dialogRef.afterClosed().subscribe((result) => {
			const ticketDTO = {
				status: 'Pendente',
				priority: result.priority,
				commentId: result.selectComments,
				categoryId: result.selectCategories,
				topicId: result.selectTopics,
			} as TicketDTO;

			this.service.save(ticketDTO).subscribe(() => {
				this.snackBar.open('Chamado criado com sucesso', 'OK', {
					duration: 2000
				});
				this.refresh();
			});
		});
	}

	onEdit(ticket: Ticket) {
		console.log(ticket);
		
		const data = {
			description: ticket.description,
			selectedComment: ticket.comment.name,
			selectedCategory: ticket.topic.category.name,
			selectedTopic: ticket.topic.name,
			status: ticket.status,
			priority: ticket.priority,
			createdAt: ticket.createdAt,
			closedAt: ticket.closedAt,
			name: ticket.personName,
			cellPhone: ticket.cellPhone,
		}
		
		const dialogRef = this.dialog.open(TicketEditDialogComponent, {
			data: data
		});

		dialogRef.afterClosed().subscribe((result) => {
			if (result) {
				ticket.status = result.status;
				const ticketDTO = {
					id: ticket.id,
					status: ticket.status,
					priority: ticket.priority,
					createdAt: ticket.createdAt,
					closedAt: ticket.closedAt,
					commentId: ticket.comment.id,
					categoryId: ticket.topic.category.id,
					topicId: ticket.topic.id
				} as TicketDTO;

				this.service.edit(ticketDTO).subscribe(() => {
					this.snackBar.open('Chamado atualizado com sucesso', 'OK', {
						duration: 2000
					});
					this.refresh();
				});	
			}	
		});
	}

	onDelete(ticket: Ticket) {
		const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
			data: "Deseja realmente excluir esse chamado?"
		});
		
		dialogRef.afterClosed().subscribe((result: boolean) => {
			if (result) {
				this.service.delete(ticket.id).subscribe(() => {
					this.snackBar.open('Ticket deleted successfully', 'OK', {
						duration: 2000
					});
					this.refresh();
				});
			}
		});
	}

	onFilter(status: string) {
		this.refresh(status);
	}
}
