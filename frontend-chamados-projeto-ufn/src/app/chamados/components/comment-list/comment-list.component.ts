import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Comment } from 'src/app/chamados/model/comment';

@Component({
  selector: 'app-comment-list',
  templateUrl: './comment-list.component.html',
  styleUrls: ['./comment-list.component.scss']
})
export class CommentListComponent {
  
  @Input() comments: Comment[] = [];
  @Output() add = new EventEmitter<Comment>();
  @Output() edit = new EventEmitter<Comment>();
  @Output() delete = new EventEmitter<Comment>();

  readonly displayedColumns = ['name', 'actions'];

  onAdd() {
    this.add.emit();
  }

  onEdit(comment: Comment) {    
    this.edit.emit(comment);
  }

  onDelete(comment: Comment) {
    this.delete.emit(comment);
  }
}
