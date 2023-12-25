import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

interface DialogData {
	title: string;
	inputName: string;
	selectTitle?: string;
	selectedOption?: string;
	options?: any[];
}

@Component({
	selector: 'app-input-dialog',
	templateUrl: './input-dialog.component.html',
	styleUrls: ['./input-dialog.component.scss']
})
export class InputDialogComponent implements OnInit {
	
	form!: FormGroup;

	constructor(
		public dialogRef: MatDialogRef<InputDialogComponent>,
		@Inject(MAT_DIALOG_DATA) public data: DialogData,
		private fb: FormBuilder
	) { }
	
	ngOnInit() {
		if (this.data.options) {
			this.form = this.fb.group({
				inputName: [this.data.inputName, Validators.required],
				selectedOption: [this.data.selectedOption, Validators.required],
			 });
		} 
		else {
			this.form = this.fb.group({
				inputName: [this.data.inputName, Validators.required],
			 });
		}
	 }

	close() {
		this.dialogRef.close();
	}

	onSubmit() {
		if (this.form.valid) {
		  this.dialogRef.close({ data: this.form.value });
		} 
		else {
		  this.form.markAllAsTouched();		
		}
	 }
}
