const app = angular.module("category", []);

app.controller("category-ctrl", function($scope, $http) {
    // alert("test")
    $scope.items = [];
    $scope.form = {};

    $scope.initialize = function() {
        // load category
        $http.get("/rest/category").then(resp => {
            $scope.items = resp.data;
        });
    }

    // Khởi đầu
    $scope.initialize();

    // delete form
    $scope.reset = function() {
        $scope.form = {};
    }
    $scope.reset();

    // view form
    $scope.edit = function(item) {
        $scope.form = angular.copy(item);
    }

    // insert product
    $scope.create = function() {
        var item = angular.copy($scope.form);
        $http.post(`/rest/category`, item).then(resp => {
            $scope.items.push(resp.data);
            $scope.reset();
            alert("Thêm mới thành công.");
        }).catch(error => {
            alert("Lỗi");
            console.log("error", error);
        })
    }

    // update product
    $scope.update = function() {
        var item = angular.copy($scope.form);
        $http.put(`/rest/category/${item.id}`, item).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id);
            $scope.items[index] = item;
            $scope.initialize();
            alert("Cập nhật sản phẩm thành công.");
        }).catch(error => {
            alert("Lỗi.");
            console.log("Error", error);
        })
    }

    // delete product
    $scope.delete = function(item) {
        $http.delete(`/rest/category/${item.id}`).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id);
            $scope.items.splice(index, 1);
            $scope.reset();
            alert("Xóa thành công.");
        }).catch(error => {
            alert("Lỗi");
            console.log("Error", error);
        })
    }
})