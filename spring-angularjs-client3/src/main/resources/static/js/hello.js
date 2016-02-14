angular.module('hello', ['ngRoute'])
	.config(function($routeProvider, $httpProvider) {
		
		$routeProvider.when('/', {
			templateUrl: 'home.html',
			controller: 'home'
		})
		.otherwise('/');
		
		$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
	});