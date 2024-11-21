interface Task {
    id: number;
    title: string;
    description?: string;
    completed: boolean;
}

const app = angular.module("taskApp", []);

app.controller("TaskController", ["$scope", "$http", function($scope: any, $http: ng.IHttpService) {
    $scope.tasks = [] as Task[];
    $scope.newTask = {} as Partial<Task>;

    // Load tasks from the backend
    $scope.loadTasks = (): void => {
        $http.get<Task[]>("/tasks").then(response => {
            $scope.tasks = response.data;
        });
    };

    // Add new task
    $scope.addTask = (): void => {
        $http.post<Task>("/tasks", $scope.newTask).then(response => {
            $scope.tasks.push(response.data);
            $scope.newTask = {};
        });
    };

    // Delete task
    $scope.deleteTask = (task: Task): void => {
        const index = $scope.tasks.indexOf(task);
        if (index > -1) {
            $scope.tasks.splice(index, 1);
        }
        // You would want to add a call to delete the task from the backend as well:
        // $http.delete('/tasks/' + task.id);
    };

    // Initialize by loading tasks on page load
    $scope.loadTasks();
}]); 