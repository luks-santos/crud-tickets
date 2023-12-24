import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { catchError, Observable } from 'rxjs';
import { Ticket } from 'src/app/model/ticket/ticket';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

import { TicketsService } from '../../service/tickets.service';

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
		private service: TicketsService
	) {
		this.refresh();
	}

	onError(errorMsg: string) {
		this.dialog.open(ErrorDialogComponent, {
			data: errorMsg
		});
	}

	private refresh() {
		this.tickets$ = this.service.findAll()
			.pipe(
				catchError(() => {
					this.onError('Error ao carregar chamados.')
					return []
				})
			);
	}

	private openDialog(): void {
		
	}

	onAdd() {
		
	}

	onEdit(ticket: Ticket) {
		
	}

	onDelete(ticket: Ticket) {
		
	}
}
