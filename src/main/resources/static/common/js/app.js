var app = angular
		.module('ekpir', [ 'ngRoute' ])
		.config(
				function($routeProvider, $httpProvider) {

					$routeProvider.when('/', {
						templateUrl : 'common/views/home.html',
						controller : 'home',
						controllerAs : 'controller'
					}).when('/login', {
						templateUrl : 'common/views/login.html',
						controller : 'navigation',
						controllerAs : 'controller'
					}).when('/register', {
						templateUrl : 'registration/registration.html',
						controller : 'registration',
						controllerAs : 'controller'
					}).when('/contractors', {
						templateUrl : 'contractors/contractors.html',
						controller : 'contractors',
						controllerAs : 'controller'
					}).when('/company', {
						templateUrl : 'company/company.html',
						controller : 'company',
						controllerAs : 'controller'
					}).when('/settings', {
						templateUrl : 'settings/settings.html',
						controller : 'settings',
						controllerAs : 'controller'
					})
					
					.otherwise('/');

					$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
				});
