import { Component, inject } from '@angular/core';
import { ApiService } from '../api.service';
import { DatePipe } from '@angular/common';
import { RouterLink } from '@angular/router';
import { ApiResponse, Task } from '../model/task.model';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-table',
  standalone: true,
  imports: [DatePipe, RouterLink, FormsModule],
  templateUrl: './table.component.html',
  styles: '',
})
export class TableComponent {
  private apiService = inject(ApiService);
  tasks: Task[] = [];
  page: number = 0;
  size: number = 10;
  total: number = 0;
  filters: { [key: string]: any } = {};

  constructor() {
    this.loadTasks();
  }

  applyFilters(event: Event, filterKey: string): void {
    const target = event.target as HTMLInputElement | HTMLSelectElement;
    const value = target.value;
    this.filters = { ...this.filters, [filterKey]: value || null };
    this.page = 0;
    this.loadTasks();
  }

  resetFilters(): void {
    this.filters = {
      category: '',
      status: '',
      date: '',
      after: '',
      before: '',
    };
    this.page = 0;
    this.loadTasks();
  }

  loadTasks(): void {
    this.apiService
      .getTasks(this.page, this.size, this.filters)
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
