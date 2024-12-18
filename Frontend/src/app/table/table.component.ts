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
  styles: '',
})
export class TableComponent {
  private apiService = inject(ApiService);
  tasks: Task[] = [];
  page: number = 0;
  size: number = 10;
  total: number = 0;

  constructor() {
    this.loadTasks();
  }

  loadTasks(): void {
    this.apiService
      .getTasks(this.page, this.size)
      .subscribe((data: ApiResponse) => {
        this.tasks = data.tasks;
        this.page = data.page;
        this.total = data.total;
      });
  }

  nextPage(): void {
    if ((this.page + 1) * this.size < this.total) {
      this.page++;
      this.loadTasks();
    }
  }

  previousPage(): void {
    if (this.page > 0) {
      this.page--;
      this.loadTasks();
    }
  }
}
