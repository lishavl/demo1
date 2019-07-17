<!doctype html>
<html class="no-js" lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8">
<title>Training Management System</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">


<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/colorpicker.min.css" rel="stylesheet">
<link href="css/angular-bootstrap-calendar.min.css" rel="stylesheet">

<link rel="stylesheet" href="css/angular-material.min.css">

<script src="js/angular/lib/jquery.min.js"></script>


<link rel="stylesheet" href="css/ng-tags-input.min.css" />

<link rel="stylesheet" href="css/angularjs-datetime-picker.css" />

<link rel="stylesheet" href="css/textAngular.css" />

<link rel="icon" href="images/favicon.ico">
<!-- Place favicon.ico in the root directory -->
<link rel="stylesheet" href="css/vendor.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/loader-style.css">
<link rel="stylesheet" href="css/ui-notification.css">
<!-- Theme initialization -->
<script>
	var themeSettings = (localStorage.getItem('themeSettings')) ? JSON
			.parse(localStorage.getItem('themeSettings')) : {};
	var themeName = themeSettings.themeName || '';
	if (themeName) {
		document
				.write('<link rel="stylesheet" id="theme-style" href="css/app-' + themeName + '.css">');
	} else {
		document
				.write('<link rel="stylesheet" id="theme-style" href="css/app.css">');
	}
</script>


</head>

