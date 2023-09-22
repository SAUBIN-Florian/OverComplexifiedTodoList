import { Component } from '@angular/core';
import { Todolist } from '../interfaces/Todo';
import { TodolistService } from '../services/todolist-service.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-todolist',
  templateUrl: './todolist.component.html',
  styleUrls: ['./todolist.component.css']
})
export class TodolistComponent {

  public todolist!: Todolist;

  public constructor(private todolistService: TodolistService, private activeRoute: ActivatedRoute) {}

  public ngOnInit() {

    // const id = this.activeRoute.snapshot.paramMap.get('id');
  
    // id && this.todolistService.findOneTodolist(id).subscribe({
    //   next: (data) => {this.todolist = data; console.log(data)},
    //   error: (err) => {console.log(err)},
    // })
  }
}
