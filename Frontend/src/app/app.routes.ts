import { Routes } from '@angular/router';
import { TableComponent } from './table/table.component';
import { DetailComponent } from './detail/detail.component';
import { CreateTaskComponent } from './create-task/create-task.component';

export const routes: Routes = [
  {
    path: '',
    component: TableComponent,
  },
  {
    path: 'tasks/:id',
    component: DetailComponent,
  },
  {
    path: 'create',
    component: CreateTaskComponent,
  },
  {
    path: '**',
    redirectTo: '',
  },
];
