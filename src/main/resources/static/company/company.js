angular.module('ekpir.company', ['ui.router', 'ngResource'])

    .factory('Company',
        function ($resource, configuration) {
            return $resource(configuration.apiURL + 'company/:companyId', {
                companyId: '@companyId'
            }, {
                update: {
                    method: 'PUT' // this method issues a PUT request
                }
            })
        })

    .controller('CompanyCtrl', function ($scope, $resource, Company) {

        $scope.company = Company.get({userId: $scope.getUserId()});

        $scope.updateCompany = function () {
            Company.update($scope.company);
        }
    });

