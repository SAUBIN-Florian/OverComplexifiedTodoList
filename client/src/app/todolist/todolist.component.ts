import { Component } from '@angular/core';
import { Todo, Todolist } from '../interfaces/Todo';
import { TodolistService } from '../services/todolist-service.service';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-todolist',
  templateUrl: './todolist.component.html',
  styleUrls: ['./todolist.component.css']
})
export class TodolistComponent {

  public id!: string | null;
  public todolist!: Todolist;
  public todos: Todo[] = [];
  public todoForm!: FormGroup;

  public constructor(private todolistService: TodolistService, private fb: FormBuilder, private activeRoute: ActivatedRoute) {}

  public ngOnInit() {
    this.activeRoute.paramMap.subscribe((paramMap) => {
      this.id = paramMap.get('id');
  
      if(this.id !== null) {
        this.todolistService.findOneTodolist(this.id).subscribe({
          next: (response) => {this.todolist = response.data.value; this.todos = response.data.value.todos},
          error: (err) => {console.log(err)},
        })
      }
    })

    this.todoForm = this.fb.group({
      task: this.fb.control(null),
      done: this.fb.control(false)
    })
  }

  public onClick() {}

  public handleSubmit() {
    if(this.id !== null) {
      this.todolistService.saveTodo(this.id, this.todoForm.value).subscribe({
        next: (response) => {console.log(response); this.todos.push(this.todoForm.value)},
        error: (err) => {console.log(err)}
      });
    }
  }
}
