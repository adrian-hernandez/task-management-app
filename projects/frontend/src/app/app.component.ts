import { Component } from '@angular/core';
import { TaskService } from './services/task.service';
import { Task } from './models/task';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  tasks: Task[] = [];
  newTask: Task = { id: '', title: '', description: '' };

  constructor(private taskService: TaskService) {}

  ngOnInit() {
    this.taskService.getTasks().subscribe((tasks: Task[]) => {
      this.tasks = tasks;
    });
  }

  addTask() {
    this.taskService.addTask(this.newTask).subscribe((task: Task) => {
      this.tasks.push(task);
      this.newTask = { id: '', title: '', description: '' };
    });
  }
} 