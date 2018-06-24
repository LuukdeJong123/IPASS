function uitloggen(){
	//De uitlog functie staat nu natuurlijk in elk .JS bestand dus die comments ga ik daar niet herhalen.
	//Aan de button uitloggen wordt een event listener toegevoegd.
	var button = document.querySelector("#uitloggen");
	button.addEventListener('click', function(){
		//De event listener vraagt of u zeker weten wilt uitloggen en daarna wordt er gekeken of er wel is ingelogd.
		if(confirm("Weet u zeker dat u wilt uitloggen?")){
			if(window.sessionStorage.getItem("myJWT") === null){
				alert("U bent nog niet ingelogd of uw sessie is verlopen")
			}
			else{
				//Dan als er wordt uitgelogd dan wordt de JWT token verwijderd en is de user uitgelogd.
				window.sessionStorage.removeItem("myJWT")
				var url ="home.html";
	        	document.location.href = url;
			}
		}
		else{
			alert("U bent niet uitgelogd!")
		}
	});
}