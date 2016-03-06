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
									y : 57
								},
								{
									key : "Female",
									y : 42
								},
								{
									key : "NoGender",
									y : 1
								} ];

						$scope.nextChart = $scope.ageChart;
					};
					
					$scope.ageChart = function() {
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
									key : "Under 20",
									y : 11
								},
								{
									key : "20-30",
									y : 57
								},
								{
									key : "30-40",
									y : 17
								},
								{
									key : "40-50",
									y : 9
								},
								{
									key : "Over 50",
									y : 4
								},
								{
									key : "No Age",
									y : 2
								}];

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
