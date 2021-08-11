const app = angular.module("admin", []);

app.controller("admin-ctrl", function($scope, $http) {
    // alert("test")
    $scope.orders = [];

    $scope.load = function () {
        // load orders
        $http.get("/rest/admin-orders").then(resp => {
            $scope.orders = resp.data
        })
    }

    $scope.load();

    $scope.update = function(order) {
        var item = angular.copy(order);
        item.status = 1;
        $http.put(`/rest/admin-order/${item.id}`, item).then(resp => {
            alert("Xác nhận đã giao hàng thành công");
            $scope.load();
        })
    }
});