<body ng-app="myApp">
	<div class="main-wrapper" ng-controller="HomeController as homeCtrl">
		<div class="app" id="app">
			<header class="header">
				<div class="header-block header-block-collapse hidden-lg-up">
					<button class="collapse-btn" id="sidebar-collapse-btn">
						<i class="fa fa-bars"></i>
					</button>
				</div>
				<div class="header-block header-block-search hidden-sm-down">
					<form role="search">
						<!-- <div class="input-container">
							<i class="fa fa-search"></i> <input type="search"
								placeholder="Search">
							<div class="underline"></div>
						</div> -->
					</form>
				</div>
				<div class="header-block header-block-buttons"></div>
				<div class="header-block header-block-nav">
					<ul class="nav-profile">

						<li ng-show="roleId!=null" class="notifications new" ng-cloak><a
							href="javascript:void(0)" data-toggle="dropdown"> <i
								class="fa fa-bell-o"></i> <sup> <span class="counter"
									ng-bind="notificationListToShow.length"
									ng-if="notificationListToShow.length > 0"></span>
							</sup>
						</a>
							<div class="dropdown-menu notifications-dropdown-menu">
								<ul class="notifications-container"
									ng-show="notificationListToShow.length>0">
									<li ng-repeat="n in notificationListToShow" ng-if="n.type == 1"
										ng-click="homeCtrl.changeStatus(n.notificationid)"><a
										href="#mytraininglistfortrainer" class="notification-item">
											<div class="img-col">
												<div class="img"
													style="background-image: url('images/info.png')"></div>
											</div>
											<div class="body-col">
												<p>
													New Training <span class="accent" ng-bind=" n.topicname"></span>
													is scheduled for <span class="accent"> {{n.sdate |
														date:'dd MMM HH:mm'}}</span>
												</p>
											</div>
									</a></li>

									<li ng-repeat="n in notificationListToShow" ng-if="n.type == 2"
										ng-click="homeCtrl.changeStatus(n.notificationid)"><a
										href="#mytraininglistfortrainer" class="notification-item">
											<div class="img-col">
												<div class="img"
													style="background-image: url('images/info.png')"></div>
											</div>
											<div class="body-col">
												<p>
													You have received feedback from <span class="accent"
														ng-bind=" (n.firstName) + ' ' +(n.lastName) "></span> for
													<span class="accent"> {{n.topicname}}</span>
												</p>
											</div>
									</a></li>

									<li ng-repeat="n in notificationListToShow" ng-if="n.type == 3"
										ng-click="homeCtrl.changeStatus(n.notificationid)"><a
										href="javascript:void(0)" class="notification-item">
											<div class="img-col">
												<div class="img"
													style="background-image: url('images/info.png')"></div>
											</div>
											<div class="body-col">
												<p>
													<span class="accent"
														ng-bind="(n.firstName)+ ' ' +(n.lastName) "></span> has
													not attend the test <span class="accent"
														ng-bind=" (n.topicname) "></span>
												</p>
											</div>
									</a></li>

									<li ng-repeat="n in notificationListToShow" ng-if="n.type == 4"
										ng-click="homeCtrl.changeStatus(n.notificationid)"><a
										href="javascript:void(0)" class="notification-item">
											<div class="img-col">
												<div class="img"
													style="background-image: url('images/info.png')"></div>
											</div>
											<div class="body-col">
												<p>
													<span class="accent"
														ng-bind="(n.firstName)+ ' ' +(n.lastName) "></span> has
													not yet submitted the feedback for <span class="accent"
														ng-bind=" (n.topicname) "></span>.
												</p>
											</div>
									</a></li>
								</ul>
								<ul class="notifications-container"
									ng-show="notificationListToShow.length<=0">
									<li style="color: gray; width: 100%; text-align: center;"><i>No
											notifications found</i></li>
								</ul>
								<footer> </footer>
							</div></li>


						<li class="profile dropdown" ng-show="roleId==null" ng-cloak><a
							class="nav-link dropdown-toggle" data-toggle="dropdown"
							href="javascript:void(0)" role="button" aria-haspopup="true"
							aria-expanded="false">
								<div class="img"
									style="background-image: url('./images/defaultuser.png')">
								</div> <span class="name"> Guest </span>
						</a>
							<div class="dropdown-menu profile-dropdown-menu"
								aria-labelledby="dropdownMenu1">
								<a class="dropdown-item" href="javascript:void(0)"
									ng-click="openLoginWindow()"> <i class="fa fa-sign-in icon"></i>
									Login
								</a> <a class="dropdown-item" href="javascript:void(0)"
									ng-click="openRegisterWindow()"> <i
									class="fa fa-user-plus icon"></i> Sign Up
								</a>
							</div></li>


						<li ng-show="roleId!=null" class="profile dropdown" ng-cloak><a
							class="nav-link dropdown-toggle" data-toggle="dropdown"
							href="javascript:void(0)" role="button" aria-haspopup="true"
							aria-expanded="false"> <img class="img"
								ng-src="./uploads/profileimages/{{userId}}.jpeg"
								err-src="./images/defaultuser.png"> <span class="name"
								ng-bind="userDetails.firstName+' '+userDetails.lastName">
							</span>
						</a>
							<div class="dropdown-menu profile-dropdown-menu"
								aria-labelledby="dropdownMenu1">
								<a class="dropdown-item" href="#updateprofile"> <i
									class="fa fa-user icon"></i> Profile
								</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="logout"> <i
									class="fa fa-power-off icon"></i> Logout
								</a>
							</div></li>


					</ul>
				</div>
			</header>
			<aside class="sidebar">
				<div class="sidebar-container">
					<div class="sidebar-header">
						<div class="brand">
							<a href="./" target="_parent"><img
								ng-src="./uploads/logo/company_logo.jpeg"
								err-src="./images/company_logo.png"
								style="width: 75%; max-width: 160px; height: auto;" /></a>
						</div>
					</div>
					<nav class="menu">
						<ul class="nav metismenu" id="sidebar-menu" ng-show="fullyLoaded"
							ng-cloak>
							<li id="home"><a target="_parent" href="#"> <i
									class="fa fa-home"></i> <span
									ng-bind="roleId!=null ? 'Dashboard' : 'Home'"></span>
							</a></li>

							<li id="mg1" ng-show="homeCtrl.menuGroup1.length>0"><a
								href="javascript:void(0)"> <i class="fa fa-tasks"></i>
									Training <i class="fa arrow"></i>
							</a>
								<ul>
									<li ng-repeat="mn in homeCtrl.menuGroup1" id="{{ mn.action }}"><a
										target="_parent" href="{{'#'+mn.action}}"
										onclick="openOption('mg1')" ng-bind="mn.menuName"></a></li>

								</ul></li>

							<li id="mg2" ng-show="homeCtrl.menuGroup2.length>0"><a
								href="javascript:void(0)"> <i class="fa fa-bar-chart"></i>
									Test <i class="fa arrow"></i>
							</a>
								<ul>

									<li ng-repeat="mn in homeCtrl.menuGroup2" id="{{ mn.action }}"><a
										target="_parent" href="{{'#'+mn.action}}"
										onclick="openOption('mg2')" ng-bind="mn.menuName"></a></li>

								</ul></li>

							<li ng-show="homeCtrl.menuGroup3.length>0" id="mg3"><a
								href="javascript:void(0)"> <i class="fa fa-sliders"></i>
									Settings <i class="fa arrow"></i>
							</a>
								<ul>

									<li ng-repeat="mn in homeCtrl.menuGroup3" id="{{ mn.action }}"><a
										target="_parent" href="{{'#'+mn.action}}"
										onclick="openOption('mg3')" ng-bind="mn.menuName"></a></li>

								</ul></li>
							<li ng-show="homeCtrl.menuGroup5.length>0" id="mg5"><a
								href="javascript:void(0)"> <i class="fa fa-line-chart"></i>
									Report <i class="fa arrow"></i>
							</a>
								<ul>

									<li ng-repeat="mn in homeCtrl.menuGroup5" id="{{ mn.action }}"><a
										target="_parent" href="{{'#'+mn.action}}"
										onclick="openOption('mg5')" ng-bind="mn.menuName"></a></li>

								</ul></li>

							<li id="mg4" id="{{ mn.action }}"
								ng-show="homeCtrl.menuGroup4.length>0"
								onclick="openOption('mg4')"><a target="_parent"
								href="#forum"> <i class="fa fa-forumbee"></i> Forum
							</a> <!-- <ul>

									<li ng-repeat="mn in homeCtrl.menuGroup4" id="{{ mn.action }}"><a
										target="_parent" href="{{'#'+mn.action}}"
										onclick="openOption('home')" ng-bind="mn.menuName"></a></li>

								</ul> --></li>

							<li id="about" ng-show="roleId==null"><a href="#/aboutus"
								onclick="openOption('about')"> <i class="fa fa-users"></i>
									About Us
							</a></li>
							<!-- 							<li id="about" ng-show="roleId==null"><a href="#/aboutus" -->
							<!-- 								onclick="openOption('about')"> <i class="fa fa-share-square-o"></i> -->
							<!-- 									Request For Demo -->
							<!-- 							</a></li> -->
						</ul>
					</nav>
				</div>
				<footer class="sidebar-footer">
					<ul class="nav metismenu" id="customize-menu">
						<li>
							<ul>
								<li class="customize">
									<div class="customize-item">
										<div class="row customize-header">
											<div class="col-xs-4"></div>
											<div class="col-xs-4">
												<label class="title">fixed</label>
											</div>
											<div class="col-xs-4">
												<label class="title">static</label>
											</div>
										</div>
										<div class="row hidden-md-down">
											<div class="col-xs-4">
												<label class="title">Sidebar:</label>
											</div>
											<div class="col-xs-4">
												<label> <input class="radio" type="radio"
													name="sidebarPosition" value="sidebar-fixed"> <span></span>
												</label>
											</div>
											<div class="col-xs-4">
												<label> <input class="radio" type="radio"
													name="sidebarPosition" value=""> <span></span>
												</label>
											</div>
										</div>
										<div class="row">
											<div class="col-xs-4">
												<label class="title">Header:</label>
											</div>
											<div class="col-xs-4">
												<label> <input class="radio" type="radio"
													name="headerPosition" value="header-fixed"> <span></span>
												</label>
											</div>
											<div class="col-xs-4">
												<label> <input class="radio" type="radio"
													name="headerPosition" value=""> <span></span>
												</label>
											</div>
										</div>
										<div class="row">
											<div class="col-xs-4">
												<label class="title">Footer:</label>
											</div>
											<div class="col-xs-4">
												<label> <input class="radio" type="radio"
													name="footerPosition" value="footer-fixed"> <span></span>
												</label>
											</div>
											<div class="col-xs-4">
												<label> <input class="radio" type="radio"
													name="footerPosition" value=""> <span></span>
												</label>
											</div>
										</div>
									</div>
									<div class="customize-item">
										<ul class="customize-colors">
											<li><span class="color-item color-red" data-theme="red"></span>
											</li>
											<li><span class="color-item color-orange"
												data-theme="orange"></span></li>
											<li><span class="color-item color-green"
												data-theme="green"></span></li>
											<li><span class="color-item color-seagreen"
												data-theme="seagreen"></span></li>
											<li><span class="color-item color-blue active"
												data-theme=""></span></li>
											<li><span class="color-item color-purple"
												data-theme="purple"></span></li>
										</ul>
									</div>
								</li>
							</ul> <a href=""> <i class="fa fa-cog"></i> Theme
						</a>
						</li>
					</ul>
				</footer>
			</aside>
			<div class="sidebar-overlay" id="sidebar-overlay"></div>
			<article
				class="content dashboard-page {{roleId==null ? 'landing_page_style' : 'home_page_style'}}">

				<div ng-view></div>




			</article>
			<footer class="footer">
				<div class="footer-block author" align="right" style="width: 60%;">

					<ul>
						<li style="font-size: 14px;"><img ng-src="./images/logo.png"
							err-src="./images/logo.png"
							style="max-width: 100px; height: auto;">

							&nbsp;&nbsp;&nbsp;&nbsp; Copyright © 2016 Pumex Infotech</li>
					</ul>

				</div>
				<div class="footer-block buttons" align="right"
					style="width: 38%; margin-right: 1%;"></div>
			</footer>
			<div class="modal fade" id="modal-media">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title">Media Library</h4>
						</div>
						<div class="modal-body modal-tab-container">
							<ul class="nav nav-tabs modal-tabs" role="tablist">
								<li class="nav-item"><a class="nav-link" href="#gallery"
									data-toggle="tab" role="tab">Gallery</a></li>
								<li class="nav-item"><a class="nav-link active"
									href="#upload" data-toggle="tab" role="tab">Upload</a></li>
							</ul>
							<div class="tab-content modal-tab-content">
								<div class="tab-pane fade" id="gallery" role="tabpanel">
									<div class="images-container">
										<div class="row"></div>
									</div>
								</div>
								<div class="tab-pane fade active in" id="upload" role="tabpanel">
									<div class="upload-container">
										<div id="dropzone">
											<form action="/" method="POST" enctype="multipart/form-data"
												class="dropzone needsclick dz-clickable" id="demo-upload">
												<div class="dz-message-block">
													<div class="dz-message needsclick">Drop files here or
														click to upload.</div>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Close</button>
							<button type="button" class="btn btn-primary">Insert
								Selected</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<div class="modal fade" id="confirm-modal">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title">
								<i class="fa fa-warning"></i> Alert
							</h4>
						</div>
						<div class="modal-body">
							<p>Are you sure want to do this?</p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary"
								data-dismiss="modal">Yes</button>
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">No</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
		</div>
	</div>
	<!-- Reference block for JS -->
	<div class="ref" id="ref">
		<div class="color-primary"></div>
		<div class="chart">
			<div class="color-primary"></div>
			<div class="color-secondary"></div>
		</div>
	</div>
	<script src="js/vendor.js"></script>
	<script src="js/styleloader.js"></script>





	<div style="visibility: hidden">
		<div class="md-dialog-container" id="myStaticLogin">
			<md-dialog> <%@include file="/Pages/login.html"%>
			</md-dialog>
		</div>
	</div>

	<div style="visibility: hidden">
		<div class="md-dialog-container" id="myStaticRegister">
			<md-dialog> <%@include
				file="/Pages/UserManagement/register.html"%>
			</md-dialog>
		</div>
	</div>

	<div style="visibility: hidden">
		<div class="md-dialog-container" id="invalidAccessDialoge">
			<md-dialog> <%@include
				file="/Pages/Warnings/invalidaccess.html"%>
			</md-dialog>
		</div>
	</div>

	<div style="visibility: hidden">
		<div class="md-dialog-container" id="invalidFocusOnTestDialoge">
			<md-dialog> <%@include
				file="/Pages/Warnings/invalidfocusontest.html"%>
			</md-dialog>
		</div>
	</div>

