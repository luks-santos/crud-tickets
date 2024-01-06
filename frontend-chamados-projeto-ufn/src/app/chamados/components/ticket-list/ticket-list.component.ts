import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Ticket } from 'src/app/model/ticket/ticket';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-ticket-list',
  templateUrl: './ticket-list.component.html',
  styleUrls: ['./ticket-list.component.scss']
})
export class TicketListComponent {

  @Input() tickets: Ticket[] = [];
  @Output() add = new EventEmitter<Ticket>();
  @Output() edit = new EventEmitter<Ticket>();
  @Output() delete = new EventEmitter<Ticket>();
  @Output() filter = new EventEmitter<string>();

  readonly displayedColumns = ["createdAt", "personName", "comment", "category", "topic", "status", "priority", "actions"];
  readonly statusOptions: string[] = ['Em andamento', 'Concluído', 'Cancelado', 'Pendente'];
  statusFilter: string = '';

  constructor(
    public authService: AuthService
  ) { }

  onAdd() {
    this.add.emit();
  }

  onEdit(ticket: Ticket) {
    this.edit.emit(ticket);
  }

  onDelete(ticket: Ticket) {
    this.delete.emit(ticket);
  }

  aplicarFiltro() {
    this.filter.emit(this.statusFilter);
  }
}
