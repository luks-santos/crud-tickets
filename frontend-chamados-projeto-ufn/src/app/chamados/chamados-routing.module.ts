import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CategoriesComponent } from './containers/categories/categories.component';
import { CommentsComponent } from './containers/comments/comments.component';
import { TopicsComponent } from './containers/topics/topics.component';
import { authGuard } from '../guards/auth.guard';
import { MenuSidenavComponent } from './components/menu-sidenav/menu-sidenav.component';


const routes: Routes = [
  {
    path: '',
    canActivateChild: [authGuard],
    component: MenuSidenavComponent,
    children: [
      { path: 'categories', component: CategoriesComponent, data: { title: 'Categorias' } },
      { path: 'comments', component: CommentsComponent, data: { title: 'Comentários' } },
      { path: 'topics', component: TopicsComponent, data: { title: 'Tópicos' } },
    ],
  },
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ChamadosRoutingModule { }
