import { DatePipe } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { AuthService } from 'src/app/services/auth.service';

@Component({
	selector: 'app-ticket-edit-dialog',
	templateUrl: './ticket-edit-dialog.component.html',
	styleUrls: ['./ticket-edit-dialog.component.scss']
})
export class TicketEditDialogComponent implements OnInit {

	form!: FormGroup;

	constructor(
		public dialogRef: MatDialogRef<TicketEditDialogComponent>,
		@Inject(MAT_DIALOG_DATA) public data: any,
		private fb: FormBuilder,
		private datePipe: DatePipe,
		public authService: AuthService
	) { }

	ngOnInit() {
		this.form = this.fb.group({
			description: [this.data.description],
			selectedComment: [this.data.selectedComment],
			selectedCategory: [this.data.selectedCategory],
			selectedTopic: [this.data.selectedTopic],
			priority: [this.data.priority],
			createdAt: [this.formatDate(this.data.createdAt)],
			closedAt: [this.formatDate(this.data.closedAt)],
			status: [this.data.status],
			name: [this.data.name],
			cellphone: [this.data.cellPhone],
		});
		Object.keys(this.form.controls).forEach(controlName => {
			const control = this.form.get(controlName);
			if (control) {
				control.disable();
			}
		});
		if (this.authService.hasRole('ADMIN') && this.data.status !== 'Cancelado' && this.data.status !== 'Concluído') {
			const statusControl = this.form.get('status');
			if (statusControl) {
				statusControl.enable();
			}
		}
	}

  private formatDate(date: any): string {
		if (date) {
			return this.datePipe.transform(date, 'dd/MM/yyyy HH:mm:ss') ?? '';
		}

		return '';
	}

	close() {
		this.dialogRef.close(false);
	}

	save() {
		this.dialogRef.close(this.form.value);
	}
}

