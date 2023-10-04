import { Injectable } from '@angular/core';
import { Todo, Todolist } from '../interfaces/Todo';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TodolistService {
  private TODOLIST_URI = 'http://localhost:8000/todolists';
  private updatedObservableTodolist = new Subject<void>();

  constructor(private http: HttpClient) {}

  public findAllTodoLists(): Observable<Todolist[]> {
    const request = this.http.get<Todolist[]>(this.TODOLIST_URI);
    return request;
  }

  public findOneTodolist(id: String): Observable<Todolist> {
    const request = this.http.get<Todolist>(this.TODOLIST_URI + "/" + id);
    return request;
  }
  
  public saveTodolist(todolist: Todolist): Observable<boolean> {
    console.log(todolist);
    const request = this.http.post<any>(this.TODOLIST_URI, todolist);
    
    request.subscribe({
      next: (data) => {this.updatedObservableTodolist.next()},
      error: (err) => {console.log(err)}
    });

    return of(true);
  }

  public updateTodolist(id: String, todolist: Todolist): Observable<boolean> {
    const request = this.http.put<any>(this.TODOLIST_URI + "/" + id, todolist);
    return request;
  }

  public deleteTodolist(id: String): Observable<boolean> {
    const request = this.http.delete<any>(this.TODOLIST_URI + "/" + id);
    return request;
  }

  public getUpdateObservableList() {
    return this.updatedObservableTodolist.asObservable();
  }

  public saveTodo(todolistId: string, todo: Todo): Observable<boolean> {
    const request = this.http.post<any>(this.TODOLIST_URI + "/" + todolistId + "/todos", todo);
    return request;
  }

  public deleteTodo(todolistId: string, todoId: number): Observable<boolean> {
    console.log("service todoListId: " + todolistId + ", todoId: " + todoId)
    const request = this.http.delete<any>(this.TODOLIST_URI + "/" + todolistId + "/todos/" + todoId);
    return request;
  }
}
