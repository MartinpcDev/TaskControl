export interface ApiResponse {
  tasks: Task[];
  page: number;
  size: number;
  total: number;
}

export interface Task {
  id: number;
  title: string;
  description: string;
  status: 'PENDING' | 'IN_PROGRESS' | 'COMPLETED';
  category:
    | 'PERSONAL'
    | 'TRABAJO'
    | 'EDUCACION'
    | 'HOGAR'
    | 'SALUD'
    | 'OCIO'
    | 'OTRO';
  date: Date;
}

export type UpdateTask = Partial<
  Pick<Task, 'title' | 'description' | 'category' | 'status' | 'date'>
>;

export type CreateTask = Omit<Task, 'id'>;
