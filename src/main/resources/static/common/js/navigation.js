app.controller('navigation', function ($rootScope, $http, $location) {

    var self = this;

    var authenticate = function (credentials, callback) {

        var headers = credentials ? {
            authorization: "Basic "
            + btoa(credentials.username + ":" + credentials.password)
        } : {};

        $http.get('api/users/logged', {headers: headers}).success(function (data) {
            if (data.name) {
                $rootScope.authenticated = true;
                $rootScope.userName = data.name;
            } else {
                $rootScope.authenticated = false;
                $rootScope.userName = "";
            }
            callback && callback();
        }).error(function () {
            $rootScope.authenticated = false;
            $rootScope.userName = "";
            callback && callback();
        });
    }

    authenticate();
    self.credentials = {};
    self.login = function () {
        authenticate(self.credentials, function () {
            if ($rootScope.authenticated) {
                $location.path("/");
                self.error = false;
            } else {
                $location.path("/login");
                self.error = true;
            }
        });
    };

    self.logout = function () {
        $http.post('logout', {}).finally(function () {
            $rootScope.authenticated = false;
            $rootScope.userName = "";
            $location.path("/");
        });
    }
});


