import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ticket } from 'src/app/model/ticket/ticket';


@Injectable({
  providedIn: 'root'
})
export class TicketsService {

  private readonly API = 'api/tickets';

  constructor(private httpClient: HttpClient) { }

  findAll(): Observable<Ticket[]> {
    return this.httpClient.get<Ticket[]>(this.API);
  }
}
