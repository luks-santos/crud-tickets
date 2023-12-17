import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Topic } from '../../model/topic';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TopicService {

  private readonly API = 'api/topics';

  constructor(private httpClient: HttpClient) { }

  findAll(): Observable<Topic[]> {
    return this.httpClient.get<Topic[]>(this.API);
  }

  create(topic: Topic): Observable<Topic> {
    return this.httpClient.post<Topic>(this.API, topic);
  }

  update(topic: Topic): Observable<Topic> {
    return this.httpClient.put<Topic>(`${this.API}/${topic.id}`, topic);
  }

  delete(id: string): Observable<any> {
    return this.httpClient.delete(`${this.API}/${id}`);
  }
}
