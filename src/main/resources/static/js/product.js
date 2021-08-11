const app = angular.module("product", []);

app.controller("product-ctrl", function($scope, $http) {
    // alert("test")
    $scope.items = [];
    $scope.form = {};
    $scope.cates = [];

    $scope.initialize = function() {
        // load product
        $http.get("/rest/products").then(resp => {
            $scope.items = resp.data;
            $scope.items.forEach(item => {
                item.createDate = new Date(item.createDate)
            })
        });
        // load category
        $http.get("/rest/category").then(resp => {
            $scope.cates = resp.data;
        });
    }

    // Khởi đầu
    $scope.initialize();

    // delete form
    $scope.reset = function() {
        $scope.form = {
            createDate: new Date(),
            image: 'cloud-upload.jpg',
            availble: true
        };
    }
    $scope.reset();

    // view form
    $scope.edit = function(item) {
        $scope.form = angular.copy(item);
    }

    // insert product
    $scope.create = function() {
        var item = angular.copy($scope.form);
        $http.post(`/rest/products`, item).then(resp => {
            resp.data.createDate = new Date(resp.data.createDate)
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
        $http.put(`/rest/products/${item.id}`, item).then(resp => {
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
        $http.delete(`/rest/products/${item.id}`).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id);
            $scope.items.splice(index, 1);
            $scope.reset();
            alert("Xóa thành công.");
        }).catch(error => {
            alert("Lỗi");
            console.log("Error", error);
        })
    }

    // upload image
    $scope.imageChanged = function(files) {
        var data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/upload/images', data, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(resp => {
            $scope.form.image = resp.data.name;
        }).catch(error => {
            alert("Lỗi Upload hình ảnh");
            console.log("Error", error);
        })
    }

    // pager
    $scope.pager = {
        page: 0,
        size: 4,
        get items() {
            var start = this.page * this.size;
            return $scope.items.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1.0 * $scope.items.length / this.size);
        },
        first() {
            this.page = 0;
        },
        prev() {
            this.page--;
            if(this.page < 0) {
                this.last()
            }
        },
        next() {
            this.page++;
            if(this.page >= this.count) {
                this.first();
            }
        },
        last() {
            this.page = this.count -1;
        }
    }
})