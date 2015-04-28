$(document).ready(function () {

	$.validator.addMethod(
			"regex",
			function(value, element, regexp) {
				var re = new RegExp(regexp);
				return this.optional(element) || re.test(value);
			},
			"Invalid format."
	);

	$( "#addComputer" ).validate({
		onkeyup : false,
		onfocusout : function(element) { $(element).valid(); },
		errorElement : "div",
		errorPlacement : function(error, element) {
			if (element.attr("name") == "name") {
				error.appendTo("div#errorName");
			} else if (element.attr("name") == "intro") {
				error.appendTo("div#errorIntro");
			} else if (element.attr("name") == "disco") {
				error.appendTo("div#errorDisco");
			}
		}, 
		rules: {
			name: {
				required: true,
				minlength : 2
			},

			intro: {
				regex : "^\\d{4}-(0[1-9]|1[012])-((0[1-9]|[12][0-9]|3[01]) (20|21|22|23|[01]\\d|\\d)(([:][0-5]\\d){1,2})(([:][0-5]\\d){1,2}))$"
			},

			disco: {
				regex : "^\\d{4}-(0[1-9]|1[012])-((0[1-9]|[12][0-9]|3[01]) (20|21|22|23|[01]\\d|\\d)(([:][0-5]\\d){1,2})(([:][0-5]\\d){1,2}))$"
			}
		},
		messages : {
			name: {
				required : "The name is required",
				minlength : "Minimum length is 2"
			},

			intro: {
				regex : "bad Format (yyyy-MM-dd HH:mm:ss)"
			},

			disco: {
				regex : "bad Format (yyyy-MM-dd HH:mm:ss)"
			}
		}
	});

	$( "#editComputer" ).validate({
		onkeyup : false,
		onfocusout : function(element) { $(element).valid(); },
		errorElement : "div",
		errorPlacement : function(error, element) {
			if (element.attr("name") == "name") {
				error.appendTo("div#errorName");
			} else if (element.attr("name") == "intro") {
				error.appendTo("div#errorIntro");
			} else if (element.attr("name") == "disco") {
				error.appendTo("div#errorDisco");
			}
		}, 
		rules: {
			name: {
				minlength : 2
			},

			intro: {
				regex : "^\\d{4}-(0[1-9]|1[012])-((0[1-9]|[12][0-9]|3[01]) (20|21|22|23|[01]\\d|\\d)(([:][0-5]\\d){1,2})(([:][0-5]\\d){1,2}))$"
			},

			disco: {
				regex : "^\\d{4}-(0[1-9]|1[012])-((0[1-9]|[12][0-9]|3[01]) (20|21|22|23|[01]\\d|\\d)(([:][0-5]\\d){1,2})(([:][0-5]\\d){1,2}))$"
			}
		},
		messages : {
			name: {
				minlength : "Minimum length is 2"
			},

			intro: {
				regex : "bad Format (yyyy-MM-dd HH:mm:ss)"
			},

			disco: {
				regex : "bad Format (yyyy-MM-dd HH:mm:ss)"
			}
		}
	});
	
	$(function () {
		$('.flag').removeClass("selected");
		if (strings['choosenLang'] == 'fr') {
			$("#dfr").addClass("selected");
		} else if (strings['choosenLang'] == 'en') {
			$("#den").addClass("selected");
		}
	});
})
