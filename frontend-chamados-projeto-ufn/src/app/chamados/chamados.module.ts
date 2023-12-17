import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { AppMaterialModule } from '../shared/app-material/app-material.module';
import { SharedModule } from '../shared/shared.module';
import { ChamadosRoutingModule } from './chamados-routing.module';
import { CategoryListComponent } from './components/category-list/category-list.component';
import { CategoriesComponent } from './containers/categories/categories.component';
import { CommentListComponent } from './components/comment-list/comment-list.component';
import { CommentsComponent } from './containers/comments/comments.component';
import { TopicListComponent } from './components/topic-list/topic-list.component';
import { TopicsComponent } from './containers/topics/topics.component';


@NgModule({
  declarations: [
    CategoriesComponent,
    CategoryListComponent,
    CommentListComponent,
    CommentsComponent,
    TopicListComponent,
    TopicsComponent
  ],
  imports: [
    CommonModule,
    ChamadosRoutingModule,
    SharedModule,
    AppMaterialModule
  ]
})
export class ChamadosModule { }
