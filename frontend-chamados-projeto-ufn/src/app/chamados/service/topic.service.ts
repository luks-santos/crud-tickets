import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Topic } from 'src/app/model/topic/topic';

import { TopicDTO } from '../../model/topic/topicDTO';

@Injectable({
  providedIn: 'root'
})
export class TopicService {

  private readonly API = 'api/topics';

  constructor(private httpClient: HttpClient) { }

  findAll(): Observable<Topic[]> {
    return this.httpClient.get<Topic[]>(this.API);
  }

  create(topic: TopicDTO): Observable<TopicDTO> {
    return this.httpClient.post<TopicDTO>(this.API, topic);
  }

  update(topic: TopicDTO): Observable<TopicDTO> {
    return this.httpClient.put<TopicDTO>(`${this.API}/${topic.id}`, topic);
  }

  delete(id: string): Observable<any> {
    return this.httpClient.delete(`${this.API}/${id}`);
  }

  findTopicsByCategoryId(categoryId: number): Observable<Topic[]> {
    return this.httpClient.get<Topic[]>(`${this.API}/category/${categoryId}`);
  }
}
