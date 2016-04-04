var app = angular
		.module('ekpir', [ 'ngRoute' ])
		.config(
				function($routeProvider, $httpProvider) {

					$routeProvider.when('/', {
						templateUrl : 'views/home.html',
						controller : 'home',
						controllerAs : 'controller'
					}).when('/login', {
						templateUrl : '/views/login.html',
						controller : 'navigation',
						controllerAs : 'controller'
					}).when('/register', {
						templateUrl : '/views/registration.html',
						controller : 'registration',
						controllerAs : 'controller'
					}).otherwise('/');

					$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
				});
