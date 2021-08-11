const app = angular.module("shopping-cart-app", []);

app.controller("shopping-cart-ctrl", function($scope, $http) {
    // Quản lý giỏ hàng
    $scope.cart = {
        items: [],

        // insert product
        add(id) {
            var item = this.items.find(item => item.id == id);
            if (item) {
                item.qty++;
                this.saveToLocalStorage();
            } else {
                $http.get(`/rest/products/${id}`).then(resp => {
                    resp.data.qty = 1;
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                })
            }
            alert("Thêm vào giỏ hàng thành công")
        }, 

        // delete product
        remove(id){
            var index = this.items.findIndex(item => item.id == id);
            this.items.splice(index, 1);
            this.saveToLocalStorage();
        }, 

        // lear cart
        clear(){
            this.items = [];
            this.saveToLocalStorage();
        }, 
        amt_of(item){}, // tính tiền của 1 sản phẩm

        // Tổng số lượng mặt hàng trong giỏ hàng
        get count() {
            return this.items
                .map(item => item.qty)
                .reduce((total, qty) => total += qty, 0);
        },
        // Tổng thành tiền các mặt hàng trong giỏ
        get amount() {
            return this.items
                .map(item => item.qty * item.price)
                .reduce((total, qty) => total += qty, 0);
        },

        // Đọc giỏ hàng từ localStorage
        loadFromLocalStorage() {
            var json = localStorage.getItem("cart");
            this.items = json ? JSON.parse(json): [];
        },

        // Lưu cart vào LocalStorage
        saveToLocalStorage() {
            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart", json);
        }
    }

    $scope.cart.loadFromLocalStorage();

    $scope.order = {
        createDate: new Date(),
        address:"",
        sumprice: $scope.cart.amount,
        phone: "",
        account: {username: $("#Accountid").text()},
        get orderDetail() {
            return $scope.cart.items.map(item => {
                return {
                    product:{id: item.id},
                    price: item.price,
                    quantity: item.qty
                }
            });
        },
        purchase() {
            var order = angular.copy(this);

            // Thực hiện đặt hàng
            $http.post("/rest/orders", order).then(resp => {
                alert("Đặt hàng thành công.");
                $scope.cart.clear();
                location.href = "/shop";
            }).catch(error => {
                alert("Lỗi đặng hàng")
                console.log(error)
            })
        }
    }
});