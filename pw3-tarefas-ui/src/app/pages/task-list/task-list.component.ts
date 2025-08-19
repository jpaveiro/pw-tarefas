import { Component, OnInit, inject } from '@angular/core';
import { Task } from '../../model/task';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { TaskService } from '../../service/task.service';

@Component({
  selector: 'app-task-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css'],
})
export class TaskListComponent implements OnInit {
  tasks: Task[] = [];
  private router = inject(Router);
  private taskService = inject(TaskService)

  ngOnInit(): void {
    this.taskService
          .findAll()
          .subscribe((t) => {
      this.tasks = t;
    });
  }

  newTask() {
    this.router.navigate(['/tasks/new']);
  }

  deleteTask(id: number | undefined) {
    if (id && confirm('Tem certeza que deseja excluir esta tarefa?')) {
      console.log("Exclusão realizada");
    }
  }

  editTask(id: number | undefined) {
    if (id) this.router.navigate(['/tasks', id]);
  }
}
