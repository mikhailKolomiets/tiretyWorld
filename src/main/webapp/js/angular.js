/**
 * Created by mihail on 10.10.17.
 */

var app = angular.module('tirety', []);

app.controller('checklog', function($scope, $http) {
    $http.get("/checklog")
        .then(function(response) {
            $scope.user = response.data;
            if ($scope.user === null)
                document.location.href = "/";
        });
});
