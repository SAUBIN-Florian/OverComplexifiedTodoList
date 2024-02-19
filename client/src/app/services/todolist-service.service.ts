import { Injectable } from '@angular/core';
import { Todo, Todolist } from '../interfaces/Todo';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { ResponseEntity } from '../interfaces/ResponseEntity';

@Injectable({
  providedIn: 'root'
})
export class TodolistService {
  private TODOLIST_URI = 'http://localhost:8000/todolists';
  private updatedObservableTodolist = new Subject<void>();

  constructor(private http: HttpClient) {}

  public findAllTodoLists(): Observable<ResponseEntity> {
    const request = this.http.get<ResponseEntity>(this.TODOLIST_URI);
    return request;
  }

  public findOneTodolist(id: String): Observable<ResponseEntity> {
    const request = this.http.get<ResponseEntity>(this.TODOLIST_URI + "/" + id);
    return request;
  }
  
  public saveTodolist(todolist: Todolist): Observable<ResponseEntity> {
    const request = this.http.post<ResponseEntity>(this.TODOLIST_URI, todolist);

    request.subscribe({
      next: (response) => {this.updatedObservableTodolist.next(), console.log(response)},
      error: (err) => {console.log(err)}
    })

    return request;
  }

  public updateTodolist(id: String, todolist: Todolist): Observable<ResponseEntity> {
    const request = this.http.put<ResponseEntity>(this.TODOLIST_URI + "/" + id, todolist);
    return request;
  }

  public deleteTodolist(id: String): Observable<ResponseEntity> {
    const request = this.http.delete<ResponseEntity>(this.TODOLIST_URI + "/" + id);
    return request;
  }

  public getUpdateObservableList() {
    return this.updatedObservableTodolist.asObservable();
  }

  public saveTodo(todolistId: string, todo: Todo): Observable<ResponseEntity> {
    const request = this.http.post<ResponseEntity>(this.TODOLIST_URI + "/" + todolistId + "/todos", todo);
    return request;
  }

  public deleteTodo(todolistId: string, todoId: number): Observable<ResponseEntity> {
    console.log("service todoListId: " + todolistId + ", todoId: " + todoId)
    const request = this.http.delete<ResponseEntity>(this.TODOLIST_URI + "/" + todolistId + "/todos/" + todoId);
    return request;
  }
}
