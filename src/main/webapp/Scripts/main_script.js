function login(){
	
	var button = document.getElementById("inloggen");
	button.addEventListener('click', function(){
		var formData = new FormData(document.querySelector("#inlog3"))
		var encData = new URLSearchParams(formData.entries());
		
		   fetch("http://localhost:4471/app/restservices/authentication", { method: 'POST', body: encData})
	        .then(function (response) {
	            if (response.ok){
	            	var url ="home.html";
	            	document.location.href = url;
	                return response.json();
	            }
	            else {
	            	alert("Er iets fout gegaan! Check je wachtwoord!");
	            }
	        })
	        .then(myJson => window.sessionStorage.setItem("myJWT", myJson.JWT))
	        	.catch(error => console.log(error));

	
			fetch("http://localhost:4471/app/restservices/producten/user")
			.then(function(response){
				return response.json();
			})
			.then(function(myJson){
				for(const klant of myJson){
					var inlog=  document.getElementById("username");
					if(inlog.value == klant.user_naam){
						localStorage.setItem('klant_ID', klant.user_id)
					}
				}
				
			});
	});
				
	
}
function registreren(){
	
	var button = document.getElementById("registreren");
	button.addEventListener('click', function(){
		var vm = document.getElementById("voornaam");
		var am = document.getElementById("achternaam");
		var gm = document.getElementById("gebruikersnaam");
		var ww = document.getElementById("wachtwoord");
		if (vm.value === ""){
			alert("Voornaam is nog leeg gelaten!");		
		}else if (am.value === ""){
			alert("Achternaam is nog leeg gelaten!");				
		}else if (gm.value === ""){
			alert("Gebruikersnaam is nog leeg gelaten!");				
		}else if (ww.value === ""){
			alert("Wachtwoord is nog leeg gelaten!");

		}else{
			var formData = new FormData(document.querySelector("#registreer2"))
			var encData = new URLSearchParams(formData.entries());
			
			   fetch("http://localhost:4471/app/restservices/authentication", { method: 'PUT', body: encData,  headers: {'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")}})
		        .then(function (response) {
		            if (response.ok){
		            	console.log("account aangemaakt");
		            	var url ="main.html";
		            	document.location.href = url;
		                return response.json();
		            }
		            else throw "wrong username/password";
				});
		}
		
	});
}