</body>

<script src="js/angular/lib/angular.js"></script>
<script src="js/angular/lib/angular-cookies.js"></script>


<!-- Calendar -->

<script src="js/calendar/moment.min.js"></script>
<script src="js/calendar/interact.min.js"></script>
<script src="js/angular/lib/angular-animate.js"></script>
<script src="js/calendar/ui-bootstrap-tpls.min.js"></script>
<script src="js/calendar/rrule.js"></script>
<script src="js/calendar/bootstrap-colorpicker-module.min.js"></script>
<script src="js/calendar/angular-bootstrap-calendar-tpls.min.js"></script>

<!-- Calendar -->


<link rel="stylesheet" href="css/angucomplete-alt.css">

<script src="js/angular/lib/angucomplete-alt.js"></script>



<script src="js/angular/lib/ng-google-chart.js"></script>

<script src="js/angular/lib/angular-ui-notification.js"></script>

<!-- <script -->
<!-- 	src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.5.5/angular.min.js"></script> -->
<script src="js/angular/lib/angular-route.min.js"></script>
<script src="js/angular/app.js"></script>
<script src="js/angular/common.js"></script>

<script src="js/moment.js"></script>


<script src="js/angular/lib/angular-material.min.js"></script>

<!-- <script src="js/angular/lib/spring-security-csrf-token-interceptor.js"></script> -->

