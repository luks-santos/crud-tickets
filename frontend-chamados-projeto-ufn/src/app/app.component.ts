import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs';

@Component({
	selector: 'app-root',
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
	title = 'App Chamados';

	constructor(private router: Router) { }

	ngOnInit(): void {
		this.router.events.pipe(
			filter(event => event instanceof NavigationEnd)
		).subscribe(() => {

			const routeTitle = this.getTitleFromRoute(this.router.routerState, this.router.routerState.root);
			console.log(routeTitle);

			this.title = routeTitle || 'App Chamados';
		});
	}

	private getTitleFromRoute(state: any, parent: any): string | null {
		if (parent && parent.snapshot.data && parent.snapshot.data.title) {
			return parent.snapshot.data.title;
		}

		if (state && parent) {
			return this.getTitleFromRoute(state, state.firstChild(parent));
		}

		return null;
	}

	isActive(route: string): boolean {
		return this.router.isActive(route, true);
	}
}
