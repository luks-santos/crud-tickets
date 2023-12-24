import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user';
import { AuthService } from 'src/app/services/auth.service';
import { environment } from 'src/environments/environment';

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

	passIncorrect: boolean = false;

	form: FormGroup = new FormGroup({
		login: new FormControl('', [Validators.required]),
		password: new FormControl('', [Validators.required])
	});

	constructor(
		private authService: AuthService,
		private router: Router,
	) { }

	ngOnInit() {
		this.checkAuthentication();
	}

	login() {
		if (!this.form.invalid) {
			const user: User = {
				login: this.form.value.login,
				password: this.form.value.password,
			};

			this.authService.login(user).subscribe(
				(data: any) => {
					this.authService.saveKey(environment.TOKEN_NAME, data.token);
					this.checkAuthentication();
				},
				() => {
					this.passIncorrect = true;
				}
			);
		}
	}

	private checkAuthentication() {
		const authenticated = this.authService.isAuthenticated();
		
		if (authenticated) {
			this.router.navigate(['chamados/categories']);
		}
	}
}
