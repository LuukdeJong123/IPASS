function getToken(){
    token = window.sessionStorage.getItem("myJWT");
	if (token == null) {
		console.log('niet ingelogd');
		var list = document.querySelectorAll(".visibleGuest");
		for (var i = list.length; i--;) {
		    list[i].className = list[i].className + ' show';
		}

		
		var list3 = document.querySelectorAll(".visibleAdmin");
		for (var i = list3.length; i--;) {
		    list3[i].className = list3[i].className + ' hide';
		}
		
		var list4 = document.querySelectorAll(".visibleLogin");
		for (var i = list4.length; i--;) {
		    list4[i].className = list4[i].className + ' hide';
		}
		
	} else {
		parseJwt(token);
	}
}

function parseJwt (token) {
    var base64Url = token.split('.')[1];;
    var base64 = base64Url.replace('-', '+').replace('_', '/');
    var base = JSON.parse(window.atob(base64));
	if (base.role == 'user') {
		console.log('ingelogd als klant');
		var list = document.querySelectorAll(".visibleGuest");
		for (var i = list.length; i--;) {
		    list[i].className = list[i].className + ' hide';
		}
		
		var list2 = document.querySelectorAll(".visibleCustomer");
		for (var i = list2.length; i--;) {
		    list2[i].className = list2[i].className + ' show';
		}
		
		var list3 = document.querySelectorAll(".visibleAdmin");
		for (var i = list3.length; i--;) {
		    list3[i].className = list3[i].className + ' hide';
		}
		
		var list4 = document.querySelectorAll(".visibleLogin");
		for (var i = list4.length; i--;) {
		    list4[i].className = list4[i].className + ' show';
		}
		
	} else if(base.role == 'admin') {
		console.log('ingelogd als medewerker');
		
		var list = document.querySelectorAll(".visibleGuest");
		for (var i = list.length; i--;) {
		    list[i].className = list[i].className + ' hide';
		}
		
		var list2 = document.querySelectorAll(".visibleCustomer");
		for (var i = list2.length; i--;) {
		    list2[i].className = list2[i].className + ' hide';
		}
		
		var list3 = document.querySelectorAll(".visibleAdmin");
		for (var i = list3.length; i--;) {
		    list3[i].className = list3[i].className + ' show';
		}
		
		var list4 = document.querySelectorAll(".visibleLogin");
		for (var i = list4.length; i--;) {
		    list4[i].className = list4[i].className + ' show';
		}

	} else {
		console.log('hier hoor je niet te komen');
	}
}