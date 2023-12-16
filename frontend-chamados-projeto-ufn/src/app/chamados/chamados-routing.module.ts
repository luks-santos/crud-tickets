import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CategoriesComponent } from './containers/categories/categories.component';
import { CommentsComponent } from './containers/comments/comments.component';
import { TopicsComponent } from './containers/topics/topics.component';


const routes: Routes = [
  { path: 'categories', component: CategoriesComponent },
  { path: 'comments', component: CommentsComponent },
  { path: 'topics', component: TopicsComponent },
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ChamadosRoutingModule { }
