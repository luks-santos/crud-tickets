import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Category } from 'src/app/chamados/model/category';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.scss']
})
export class CategoryListComponent {

  @Input() categories: Category[] = [];
  @Output() add = new EventEmitter<Category>();
  @Output() edit = new EventEmitter<Category>();
  @Output() delete = new EventEmitter<Category>();

  readonly displayedColumns = ['name', 'actions'];

  constructor() {}

  onAdd() {
    this.add.emit();
  }

  onEdit(category: Category) {    
    this.edit.emit(category);
  }

  onDelete(category: Category) {
    this.delete.emit(category);
  }
}
