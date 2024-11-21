import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  imports: [RouterOutlet, CommonModule, FormsModule],
  standalone: true
})
export class AppComponent {
  newTask = {
    title: '',
    description: '',
    completed: false
  };
  
  tasks: any[] = [];

  addTask() {
    this.tasks.push({...this.newTask});
    this.newTask.title = '';
    this.newTask.description = '';
  }

  toggleComplete(task: any) {
    task.completed = !task.completed;
  }
}
