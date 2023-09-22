import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TodolistComponent } from './todolist/todolist.component';
import { FormTodolistComponent } from './form-todolist/form-todolist.component';

const routes: Routes = [
  // {path: "todolist/:id", component: TodolistComponent},
  {path: "todolist/new", component: FormTodolistComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
