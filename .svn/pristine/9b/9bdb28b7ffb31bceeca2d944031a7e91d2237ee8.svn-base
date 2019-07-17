'use strict';

var last
var triedOnce = false;
function blockBlur($rootScope) {
	$(window)
			.blur(
					function() {
						if (triedOnce) {
							window.location.href = "./";
							unBlockBlur();
						} else {
							triedOnce = true;
							$rootScope.showInvalidFocusWarning();
						}
					});
}

function unBlockBlur() {
	triedOnce = false;
	$(window).off('blur');
}

var oldId = "home";
function openOption(componentId) {
	$("#" + oldId).removeClass("active");
	oldId = componentId;
	$("#" + componentId).addClass("active");
}

function secondsToTime(seconds) {

	var date = new Date(seconds * 1000);
	var hh = date.getUTCHours();
	var mm = date.getUTCMinutes();
	var ss = date.getSeconds();

	if (hh <= 0) {
		hh = "";
	} else if (hh < 10) {
		hh = "0" + hh + ":";
	}

	if (mm < 10) {
		mm = "0" + mm;
	}
	if (ss < 10) {
		ss = "0" + ss;
	}
	// This formats your string to HH:MM:SS
	return hh + mm + ":" + ss;
}