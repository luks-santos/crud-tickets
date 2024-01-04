import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ticket } from 'src/app/model/ticket/ticket';
import { TicketDTO } from 'src/app/model/ticket/ticketDTO';


@Injectable({
  providedIn: 'root'
})
export class TicketsService {

  private readonly API = 'api/tickets';

  constructor(private httpClient: HttpClient) { }

  findAll(): Observable<Ticket[]> {
    return this.httpClient.get<Ticket[]>(this.API);
  }

  save(ticketDTO: TicketDTO): Observable<TicketDTO> {
    return this.httpClient.post<TicketDTO>(this.API, ticketDTO);
  }
}
