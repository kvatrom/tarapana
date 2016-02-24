angular.module('tarapanaApp.controllers').controller('myCtrl',
		function($scope, pageService) {

			$scope.loadPages = function() {
				$scope.options = {
					chart : {
						type : 'discreteBarChart',
						height : 450,
						margin : {
							top : 20,
							right : 20,
							bottom : 60,
							left : 55
						},
						x : function(d) {
							return d.label;
						},
						y : function(d) {
							return d.value;
						},
						showValues : true,
						valueFormat : function(d) {
							return d3.format(',.4f')(d);
						},
						transitionDuration : 500,
						xAxis : {
							axisLabel : 'Kolicina lajkova'
						},
						yAxis : {
							axisLabel : 'Stranica',
							axisLabelDistance : 30
						}
					}
				};
				pageService.getPages().$promise.then(function(value) {
					$scope.data = value;
				},

				function(error) {
					$scope.data = error;
				});
			};
			$scope.loadPages();
		});