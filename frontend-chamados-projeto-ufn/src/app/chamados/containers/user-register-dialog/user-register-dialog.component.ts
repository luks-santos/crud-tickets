import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { InputDialogComponent } from 'src/app/shared/components/input-dialog/input-dialog.component';
import { UserService } from '../../service/user.service';

@Component({
	selector: 'app-user-register-dialog',
	templateUrl: './user-register-dialog.component.html',
	styleUrls: ['./user-register-dialog.component.scss']
})
export class UserRegisterDialogComponent implements OnInit {

	form!: FormGroup;
	userExist: boolean = false;

	constructor(
		public dialogRef: MatDialogRef<InputDialogComponent>,
		@Inject(MAT_DIALOG_DATA) public data: any,
		private fb: FormBuilder,
		private service: UserService,
	) { }

	ngOnInit() {
		this.form = this.fb.group({
			login: [null, Validators.required],
			password: [null, Validators.required],
			role: [null, Validators.required],
			name: [null, Validators.required],
			cellphone: [null, Validators.required],
		});
	}

	close() {
		this.dialogRef.close();
	}

	save() {
		if (this.form.valid) {
			this.service.create(this.form.value).subscribe({
				next: () => {
					this.dialogRef.close(true);
				},
				error: (error: any) => {
					if (error.status == 422) {
						this.form.get('login')?.setErrors({ usuarioExistente: true });
					}
				}
			});
		} 
		else {
			this.form.markAllAsTouched();
		}
	}
}
