import { Component, Input } from '@angular/core';
import { Todo } from '../interfaces/Todo';
import { TodolistService } from '../services/todolist-service.service';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent {

  @Input()
  public todo!: Todo;
  @Input()
  public todolistId!: string;

  public constructor(private todolistService: TodolistService) {}
  
  handleDelete() {
    this.todolistService.deleteTodo(this.todolistId, this.todo.id).subscribe({
      next: (data) => {console.log(data)},
      error: (err) => {console.log(err)}
    })
  }
}
