/**
 * Created by mihail on 10.10.17.
 */

var app = angular.module('tirety', []);

app.controller('checklog', function ($scope, $http) {
    $http.get("/checklog")
        .then(function (response) {
            $scope.user = response.data;
            if ($scope.user === null)
                document.location.href = "/";
        });
});

app.controller('getMessage', function ($scope, $http) {
    $http.get("/get-message")
        .then(function (response) {
            $scope.message = response.data;
        });
});

app.controller('userGo', function ($scope, $http) {
    $scope.locations = 'iii';
    $scope.refrash = function () {
        $http.get("/get-locations")
            .then(function (response) {
                $scope.locations = response.data;
                $scope.any = 444;
            });
    }
});
