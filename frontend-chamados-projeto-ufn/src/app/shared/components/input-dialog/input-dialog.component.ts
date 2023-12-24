import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

interface DialogData {
	ref: string;
	nameSelect: string;
	name: string;
	selectedOption?: string;
	options?: any[];
}

@Component({
	selector: 'app-input-dialog',
	templateUrl: './input-dialog.component.html',
	styleUrls: ['./input-dialog.component.scss']
})
export class InputDialogComponent {
	
	constructor(
		public dialogRef: MatDialogRef<InputDialogComponent>,
		@Inject(MAT_DIALOG_DATA) public data: DialogData,
	) { }

	close() {
		this.dialogRef.close();
	}
}
