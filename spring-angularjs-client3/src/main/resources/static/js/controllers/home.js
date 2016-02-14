angular.module('hello').controller('home', 
		function($scope, $http) {
			$http.get('resource/')
				.success(function(data) {
					$scope.greeting = data;
				})
				.error(function(statusText) {
					console.log(statusText);
				})
	})