'use strict';
/**
 * @Author Jinshad P.T.
 * @Date 25/08/2016
 */

App
		.controller(
				'RoleOptionController',
				[
						'$scope',
						'$rootScope',
						'$timeout',
						'RoleOptionService',
						function($scope, $rootScope, $timeout,
								RoleOptionService) {

							var self = this;
							self.chk = null;
							self.options = null;
							self.master = 0;
							self.roleAccess = {
								optionIDs : [],
								roleId : ''
							};

							self.fetchAllOptions = function() {

								if (self.roleAccess.roleId != '') {
									RoleOptionService
											.fetchAllOptions(
													self.roleAccess.roleId)
											.then(
													function(response) {
														self.options = response[0];
														self.roleAccess.optionIDs = [];
														angular
																.forEach(
																		response[1],
																		function(
																				value,
																				index) {
																			self.roleAccess.optionIDs
																					.push(value.optionId);
																		});

													},
													function(errResponse) {
														console
																.error('Error while fetching Currencies');
													});

								} else {
									self.options = [];
								}
							};

							self.submit = function($event) {
								self.saveAttendance(self.roleAccess);
							}

							self.saveUserAccess = function(roleAccess) {

								if (self.roleAccess.roleId == '') {
									$rootScope.showNotification(
											"Please select role", false);
								} else {
									RoleOptionService
											.saveUserAccess(self.roleAccess)
											.then(
													function(response) {
														$rootScope
																.showNotification(
																		"Saved successfully",
																		true);
													},
													function(errResponse) {
														$rootScope
																.showNotification(
																		"Saving failed",
																		false);
													});
								}
							}

							self.checkAllElements = function() {
								if (self.master == 1) {
									self.checkAll();
								} else {
									self.unCheckAll();
								}
							}

							self.unCheckAll = function() {
								if (self.options.length) {
									self.roleAccess.optionIDs = [];
								} else {
								}
							}

							self.checkAll = function() {

								self.chk = [];
								angular.forEach(self.options, function(value,
										index) {
									self.chk.push(value.optionId);
								});
								if (self.roleAccess.optionIDs.length <= self.chk.length) {
									self.roleAccess.optionIDs = angular
											.copy(self.chk);
								}
							};

						} ]);
