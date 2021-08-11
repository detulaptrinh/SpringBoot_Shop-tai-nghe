const app = angular.module("account", []);

app.controller("account-ctrl", function($scope, $http) {
    // alert("test")
    $scope.account = [];
    $scope.listUser = [];

    $scope.load = function() {
        $http.get("/account/info").then(resp => {
            $scope.account = resp.data;
        }).catch(error => {
            console.log("Error", error)
        })
        
        // list user
        $http.get("/account/list").then(resp => {
            $scope.listUser = resp.data;
        }).catch(error => {
            console.log("Error", error)
        })
    }

    $scope.load();

    // update account
    $scope.update = function() {
        var item = angular.copy($scope.account);
        $http.put(`/account/info/${item.username}`, item).then(resp => {
            $scope.account = resp.data;
            $scope.load();
            alert("Cập nhật thành công.");
        }).catch(error => {
            alert("Lỗi.");
            console.log("Error", error);
        })
    }

    $scope.reset = function() {
        $scope.pass = "";
        $scope.newPass = "";
        $scope.newPass1 = "";
    }

    // check pass
    $scope.check = function() {
        if($scope.pass === $scope.account.password) {
            if($scope.newPass === $scope.newPass1) {
                var item = angular.copy($scope.account);
                item.password = angular.copy($scope.newPass);
                $http.put(`/account/info/${item.username}`, item).then(resp => {
                    $scope.account = resp.data;
                    $scope.reset();
                    alert("Cập nhật thành công.");
                }).catch(error => {
                    alert("Lỗi.");
                    console.log("Error", error);
                })
            } else {
                alert("Mật khẩu mới phải giống nhau")
            }
        } else {
            alert("Sai Mật khẩu")
        }
    }
});