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

	public saveKey(key: string, value: string, role: string) {
		const data = { value, role }; 
		console.log(data);
		
		const serializedData = JSON.stringify(data); 
		this.storage.setItem(key, serializedData); 
	}

	hasRole(expectedRole: string): boolean {
		const token = this.getKey(this.tokenName);
  
		if (token) {
		  return token && token.role === expectedRole;
		}
  		return false;
	 }

	public getKey(key: string) {
		const serializedData = this.storage.getItem(key);
  
		if (serializedData) {
			const data = JSON.parse(serializedData); 
			return data;
		}

  		return null;
	}
	
	public removeKey(key: string) {
		this.storage.removeItem(key);
	}
	
	public removeAllKeys() {
		this.storage.clear();
	}
}
