import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { User } from '../model/user';
import { Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Router } from '@angular/router';

@Injectable({
	providedIn: 'root'
})
export class AuthService {
 

	private readonly API = 'api/auth';
	private storage: Storage = localStorage;
	private tokenName = environment.TOKEN_NAME;

	constructor(private httpClient: HttpClient, private router: Router) { }

	login(user: User): any {
		return this.httpClient.post<User>(`${this.API}/login`, user);
	}

	isAuthenticated() {
		const token = localStorage.getItem(this.tokenName);
		if (token) {
			return true;
		} else {
			this.router.navigate(['/login'])
			return false;
		}
	}

	public saveKey(key: string, value: string) {
		this.storage.setItem(key, value);
	}

	public getKey(key: string): string | null {
		return this.storage.getItem(key);
	}
	
	public removeKey(key: string) {
		this.storage.removeItem(key);
	}
	
	public removeAllKeys() {
		this.storage.clear();
	}
}
