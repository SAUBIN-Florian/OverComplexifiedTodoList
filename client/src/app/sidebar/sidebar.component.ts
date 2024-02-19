import { Component } from '@angular/core';
import { Todolist } from '../interfaces/Todo';
import { TodolistService } from '../services/todolist-service.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {
  
  public todolists: Todolist[] = [];

  public constructor(private todolistService: TodolistService) {}

  public ngOnInit() {
    this.todolistService.getUpdateObservableList().subscribe(() => {
      this.refreshEntries();
    })

    this.refreshEntries();
  }

  public refreshEntries() {
    this.todolistService.findAllTodoLists().subscribe({
      next: (response) => {this.todolists = response.data.value},
      error: (err) => {console.log(err)}
    });
  }

}
