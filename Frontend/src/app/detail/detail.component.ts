import { Component, inject } from '@angular/core';
import { ApiService } from '../api.service';
import { ActivatedRoute } from '@angular/router';
import { DatePipe, UpperCasePipe } from '@angular/common';
import { Task } from '../model/task.model';

@Component({
  selector: 'app-product-detail',
  standalone: true,
  imports: [UpperCasePipe, DatePipe],
  templateUrl: './detail.component.html',
  styleUrl: './detail.component.css',
})
export class DetailComponent {
  task?: Task;

  private apiService = inject(ApiService);
  private router = inject(ActivatedRoute);

  constructor() {
    this.router.params.subscribe({
      next: (params) => {
        this.apiService.getTask(Number(params['id'])).subscribe({
          next: (data: Task) => {
            this.task = data;
          },
          error: (error: any) => {
            console.log(error);
          },
        });
      },
      error: (error: any) => {
        console.log(error);
      },
    });
  }
}
