var app = angular.module("OrderManagement", []);
 
// Controller Part
app.controller("OrderController", function($scope, $http) {
 
 
    $scope.orders = [];
    $scope.orderForm = {
    	orderId: -1,
    	orderType: "BUY",
    	price: "",
    	volume: ""
    };
 
    // Now load the data from server
    _refreshOrderData();
    
    _orderBook();
 
    // HTTP POST/PUT methods for add/edit order  
    // Call: http://localhost:8080/order
    $scope.submitOrder = function() {
 
        var method = "";
        var url = "";
 
        if ($scope.orderForm.orderId == -1) {
            method = "POST";
            url = '/order';
        } else {
            method = "PUT";
            url = '/order';
        }
 
        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.orderForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_splSuccess, _error);
    };
 
    $scope.createOrder = function() {
        _clearFormData();
    }
 
    // HTTP DELETE- delete employee by Id
    // Call: http://localhost:8080/order/{orderId}
    $scope.deleteOrder = function(order) {
        $http({
            method: 'DELETE',
            url: '/order/' + order.orderId
        }).then(_success, _error);
    };
 
    // In case of edit
    $scope.editOrder = function(order) {
        $scope.orderForm.orderId = order.orderId;
        $scope.orderForm.orderType = order.orderType;
        $scope.orderForm.price = order.price;
        $scope.orderForm.volume = order.volume;
    };
 
    // Private Method  
    // HTTP GET- get all employees collection
    // Call: http://localhost:8080/orders
    function _refreshOrderData() {
        $http({
            method: 'GET',
            url: '/orders'
        }).then(
            function(res) { // success
                $scope.orders = res.data;
            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }
    
    function _orderBook() {

        $http({
            method: 'GET',
            url: '/buyOrders'
        }).then(
            function(bres) { // success
                $scope.buyOrders = bres.data;
            },
            function(bres) { // error
                console.log("Error: " + bres.status + " : " + bres.data);
            }
        );
        $http({
            method: 'GET',
            url: '/sellOrders'
        }).then(
            function(sres) { // success
                $scope.sellOrders = sres.data;
            },
            function(sres) { // error
                console.log("Error: " + sres.status + " : " + sres.data);
            }
        );
    }
 
    function _success(res) {
        _refreshOrderData();
        _orderBook();
        _clearFormData();
    }
    
    function _splSuccess(res) {
        alert("200 Form submitted successfully");
        _refreshOrderData();
        _orderBook();
        _clearFormData();
    }
 
    function _error(res) {
    	alert("400 An Error Occurred");
        var data = res.data;
        var status = res.status;
        var header = res.header;
        var config = res.config;
        alert("Error: " + status + ":" + data);
    }   
 
    // Clear the form
    function _clearFormData() {
        $scope.orderForm.orderId = -1;
        $scope.orderForm.orderType = "BUY";
        $scope.orderForm.price = "";
        $scope.orderForm.volume = "";
    };
});