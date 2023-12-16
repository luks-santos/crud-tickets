import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Comment } from '../model/comment';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  	private readonly API = 'api/comments';

  	constructor(private httpClient: HttpClient) { }

  	findAll(): Observable<Comment[]> {
   		return this.httpClient.get<Comment[]>(this.API);
  	}

	create(comment: Comment): Observable<Comment> {
		return this.httpClient.post<Comment>(this.API, comment);
	}

	update(comment: Comment): Observable<Comment> {
		return this.httpClient.put<Comment>(`${this.API}/${comment.id}`, comment);
	}

	delete(id: string): Observable<any> {
		return this.httpClient.delete(`${this.API}/${id}`);
	}
}
