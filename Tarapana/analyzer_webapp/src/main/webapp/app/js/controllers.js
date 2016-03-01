angular.module('app').factory('pageService',
		function($resource) {

			return $resource('service/pageInfo', {}, {
				getPages : {
					method : 'GET',
					isArray : true
				}
			});
		});