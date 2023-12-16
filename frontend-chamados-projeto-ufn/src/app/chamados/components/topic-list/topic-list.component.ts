import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Topic } from '../../model/topic';

@Component({
  selector: 'app-topic-list',
  templateUrl: './topic-list.component.html',
  styleUrls: ['./topic-list.component.scss']
})
export class TopicListComponent {

  @Input() topics: Topic[] = [];
  @Output() add = new EventEmitter<Topic>();
  @Output() edit = new EventEmitter<Topic>();
  @Output() delete = new EventEmitter<Topic>();

  readonly displayedColumns = ['name', 'category', 'actions'];

  onAdd() {
    this.add.emit();
  }

  onEdit(topic: Topic) {    
    this.edit.emit(topic);
  }

  onDelete(topic: Topic) {
    this.delete.emit(topic);
  }
}
