import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { IsActiveMatchOptions, NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';
import { environment } from 'src/environments/environment';
import { UserRegisterDialogComponent } from '../../containers/user-register-dialog/user-register-dialog.component';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-menu-sidenav',
  templateUrl: './menu-sidenav.component.html',
  styleUrls: ['./menu-sidenav.component.scss']
})
export class MenuSidenavComponent implements OnInit {

	private tokenName = environment.TOKEN_NAME;
  	title = 'App Chamados';

	constructor(
		private router: Router,
		private auth: AuthService,
		private dialog: MatDialog,
		private snackBar: MatSnackBar,
	) { }

	ngOnInit() {
		this.router.events.pipe(filter(event => event instanceof NavigationEnd))
			.subscribe(() => {
				const routeTitle = this.getTitleFromRoute(this.router.routerState, this.router.routerState.root);
				this.title = routeTitle ?? 'App Chamados';
		});
	}

	private getTitleFromRoute(state: any, parent: any): string | null {
		if (parent?.snapshot?.data.title) {
			return parent.snapshot.data.title;
		}

		if (state && parent) {
			return this.getTitleFromRoute(state, state.firstChild(parent));
		}
		return null;
	}

	cadUser() {
		const dialogRef = this.dialog.open(UserRegisterDialogComponent, {});
		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				this.snackBar.open('Usuário criado com sucesso', 'OK', {
					duration: 2000
				});
			}
		});
	}

	isActive(route: string): boolean {
		const options: IsActiveMatchOptions = {
			paths: 'exact',
			queryParams: 'exact',
			fragment: 'ignored',
			matrixParams: 'ignored'
		};
		return this.router.isActive(route, options);
	}

	logout() {
		this.auth.removeKey(this.tokenName);
		this.router.navigate(['/login']);
	}

	hasPermission(role: string): boolean {
		return this.auth.hasRole(role);
	 }
}
