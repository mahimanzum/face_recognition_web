

function activateMenu(){
	for (var i = 0; i < arguments.length; i++) {
		if (typeof arguments[i] !== 'undefined' && arguments[i].trim()) {
			$("#" + arguments[i].trim()).addClass("start active open");
	    }
	  }
}


