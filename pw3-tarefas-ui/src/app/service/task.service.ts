import { Task } from './../model/task';
import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private apiUrl: string = "http://localhost:8080/tarefas";
  private http = inject(HttpClient);

  constructor() {
  }

  public findAll(): Observable<Task[]> {
    return this.http
            .get<Task[]>
            (this.apiUrl);
  }

  public create(task: Task): Observable<Task> {
    return this.http
            .post<Task>
            (this.apiUrl, task);
  }
}
