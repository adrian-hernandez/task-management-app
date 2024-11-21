import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TaskService } from './services/task.service';
import { Task } from './models/task';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  imports: [RouterOutlet, CommonModule, FormsModule],
  standalone: true
})
export class AppComponent implements OnInit {
  newTask: Task = {
    title: '',
    description: '',
    completed: false
  };
  
  tasks: Task[] = [];

  constructor(private taskService: TaskService) {}

  ngOnInit() {
    this.loadTasks();
  }

  loadTasks() {
    this.taskService.getTasks().subscribe(tasks => {
      this.tasks = tasks;
    });
  }

  addTask() {
    this.taskService.createTask(this.newTask).subscribe(task => {
      this.tasks.push(task);
      this.newTask = {
        title: '',
        description: '',
        completed: false
      };
    });
  }

  toggleComplete(task: Task) {
    task.completed = !task.completed;
    this.taskService.updateTask(task).subscribe();
  }

  deleteTask(task: Task) {
    if (task.id) {
      this.taskService.deleteTask(task.id).subscribe(() => {
        this.tasks = this.tasks.filter(t => t.id !== task.id);
      });
    }
  }
}
