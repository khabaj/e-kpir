
app.controller('registration', function ($scope, $http, $location) {
    var self = this;

    self.register = function () {
    	
        var data = {login: self.user.email, password: self.user.password};
        $http.post('/api/registration', data).success(function (data) {
            self.registered = true;
        }).finally(function () {

        });
    }

});