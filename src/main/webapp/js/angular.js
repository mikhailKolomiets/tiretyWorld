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

    $scope.refrash = function () {
        $http.get("/get-locations")
            .then(function (response) {
                $scope.locations = response.data;

            });
    }
});

app.controller('getUserbeg', function ($scope, $http) {
    $http.get("/get-userbeg")
        .then(function (response) {
            var json = $scope.userbeg = response.data;

            $scope.crop = json["Пшеница"];
        });
});

app.controller('getAllCroplands', function ($scope, $http) {
    $http.get("/get-all-croplands")
        .then(function (response) {
            $scope.croplands = response.data;
        });
});

app.controller('getBanking', function ($scope, $http) {
    $http.get("/get-banking")
        .then(function (response) {
            $scope.bank = response.data;
        });
});

app.controller('gameTime', function ($scope, $http) {
    $http.get("/getGameTime")
        .then(function (response) {
            $scope.time = response.data;

            $scope.sDate = Date.parse(response.data.gameTime);
            var gameDate = new Date($scope.sDate);

            $scope.mesDate = (gameDate.getFullYear()) + " г " + (gameDate.getMonth() + 1) + " м " + gameDate.getDate() +
                " д";

        });
});

app.controller('counter', function ($scope, $http) {
    $http.get("/counter")
        .then(function (response) {
            $scope.count = response.data;
        });
});