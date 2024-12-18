import { Injectable } from '@angular/core';
import { BaseHttpService } from './base-http.service';
import { ApiResponse, CreateTask, Task, UpdateTask } from './model/task.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ApiService extends BaseHttpService {
  getTasks(
    page: number = 0,
    size: number = 10,
    filters: { [key: string]: any }
  ): Observable<ApiResponse> {
    const params: any = {
      page: page.toString(),
      size: size.toString(),
      ...filters,
    };
    return this.http.get<ApiResponse>(`${this.apiUrl}/tasks`, { params });
  }

  getTask(id: number): Observable<Task> {
    return this.http.get<Task>(`${this.apiUrl}/tasks/${id}`);
  }

  createTask(task: CreateTask): Observable<Task> {
    return this.http.post<Task>(`${this.apiUrl}/tasks`, task);
  }

  updateTask(id: number, task: UpdateTask): Observable<Task> {
    return this.http.put<Task>(`${this.apiUrl}/tasks/${id}`, task);
  }

  deleteTask(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/tasks/${id}`);
  }
}