<script src="js/angular/lib/angular.min.js"></script>
<script src="js/angular/lib/angular-animate.min.js"></script>
<script src="js/angular/lib/angular-aria.min.js"></script>
<script src="js/angular/lib/angular-messages.min.js"></script>

<script src="js/angular/lib/ng-tags-input.min.js"></script>

<link rel="stylesheet" href="css/nv.d3.min.css" />
<script src="js/angular/lib/d3.min.js" charset="utf-8"></script>
<script src="js/angular/lib/nv.d3.min.js"></script>
<script src="js/angular/lib/angular-nvd3.min.js"></script>

<script src="js/angular/lib/textAngular.min.js"></script>
<script src="js/angular/lib/textAngular-sanitize.js"></script>
<script src="js/angular/lib/textAngular-rangy.min.js"></script>
<script src="js/angular/lib/textAngular.js"></script>
<script src="js/angular/lib/textAngularSetup.js"></script>
<script src="js/angular/lib/textAngular.umd.js"></script>
<script src="js/angular/lib/angularjs-dropdown-multiselect.js"></script>


<script src="js/angular/lib/angularjs-datetime-picker.js"></script>

<script src="js/angular/lib/checklist-model.js"></script>

<link rel="stylesheet" href="css/jquery.dataTables.min.css" />
<script src="js/angular/lib/datatable/angular-datatables.directive.js"></script>
<script src="js/angular/lib/datatable/angular-datatables.factory.js"></script>
<script src="js/angular/lib/datatable/angular-datatables.instances.js"></script>
<script src="js/angular/lib/datatable/angular-datatables.js"></script>
<script src="js/angular/lib/datatable/angular-datatables.options.js"></script>
<script src="js/angular/lib/datatable/angular-datatables.renderer.js"></script>
<script src="js/angular/lib/datatable/angular-datatables.util.js"></script>
<script src="js/angular/lib/datatable/angular-datatables.min.js"></script>
<script src="js/angular/lib/datatable/jquery.dataTables.min.js"></script>


