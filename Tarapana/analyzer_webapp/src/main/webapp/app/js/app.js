angular.module('app', ['ngRoute', 'ngResource', 'nvd3'])
.run(['$rootScope','pageService', function ($rootScope,pageService) {
	/*pageService.getPages().$promise.then(function(value) {
		$rootScope.dataLikesPerPage = value;
		$rootScope.$apply($rootScope.dataLikesPerPage);
		console.log(value);
	});*/
}]);