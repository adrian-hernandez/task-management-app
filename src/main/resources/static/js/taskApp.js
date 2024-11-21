var app = angular.module("taskApp", []);
app.controller("TaskController", ["$scope", "$http", function ($scope, $http) {
        $scope.tasks = [];
        $scope.newTask = {};
        // Load tasks from the backend
        $scope.loadTasks = function () {
            $http.get("/tasks").then(function (response) {
                $scope.tasks = response.data;
            });
        };
        // Add new task
        $scope.addTask = function () {
            $http.post("/tasks", $scope.newTask).then(function (response) {
                $scope.tasks.push(response.data);
                $scope.newTask = {};
            });
        };
        // Delete task
        $scope.deleteTask = function (task) {
            var index = $scope.tasks.indexOf(task);
            if (index > -1) {
                $scope.tasks.splice(index, 1);
            }
            // You would want to add a call to delete the task from the backend as well:
            // $http.delete('/tasks/' + task.id);
        };
        // Initialize by loading tasks on page load
        $scope.loadTasks();
    }]);
//# sourceMappingURL=taskApp.js.map