<!-- Controllers & Services -->

<script src="js/angular/controller/home_controller.js"></script>
<script src="js/angular/service/home_service.js"></script>

<script src="js/angular/controller/test_controller.js"></script>
<script src="js/angular/service/test_service.js"></script>

<script src="js/angular/controller/register_controller.js"></script>
<script src="js/angular/service/register_service.js"></script>

<script src="js/angular/controller/login_controller.js"></script>
<script src="js/angular/service/login_service.js"></script>

<script src="js/angular/controller/user_controller.js"></script>
<script src="js/angular/service/user_service.js"></script>

<script src="js/angular/controller/training_controller.js"></script>
<script src="js/angular/service/training_service.js"></script>

<script src="js/angular/controller/manage_test_controller.js"></script>
<script src="js/angular/service/manage_test_service.js"></script>

<script src="js/angular/controller/prepare_questions_controller.js"></script>
<script src="js/angular/service/prepare_questions_service.js"></script>

<script src="js/angular/controller/profileupdate_controller.js"></script>
<script src="js/angular/service/profileupdate_service.js"></script>

<script src="js/angular/controller/changepassword_controller.js"></script>
<script src="js/angular/service/changepassword_service.js"></script>

<script src="js/angular/controller/resetpassword_controller.js"></script>
<script src="js/angular/service/resetpassword_service.js"></script>

