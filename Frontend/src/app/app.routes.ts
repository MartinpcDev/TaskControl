import { Routes } from '@angular/router';
import { TableComponent } from './table/table.component';
import { DetailComponent } from './detail/detail.component';

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
    path: '**',
    redirectTo: '',
  },
];
