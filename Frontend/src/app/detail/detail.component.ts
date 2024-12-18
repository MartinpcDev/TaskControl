import { Component, inject } from '@angular/core';
import { ApiService } from '../api.service';
import { ActivatedRoute, Router } from '@angular/router';
import { DatePipe, UpperCasePipe } from '@angular/common';
import { Task } from '../model/task.model';

@Component({
  selector: 'app-product-detail',
  standalone: true,
  imports: [UpperCasePipe, DatePipe],
  templateUrl: './detail.component.html',
  styles: '',
})
export class DetailComponent {
  task?: Task;
  taskId!: number;

  private apiService = inject(ApiService);
  private router = inject(ActivatedRoute);
  private homeRouter = inject(Router);

  constructor() {
    this.router.params.subscribe({
      next: (params) => {
        this.apiService.getTask(Number(params['id'])).subscribe({
          next: (data: Task) => {
            this.task = data;
            this.taskId = Number(params['id']);
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

  onSubmit() {
    this.apiService.deleteTask(this.taskId).subscribe(() => {
      this.homeRouter.navigate(['/home']);
    });
  }
}
