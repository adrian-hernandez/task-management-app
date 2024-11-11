// taskApp.js

const app = angular.module("taskApp", []);

app.controller("TaskController", function($scope, $http) {
    $scope.tasks = [];
    $scope.newTask = {};

    // Load tasks from the backend
    $scope.loadTasks = function() {
        $http.get("/tasks").then(response => {
            $scope.tasks = response.data;
        });
    };

    // Add new task
    $scope.addTask = function() {
        $http.post("/tasks", $scope.newTask).then(response => {
            $scope.tasks.push(response.data);
            $scope.newTask = {};
        });
    };

    // Delete task
    $scope.deleteTask = function(task) {
        // Here we are directly removing the task from the UI
        const index = $scope.tasks.indexOf(task);
        if (index > -1) {
            $scope.tasks.splice(index, 1); // Remove task from the array
        }

        // You would want to add a call to delete the task from the backend as well:
        // $http.delete('/tasks/' + task.id);
    };

    // Initialize by loading tasks on page load
    $scope.loadTasks();
});