<script src="js/angular/controller/attendance_controller.js"></script>
<script src="js/angular/service/attendance_service.js"></script>

<script src="js/angular/controller/testresulttrainer_controller.js"></script>
<script src="js/angular/service/test_result_trainer_service.js"></script>

<script src="js/angular/controller/training_schedule_controller.js"></script>
<script src="js/angular/service/training_schedule_service.js"></script>

<script src="js/angular/controller/feedback_controller.js"></script>
<script src="js/angular/service/feedback_service.js"></script>

<script src="js/angular/controller/mytraininglist_trainer_controller.js"></script>
<script src="js/angular/service/mytraininglist_trainer_service.js"></script>

<script src="js/angular/controller/upcoming_training_controller.js"></script>
<script src="js/angular/service/upcoming_training_service.js"></script>

<script
	src="js/angular/controller/add_interested_attendee_controller.js"></script>
<script src="js/angular/service/add_interested_attendee_service.js"></script>

<script src="js/angular/controller/admin_feedback_view_controller.js"></script>
<script src="js/angular/service/admin_feedback_view_service.js"></script>

<script src="js/angular/controller/training_material_controller.js"></script>
<script src="js/angular/service/training_material_service.js"></script>

<script src="js/angular/controller/role_option_controller.js"></script>
<script src="js/angular/service/role_option_service.js"></script>

<script src="js/angular/controller/forum_controller.js"></script>
<script src="js/angular/service/forum_service.js"></script>

<script src="js/angular/controller/company_profile_controller.js"></script>
<script src="js/angular/service/company_profile_service.js"></script>

<script src="js/angular/controller/user_activation_controller.js"></script>
<script src="js/angular/service/user_activation_service.js"></script>

<script src="js/angular/controller/department_controller.js"></script>
<script src="js/angular/service/department_service.js"></script>

<script src="js/angular/controller/performance_analysis_controller.js"></script>
<script src="js/angular/service/performance_analysis_service.js"></script>

<script src="js/calendar/helpers.js"></script>
</html>