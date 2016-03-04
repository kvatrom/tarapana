angular
		.module('app')
		.controller(
				'chartController',
				function($scope, pageService) {

					$scope.allPagesLikesChart = function() {
						$scope.data = $scope.value;
						$scope.options = {
							chart : {
								type : 'discreteBarChart',
								height : 800,
								margin : {
									top : 20,
									right : 20,
									bottom : 60,
									left : 85
								},
								x : function(d) {
									return d.name;
								},
								y : function(d) {
									return d.numberOfLikes;
								},
								showValues : true,
								valueFormat : function(d) {
									return d3.format('d')(d);
								},
								transitionDuration : 500,
								xAxis : {
									axisLabel : 'Kolicina lajkova'
								},
								yAxis : {
									axisLabel : 'Stranica',
									axisLabelDistance : 50
								}
							}
						};
						$scope.nextChart = $scope.genderChart;
					};

					$scope.genderChart = function() {
						$scope.options = {
							chart : {
								type : 'pieChart',
								height : 500,
								x : function(d) {
									return d.key;
								},
								y : function(d) {
									return d.y;
								},
								showLabels : true,
								duration : 500,
								labelThreshold : 0.01,
								labelSunbeamLayout : true,
								legend : {
									margin : {
										top : 5,
										right : 35,
										bottom : 5,
										left : 0
									}
								}
							}
						};
						$scope.data = [
								{
									key : "Male",
									y : $scope.value[0].values[$scope.value[0].values.length - 1].numberOfMaleActiveLikes
								},
								{
									key : "Female",
									y : $scope.value[0].values[$scope.value[0].values.length - 1].numberOfFemaleActiveLikes
								},
								{
									key : "NoGender",
									y : $scope.value[0].values[$scope.value[0].values.length - 1].numberOfNoGenderActiveLikes
								} ];

						$scope.nextChart = $scope.allPagesLikesChart;
					};

					pageService.getPages().$promise.then(function(value) {
						$scope.value = value;
						console.log(value);
						$scope.initialised = true;
						$scope.nextChart = $scope.allPagesLikesChart;
						$scope.nextChart();
					},

					function(error) {
						$scope.data = error;
						$scope.initialised = true;
						$scope.genderChart();
					});
				});
