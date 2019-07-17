'use strict';

var App = angular.module('myApp', [ 'ngRoute', 'ngMaterial', 'ngMessages',
		'ngMaterial', 'ngTagsInput', 'nvd3', 'angularjs-datetime-picker',
		'checklist-model', 'datatables', 'googlechart', 'textAngular','angucomplete-alt',
		'ui-notification','mwl.calendar', 'ngAnimate', 'ui.bootstrap', 'colorpicker.module','angularjs-dropdown-multiselect','ngCookies']);

App
		.config(function($routeProvider) {
			$routeProvider

			.when('/', {
				templateUrl : 'Pages/home.html',
			})

			.when('/home', {
				templateUrl : 'Pages/home.html',
			})
			
			.when('/calendar', {
				templateUrl : 'Pages/Calendar/calendar.html',
			})

			.when('/aboutus', {
				templateUrl : 'Pages/aboutus.html',
			})

			.when('/contact', {
				templateUrl : 'Pages/contact.jsp',
			})

			.when('/roleoptionmapping', {
				templateUrl : 'Pages/RoleOption/roleoptionmapping.html',
			})

			.when('/mytests', {
				templateUrl : 'Pages/OnlineTest/onlinetests.html',
			})

			.when('/attendtest', {
				templateUrl : 'Pages/OnlineTest/attendtest.html',
			})

			.when('/attendtest/:testId', {
				templateUrl : 'Pages/OnlineTest/attendtest.html',
			})

			.when('/manageonlinetests', {
				templateUrl : 'Pages/OnlineTest/testmanagement.html',
			})

			.when('/createquestions', {
				templateUrl : 'Pages/OnlineTest/questionsmanagement.html',
			})

			.when('/userlist', {
				templateUrl : 'Pages/UserManagement/userlist.html',
			})

			.when('/training', {
				templateUrl : 'Pages/Training/training.html',
			})

			.when('/markattendance', {
				templateUrl : 'Pages/Attendance/markAttendance.html',
			})

			.when('/testresult', {
				templateUrl : 'Pages/OnlineTest/testresulttrainer.html',
			})

			.when('/addfeedback', {
				templateUrl : 'Pages/Feedback/feedbacklist.html',
			})

			.when('/viewfeedback', {
				templateUrl : 'Pages/Feedback/adminfeedbackview.html',
			})

			.when('/addattendee/interested/:secretKey/:topicid/:trainerid', {
				templateUrl : 'Pages/Training/addattendee.html',
			})

			.when('/secure/resetpassword/:secretKey', {
				templateUrl : 'Pages/UserManagement/resetpassword.html',
			})
			
			.when('/secure/activeaccount/:secretKey', {
				templateUrl : 'Pages/UserManagement/activationsuccess.html',
			})

			.when('/trainingprograms', {
				templateUrl : 'Pages/Training/upcomingtraininglist.html',
			})

			.when('/category', {
				templateUrl : 'Pages/Training/trainingcategory.html',
			})

			.when('/updateprofile', {
				templateUrl : 'Pages/UserManagement/updateprofile.html',
			})

			.when('/changepassword', {
				templateUrl : 'Pages/UserManagement/changepassword.html',
			})

			.when('/mytraininglistfortrainer', {
				templateUrl : 'Pages/Training/mytraininglistfortrainer.html',
			})

			.when('/showtrainingmaterials', {
				templateUrl : 'Pages/Training/trainingmaterial.html',
			})

			.when('/trainingmaterial', {
				templateUrl : 'Pages/Training/AttendeeTrainingMaterial.html',
			})

			.when('/atendeetestresult', {
				templateUrl : 'Pages/TestResult/testresult.html',
			})
			
			.when('/forum', {
				templateUrl : 'Pages/Forom/forum.html',
			})
			
			.when('/companyprofile', {
				templateUrl : 'Pages/OrganizationProfile/organizationprofile.html',
			})
			
			.when('/department', {
				templateUrl : 'Pages/Department/department.html',
			})
			
			.when('/attendeeperformanceanalysis', {
				templateUrl : 'Pages/Reports/performanceanalysis.html',
			})

			.otherwise({
				redirectTo : '/'
			});
		})
		.run(
				function($rootScope, $mdDialog, $location, $http, $window) {

					$rootScope.showmenu = true;

					// register listener to watch route changes
					$rootScope
							.$on(
									"$routeChangeStart",
									function(event, next, current) {

										if (next.originalPath != null
												&& next.originalPath != '/'
												&& next.originalPath.length > 2
												&& next.originalPath != '/aboutus'
													&& next.originalPath != '/calendar'
												&& next.originalPath != '/secure/resetpassword/:secretKey'
												&& next.originalPath != '/secure/activeaccount/:secretKey'
												&& next.originalPath != '/addattendee/interested/:secretKey/:topicid/:trainerid') {

											$http
													.get(
															'isvalidaccess/'
																	+ next.originalPath
																			.replace(
																					'/',
																					''))
													.then(
															function(response) {
																if (response.data) {
																	if (next.templateUrl != "Pages/OnlineTest/attendtest.html") {
																		unBlockBlur();
																		$rootScope.$emit('removeTestTimer');
																	}
																} else {

																	$window.location = "./#";

																	$mdDialog
																			.show({
																				clickOutsideToClose : true,
																				scope : $rootScope,
																				preserveScope : true,
																				contentElement : '#invalidAccessDialoge',
																				controller : function HomeController(
																						$scope,
																						$mdDialog) {
																					$scope.closeDialog = function() {
																						$mdDialog
																								.hide();
																					}
																				}
																			});

																}
															},
															function(
																	errResponse) {
																console
																		.error('Error while fetching users');
															});

										} else if (next.originalPath != null) {
											// openOption(next.originalPath
											// .replace('/', ''));
											if (next.templateUrl != "Pages/OnlineTest/attendtest.html") {
												unBlockBlur();
												$rootScope.$emit('removeTestTimer');
											}
										}

									});
				});

