import { CommonModule, DatePipe } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { AppMaterialModule } from '../shared/app-material/app-material.module';
import { SharedModule } from '../shared/shared.module';
import { ChamadosRoutingModule } from './chamados-routing.module';
import { CategoryListComponent } from './components/category-list/category-list.component';
import { CommentListComponent } from './components/comment-list/comment-list.component';
import { MenuSidenavComponent } from './components/menu-sidenav/menu-sidenav.component';
import { TicketListComponent } from './components/ticket-list/ticket-list.component';
import { TopicListComponent } from './components/topic-list/topic-list.component';
import { CategoriesComponent } from './containers/categories/categories.component';
import { CommentsComponent } from './containers/comments/comments.component';
import { TicketsComponent } from './containers/ticket/tickets/tickets.component';
import { TopicsComponent } from './containers/topics/topics.component';
import { UserRegisterDialogComponent } from './containers/user-register-dialog/user-register-dialog.component';
import { TicketRegisterDialogComponent } from './containers/ticket/ticket-register-dialog/ticket-register-dialog.component';
import { TicketEditDialogComponent } from './containers/ticket/ticket-edit-dialog/ticket-edit-dialog.component';

@NgModule({
  declarations: [
    CategoriesComponent,
    CategoryListComponent,
    CommentListComponent,
    CommentsComponent,
    TopicListComponent,
    TopicsComponent,
    TicketListComponent,
    MenuSidenavComponent,
    TicketsComponent,
    TicketRegisterDialogComponent,
    UserRegisterDialogComponent,
    TicketEditDialogComponent,
  ],
  imports: [
    CommonModule,
    ChamadosRoutingModule,
    SharedModule,
    AppMaterialModule,
    ReactiveFormsModule
  ],
  providers: [DatePipe]
})
export class ChamadosModule { }
