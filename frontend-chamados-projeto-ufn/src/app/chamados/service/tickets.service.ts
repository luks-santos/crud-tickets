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

  findAll(status = 'Todos'): Observable<Ticket[]> {
    const params = { status: status };

    return this.httpClient.get<Ticket[]>(this.API, { params: params});
  }

  findByUser(status = 'Todos'): Observable<Ticket[]> {
    const params = { status: status };

    return this.httpClient.get<Ticket[]>(`${this.API}/user`, { params: params});
  }
  
  save(ticketDTO: TicketDTO): Observable<TicketDTO> {
    return this.httpClient.post<TicketDTO>(this.API, ticketDTO);
  }

  delete(id: string): Observable<any> {
    return this.httpClient.delete(`${this.API}/${id}`);
  }
}
