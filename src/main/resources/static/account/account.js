/**
 * Created by Krystian on 2016-04-08.
 */
angular.module('ekpir.account', ['ui.router', 'ngResource'])

    .factory('sessionService', function ($http, $state) {
        var session = {};
        session.login = function (account) {
            return $http.post("/login", "username=" + account.username +
                "&password=" + account.password, {
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            }).success(function () {
                $http.get('/api/users/login/' + account.username)
                    .success(function (response) {
                        localStorage.setItem("session", response.login);
                        localStorage.setItem("userId", response.userId);
                    }).finally(function () {
                });
            });
        };
        session.logout = function () {
            $http.post('logout', {}).finally(function () {
                $state.go("login");
            });
            localStorage.removeItem("session");
        };
        session.isLoggedIn = function () {
            return localStorage.getItem("session") !== null;
        };
        session.getUser = function () {
            return localStorage.getItem("session");
        };
        session.getUserId = function () {
            return localStorage.getItem("userId");
        };

        return session;
    })


    .factory('accountService', function ($http) {
        var service = {};
        service.register = function (account) {
            var data = {login: account.email, password: account.password};
            return $http.post('/api/registration', data);
        };
        return service;
    })


    .controller("LoginCtrl", function ($scope, sessionService, $state) {
        var self = this;
        $scope.login = function () {
            sessionService.login($scope.account).success(function () {
                    $state.go("home");
                })
                .error(function () {
                    self.error = true;
                });
        }
    })


    .controller("RegisterCtrl", function ($scope, $state, accountService) {

        $scope.register = function () {
            $scope.error = false;
            $scope.registered = false;

            accountService.register($scope.account).success(function () {
                    $scope.registered = true;
                })
                .error(function () {
                    $scope.error = true;
                });
        }
    });