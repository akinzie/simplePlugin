var exec = require("cordova/exec");

module.exports = {
	set:function(key, value, successCB, errorCB) {
		exec(successCB, errorCB, "simple", "set", [key, value]);
	},
	get:function(key, successCB, errorCB) {
		exec(successCB, errorCB, "simple", "get", [key]);
	},
	getOnMainThread:function(key, successCB, errorCB) {
		exec(successCB, errorCB, "simple", "getOnMainThread", [key]);
	}
};
