function add_product(){
    var button3 = document.querySelector("#toevoegen2");
    button3.addEventListener('click', function () {
    	
    	var vm = document.getElementById("input");
		var am = document.getElementById("input2");
		var gm = document.getElementById("input3");
		var ww = document.getElementById("input4");
		//Deze if statements checken of de velden wel zijn gevuld zodat ze niet lege data opsturen naar de database 
		if (vm.value === ""){
			alert("Naam is nog leeg gelaten!");		
		}else if (am.value === ""){
			alert("Prijs is nog leeg gelaten!");				
		}else if (gm.value === ""){
			alert("Smaak is nog leeg gelaten!");				
		}else if (ww.value === ""){
			alert("Alcoholpercentage is nog leeg gelaten!");
			
		}else{
	    	var formData = new FormData(document.querySelector("#toevoegen3"))
			var encData = new URLSearchParams(formData.entries());
			
			fetch("http://localhost:4471/app/restservices/producten", {method: "POST", body: encData})
				.then(function(response){
					return response.json();
				})
				.then(function(myJson){
					alert("Product is toegevoegd aan de database")
				});
		}

    });
}
function uitloggen(){
	var button = document.querySelector("#uitloggen");
	button.addEventListener('click', function(){
		if(confirm("Weet u zeker dat u wilt uitloggen?")){
			if(window.sessionStorage.getItem("myJWT") === null){
				alert("U bent nog niet ingelogd of uw sessie is verlopen")
			}
			else{
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