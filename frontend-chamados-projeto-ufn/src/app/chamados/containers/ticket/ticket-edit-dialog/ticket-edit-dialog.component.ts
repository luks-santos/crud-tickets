import { DatePipe } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

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
    public datePipe: DatePipe
	) { }
	
	ngOnInit() {
		this.form = this.fb.group({
			description: [this.data.description],
			selectedComment: [this.data.selectedComment],
			selectedCategory: [this.data.selectedCategory],
			selectedTopic: [this.data.selectedTopic],
			priority: [this.data.priority],
			createdAt : [this.formatDate(this.data.createdAt)],
			closedAt : [this.formatDate(this.data.closedAt)],
			status : [this.data.status]
		});
    this.form.get('description')?.disable();
    this.form.get('selectedComment')?.disable();
    this.form.get('selectedCategory')?.disable();
    this.form.get('selectedTopic')?.disable();
    this.form.get('priority')?.disable();
    this.form.get('createdAt')?.disable();
    this.form.get('closedAt')?.disable();
	}

  private formatDate(date: any): string {
    if (date) {
      return this.datePipe.transform(date, 'dd/MM/yyyy HH:mm:ss') || '';
    }

    return '';
  }
  
	close() {
		this.dialogRef.close();
	}

	save() {
		this.dialogRef.close(this.form.value);
	}
}