var interceptor = function($q, $window) {
	return {

		'request' : function(config) {
			return config;
		},
		'requestError' : function(rejection) {
			if (canRecover(rejection)) {
				return responseOrNewPromise
			}
			return $q.reject(rejection);
		},

		'response' : function(response) {
			return response;
		},
		'responseError' : function(rejection) {
			if (rejection.status == 402) {
				$window.location.href = './';
			} else if (rejection.status == 500) {
				$window.location.href = './';
			}
			return $q.reject(rejection);
		}
	};

}

App.config(function($httpProvider) {
	$httpProvider.interceptors.push(interceptor);

});

App.directive('fileModel', [ '$parse', function($parse) {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			var model = $parse(attrs.fileModel);
			var modelSetter = model.assign;

			element.bind('change', function() {
				scope.$apply(function() {
					modelSetter(scope, element[0].files[0]);
				});
			});
		}
	};
} ]);

App.controller('AboutUsCuntroller', [ '$scope', function($scope) {

} ]);

App.value('googleChartApiConfig', {
	version : '1.1',
	optionalSettings : {
		packages : [ 'bar' ],
		language : 'en'
	}
});

App.directive('errSrc', function() {
	return {
		link : function(scope, element, attrs) {

			scope.$watch(function() {
				return attrs['ngSrc'];
			}, function(value) {
				if (!value) {
					element.attr('src', attrs.errSrc);
				}
			});

			element.bind('error', function() {
				element.attr('src', attrs.errSrc);
			});
		}
	}
});
App.filter('reverse', function() {
	return function(items) {
		return items.slice().reverse();
	};
});

App.config(function($mdDateLocaleProvider) {
	$mdDateLocaleProvider.formatDate = function(date) {
		return moment(date).format('YYYY-MM-DD');
	};
});

App.config(['$routeProvider', '$httpProvider', function($routeProvider, $httpProvider) {
    $httpProvider.defaults.cache = false;
    if (!$httpProvider.defaults.headers.get) {
      $httpProvider.defaults.headers.get = {};
    }
    $httpProvider.defaults.headers.get['If-Modified-Since'] = '0';
}]);
