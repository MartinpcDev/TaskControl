import { Component, inject } from '@angular/core';
import { ApiService } from '../api.service';
import { DatePipe } from '@angular/common';
import { RouterLink } from '@angular/router';
import { ApiResponse, Task } from '../model/task.model';

@Component({
  selector: 'app-table',
  standalone: true,
  imports: [DatePipe, RouterLink],
  templateUrl: './table.component.html',
  styleUrl: './table.component.css',
})
export class TableComponent {
  private apiService = inject(ApiService);
  tasks: Task[] = [];

  constructor() {
    this.apiService.getTasks().subscribe((data: ApiResponse) => {
      this.tasks = data.tasks;
    });
  }
}
