function login(){
	//Aan de button inloggen wordt een event listener toegevoegd.
	var button = document.getElementById("inloggen");
	button.addEventListener('click', function(){
		var formData = new FormData(document.querySelector("#inlog3"))
		var encData = new URLSearchParams(formData.entries());
		//Hier stuur ik een POST request om te kijken of de gebruikersnaam en wachtwoord kloppen met wat in de database staat.
		   fetch("http://localhost:4471/app/restservices/authentication", { method: 'POST', body: encData})
	        .then(function (response) {
	            if (response.ok){
	            	//Nu wordt je dan door deze URL ook meteen doorgewijzigd naar de Home pagina
	            	var url ="home.html";
	            	document.location.href = url;
	                return response.json();
	            }
	            else {
	            	// Als de gegevens niet kloppen dan wordt er een foutmelding gegeven.
	            	alert("Er iets fout gegaan! Check je wachtwoord!");
	            }
	        })
	        .then(myJson => window.sessionStorage.setItem("myJWT", myJson.JWT))
	        	.catch(error => console.log(error));

		   //Hier stuur ik een GET request om de klant_ID optevragen want dan weet je als wie je ingelogd ben.
			fetch("http://localhost:4471/app/restservices/producten/user")
			.then(function(response){
				return response.json();
			})
			.then(function(myJson){
				for(const klant of myJson){
					var inlog=  document.getElementById("username");
					//Als de opgegven naam klopt met de naam die in database staat wordt het klanten ID in de localstorage gezet.
					if(inlog.value == klant.user_naam){
						localStorage.setItem('klant_ID', klant.user_id)
					}
				}
				
			});
	});
				
	
}
function registreren(){
	//Aan de button registeren wordt een event listener toegevoegd.
	var button = document.getElementById("registreren");
	button.addEventListener('click', function(){
		// De event listerner checkt of alles is ingevuld want als dat niet is wordt er een slechte PUT request gestuurd.
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
			//Hier stuur ik een PUT request om te kijken of de gebruikersnaam en wachtwoord kloppen met wat in de database staat.
			   fetch("http://localhost:4471/app/restservices/authentication", { method: 'PUT', body: encData,  headers: {'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")}})
		        .then(function (response) {
		            if (response.ok){
		            	///Nu wordt je dan door deze URL ook meteen doorgewijzigd naar de inlog pagina pagina
		            	var url ="main.html";
		            	document.location.href = url;
		                return response.json();
		            }
		            else throw "wrong username/password";
				});
		}
		
	});
}


