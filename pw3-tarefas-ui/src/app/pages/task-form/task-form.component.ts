import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Task } from '../../model/task';
import { TaskService } from '../../service/task.service';

@Component({
  selector: 'app-task-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './task-form.component.html',
  styleUrls: ['./task-form.component.css'],
})
export class TaskFormComponent {

  private fb = inject(FormBuilder);
  private taskService = inject(TaskService);

  id: string | null = null;

  taskForm = this.fb.group({
    id: [null as number | null],
    titulo: ['', Validators.required],
    descricao: [''],
    responsavel: ['', Validators.required],
    dataLimite: ['', Validators.required],
    status: ['PENDING', Validators.required],
  });

  onSubmit(): void {
    if (this.taskForm.invalid) return;

    const task: Task = this.taskForm.value as Task;
    console.log(task.dataLimite);
    this.taskService
        .create(task)
        .subscribe(
          response => {
            alert("Tarefa cadastrada com sucesso!")
            this.taskForm.reset();
          },
          error => {
            alert("Erro ao cadastrar tarefa.")
            console.log("Erro ao cadastrar tarefa:", error)
          }
        );
  }
}
