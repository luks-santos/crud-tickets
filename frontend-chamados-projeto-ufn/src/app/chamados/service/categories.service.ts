import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { Category } from '../../model/category';

@Injectable({
  providedIn: 'root'
})
export class CategoriesService {

  	private readonly API = 'api/categories';
	
  	constructor(private httpClient: HttpClient) { }

  	findAll(): Observable<Category[]> {
   		return this.httpClient.get<Category[]>(this.API);
  	}

	create(category: Category): Observable<Category> {
		return this.httpClient.post<Category>(this.API, category);
	}

	update(category: Category): Observable<Category> {
		return this.httpClient.put<Category>(`${this.API}/${category.id}`, category);
	}

	delete(id: string): Observable<any> {
		return this.httpClient.delete(`${this.API}/${id}`);
	}
}
