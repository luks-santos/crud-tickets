import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { catchError, Observable } from 'rxjs';
import { Ticket } from 'src/app/model/ticket/ticket';
import { TicketDTO } from 'src/app/model/ticket/ticketDTO';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

import { TicketDialogComponent } from '../../components/ticket-dialog/ticket-dialog.component';
import { TicketsService } from '../../service/tickets.service';
import { AuthService } from 'src/app/services/auth.service';

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

	private refresh() {
		const isAdmin = this.authService.hasRole('ADMIN');

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

	private openDialog(): void {
		const dialogRef = this.dialog.open(TicketDialogComponent, {});
		
		dialogRef.afterClosed().subscribe((result) => {
			const ticketDTO = {
				id: null,
				status: 'Aberto',
				priority: result.priority,
				createdAt: null,
				closedAt: null,
				commentId: result.selectComments,
				categoryId: result.selectCategories,
				topicId: result.selectTopics,
				userId: null,
			} as TicketDTO;

			this.service.save(ticketDTO).subscribe(() => {
				this.snackBar.open('Ticket created successfully', 'OK', {
					duration: 2000
				});
				this.refresh();
			});
		});
	}

	onAdd() {
		this.openDialog();
	}

	onEdit(ticket: Ticket) {
		
	}

	onDelete(ticket: Ticket) {
		
	}
}
