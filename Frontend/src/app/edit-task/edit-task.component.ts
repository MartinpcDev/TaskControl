import { Component, inject } from '@angular/core';
import { Task } from '../model/task.model';
import { ApiService } from '../api.service';
import { ActivatedRoute, Router } from '@angular/router';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';

@Component({
  selector: 'app-edit-task',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './edit-task.component.html',
  styles: ``,
})
export class EditTaskComponent {
  task?: Task;
  taskId!: number;
  taskForm: FormGroup;

  private apiService = inject(ApiService);
  private router = inject(ActivatedRoute);
  private homeRouter = inject(Router);
  private form = inject(FormBuilder);

  constructor() {
    this.router.params.subscribe({
      next: (params) => {
        this.apiService.getTask(Number(params['id'])).subscribe({
          next: (data: Task) => {
            this.task = data;
            this.taskId = Number(params['id']);
          },
          error: (error) => {
            console.log(error);
          },
        });
      },
      error: (error) => {
        console.log(error);
      },
    });

    this.taskForm = this.form.group({
      title: [
        this.task?.title,
        [
          Validators.required,
          Validators.minLength(10),
          Validators.maxLength(60),
        ],
      ],
      description: [
        this.task?.description,
        [
          Validators.required,
          Validators.minLength(10),
          Validators.maxLength(300),
        ],
      ],
      status: [this.task?.status, [Validators.required]],
      category: [this.task?.category, [Validators.required]],
      date: [this.task?.date, [Validators.required]],
    });
  }

  hasErrors(controlName: string, errorType: string) {
    return (
      this.taskForm.get(controlName)?.hasError(errorType) &&
      this.taskForm.get(controlName)?.touched
    );
  }

  onSubmit() {
    if (this.taskForm.valid) {
      this.apiService
        .updateTask(this.taskId, this.taskForm.value)
        .subscribe(() => {
          this.homeRouter.navigate(['/home']);
        });
    } else {
      console.log(this.taskForm);
    }
  }
}
