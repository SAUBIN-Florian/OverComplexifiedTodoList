import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { TodolistService } from '../services/todolist-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-form-todolist',
  templateUrl: './form-todolist.component.html',
  styleUrls: ['./form-todolist.component.css']
})
export class FormTodolistComponent {

  public formTodoList!: FormGroup;

  public constructor(private todolistService: TodolistService, private fb: FormBuilder, private router: Router) {}

  public ngOnInit() {
    this.formTodoList = this.fb.group({
      title: this.fb.control(null)
    })
  }

  public handleForm() {
    this.todolistService.saveTodolist(this.formTodoList.value).subscribe({
      next: (data) => {true},
      error: (err) => {console.log(err)}
    });
    this.router.navigate(["/"]);
  }
}
