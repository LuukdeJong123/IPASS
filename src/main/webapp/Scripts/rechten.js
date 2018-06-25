function getToken(){
    token = window.sessionStorage.getItem("myJWT");
    //Als je niet bent ingelogd dan moet je veel kunnen zien maar niet alles en dat wordt hiet bepaald
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
	//
    var jwt = token.split('.')[1];;
    var jwt_gesplit = jwt.replace('-', '+').replace('_', '/');
    var jwt2 = JSON.parse(window.atob(jwt_gesplit));
    //Niet alles moet zichtbaar zijn voor user
	if (jwt2.role == 'user') {
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
	//Alles moet zichtbaar zijn voor admin
	} else if(jwt2.role == 'admin') {
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