angular.module('tarapanaApp.controllers').controller('myCtrl',
		function($scope, pageService) {

			$scope.loadPages = function() {
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
							return d.likes;
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
				pageService.getPages().$promise.then(function(value) {
					$scope.data = value;
				},

				function(error) {
					$scope.data = error;
				});
			};
			$scope.loadPages();
		});