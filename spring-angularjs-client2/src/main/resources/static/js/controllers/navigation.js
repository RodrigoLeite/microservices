angular.module('hello').controller('navigation',

		function($rootScope, $scope, $http, $location, $route) {
			
			$http.get('user').success(function(data) {
			    if (data.name) {
			      $rootScope.authenticated = true;
			    } else {
			      $rootScope.authenticated = false;
			    }
			  }).error(function() {
			    $rootScope.authenticated = false;
			  });

			  $scope.credentials = {};

			  $scope.logout = function() {
			    $http.post('logout', {}).success(function() {
			      $rootScope.authenticated = false;
			      $location.path("/");
			    }).error(function(data) {
			      $rootScope.authenticated = false;
			    });
			  }			
		});