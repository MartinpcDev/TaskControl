import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-create-task',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './create-task.component.html',
  styles: ``,
})
export class CreateTaskComponent {
  taskForm: FormGroup;

  constructor(
    private form: FormBuilder,
    private router: Router,
    private apiservice: ApiService
  ) {
    this.taskForm = this.form.group({
      title: [
        '',
        [
          Validators.required,
          Validators.minLength(10),
          Validators.maxLength(60),
        ],
      ],
      description: [
        '',
        [
          Validators.required,
          Validators.minLength(10),
          Validators.maxLength(300),
        ],
      ],
      status: ['', [Validators.required]],
      category: ['', [Validators.required]],
      date: ['', [Validators.required]],
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
      this.apiservice.createTask(this.taskForm.value).subscribe(() => {
        this.router.navigate(['/home']);
      });
    } else {
      console.log(this.taskForm);
    }
  }
}
