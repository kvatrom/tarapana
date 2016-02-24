angular.module('tarapanaApp.controllers', [ 'nvd3' ]).factory('pageService',
		function($resource) {

			return $resource('service/tracking-entities', {}, {
				getPages : {
					method : 'GET',
					isArray : true
				}
			});
		});