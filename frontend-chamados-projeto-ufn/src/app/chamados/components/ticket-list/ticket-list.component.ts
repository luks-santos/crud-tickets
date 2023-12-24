import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Ticket } from 'src/app/model/ticket/ticket';

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

  readonly displayedColumns = ["createdAt", "personName", "comment", "category", "topic", "status", "priority", "actions"];

  onAdd() {
    this.add.emit();
  }

  onEdit(ticket: Ticket) {    
    this.edit.emit(ticket);
  }

  onDelete(ticket: Ticket) {
    this.delete.emit(ticket);
  }
}
