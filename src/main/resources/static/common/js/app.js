angular.module('ekpir', [
        'ekpir.account',
        'ekpir.company',
        'ui.router',
        'ekpir.validation'
    ])
    .constant('configuration', {
        apiURL: '/api/'
    })
    .config(function ($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise('/');

        $stateProvider.state('login', {
                url: '/login',
                views: {
                    'main': {
                        templateUrl: 'account/login.html',
                        controller: 'LoginCtrl',
                        controllerAs: 'controller'
                    }
                },
                data: {pageTitle: "Logowanie"}
            })
            .state('register', {
                    url: '/register',
                    views: {
                        'main': {
                            templateUrl: 'account/registration.html',
                            controller: 'RegisterCtrl',
                            controllerAs: 'controller'
                        }
                    },
                    data: {pageTitle: "Rejestracja"}
                }
            )
            .state('account', {
                    url: '/account',
                    views: {
                        'main': {
                            templateUrl: 'account/accountSettings.html',
                            controller: 'AccountSettingsCtrl',
                            controllerAs: 'controller'
                        }
                    },
                    data: {pageTitle: "Ustawienia konta"}
                }
            )
            .state('home', {
                    url: '/',
                    views: {
                        'main': {
                            templateUrl: 'home/home.html'
                        }
                    },
                    data: {pageTitle: "Home"}
                }
            )
            .state('contractors', {
                    url: '/contractors',
                    views: {
                        'main': {
                            templateUrl: 'contractors/contractors.html'
                        }
                    },
                    data: {pageTitle: "Kontrahenci"}
                }
            )
            .state('company', {
                    url: '/company',
                    views: {
                        'main': {
                            templateUrl: 'company/company.html',
                            controller: 'CompanyCtrl',
                            controllerAs: 'controller'
                        }
                    },
                    data: {pageTitle: "Dane firmy"}
                }
            )
    })

    .run(function run($state) {
        $state.go("home");
    })

    .factory('authHttpResponseInterceptor', ['$q', '$injector' , function ($q, $injector) {
        return {
            response: function (response) {
                if (response.status === 401) {
                    var sessionService = $injector.get('sessionService');
                    sessionService.logout();
                }
                return response || $q.when(response);
            },
            responseError: function (rejection) {
                if (rejection.status === 401) {
                    var sessionService = $injector.get('sessionService');
                    sessionService.logout();
                }
                return $q.reject(rejection);
            }
        }
    }])
    .config(['$httpProvider', function ($httpProvider) {
        //Http Intercpetor to check auth failures for xhr requests
        $httpProvider.interceptors.push('authHttpResponseInterceptor');
    }])

    .controller('AppCtrl', function AppCtrl($scope, sessionService, $state) {
        $scope.$on('$stateChangeSuccess', function (event, toState, toParams, fromState, fromParams) {
            if (angular.isDefined(toState.data.pageTitle)) {
                $scope.pageTitle = toState.data.pageTitle + ' | e-kpir';
            }
        });

        $scope.user = sessionService.getUser;
        $scope.isLoggedIn = sessionService.isLoggedIn;
        $scope.logout = sessionService.logout;
        $scope.getUserId = sessionService.getUserId;
    })
;