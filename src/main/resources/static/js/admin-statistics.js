const app = angular.module("app", []);

app.controller("ctrl", function($scope, $http) {
    $scope.list = [];

    $scope.listDay = function () {
        $http.get("/rest/day").then(resp => {
            $scope.list = resp.data;
        }).catch(error => {
            console.log("Error", error)
        })
    }

    $scope.listDay();

    $scope.listYesterday = function () {
        $http.get("/rest/yesterday").then(resp => {
            $scope.list = resp.data;
        }).catch(error => {
            console.log("Error", error)
        })
    }
    $scope.listMoth = function () {
        $http.get("/rest/moth").then(resp => {
            $scope.list = resp.data;
        }).catch(error => {
            console.log("Error", error)
        })
    }
    $scope.listYear = function () {
        $http.get("/rest/year").then(resp => {
            $scope.list = resp.data;
        }).catch(error => {
            console.log("Error", error)
        })
    }
});