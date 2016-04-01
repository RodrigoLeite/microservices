angular.module('hello').controller(
		'navigation',

		function($rootScope, $scope, $http, $location, $route) {
			
			$scope.tab = function(route) {
				return $route.current && route === $route.current.controller;
			};

			var authenticate = function(credentials, callback) {

				var headers = credentials ? {
					authorization : "Basic "
							+ btoa(credentials.username + ":"
									+ credentials.password)
				} : {};

				$http.get('user', {
					headers : headers
				}).success(function(data) {
					if (data.name) {
						$rootScope.authenticated = true;
					} else {
						$rootScope.authenticated = false;
					}
					callback && callback();
				}).error(function(data) {
					$rootScope.authenticated = false;
					callback && callback();
				});
			}

			authenticate();
			$scope.credentials = {};

			$scope.login = function() {
				authenticate($scope.credentials, function() {
					if ($rootScope.authenticated) {
						$location.path("/");
						$scope.error = false;
					} else {
						$location.path("/login");
						$scope.error = true;
					}
				});
			};

			$scope.logout = function() {
				$http.post('logout', {}).success(function() {
					$rootScope.authenticated = false;
					$location.path("/");
				}).error(function(data) {
					$rootScope.authenticated = false;
				});
			};
		});