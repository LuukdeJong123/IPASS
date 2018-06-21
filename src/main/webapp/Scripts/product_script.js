function get_product(){
	//Hier haalt hij het product op uit de localstorage die is geselecteerd in de producten pagina
	var product_naam = localStorage.getItem('product_naam')
	var dingen = document.querySelector("#productnaam")
	dingen.innerHTML = "Naam: " +product_naam;
	
	var product_smaak = localStorage.getItem('product_smaak')
	var smaak = document.querySelector("#productsmaak")
	smaak.innerHTML = "Smaak: "+product_smaak;
	
	var product_prijs = localStorage.getItem('product_prijs')
	var prijs = document.querySelector("#product_prijs")
	prijs.innerHTML = "Prijs: "+ product_prijs +" $";
	
	var product_alcoholpercentage = localStorage.getItem('product_alcoholpercentage')
	var percentage = document.querySelector("#product_alcoholpercentage")
	percentage.innerHTML = "Alcoholpercentage: "+ product_alcoholpercentage +"%";
	
	var button = document.querySelector("#toevoegen1")
	button.addEventListener("click", function(){
		
	
	});

}

function add_opmerking(){
	var reactie = document.querySelector("#opmerking_plaatsen2")
	reactie.value = localStorage.getItem('ID')
	var button = document.querySelector("#opmerking_plaatsen")
	button.addEventListener("click", function(){
		var reactie = document.querySelector("#opmerking_plaatsen1");
		if(reactie.innerHTML == ""){
			alert("U moet wel iets neerzetten bij de opmerking!")
		}
		else{
    	var formData = new FormData(document.querySelector("#reactie"))
		var encData = new URLSearchParams(formData.entries());
		
		fetch("http://localhost:4471/app/restservices/producten", {method: "PUT", body: encData})
			.then(function(response){
				return response.json();
			})
			.then(function(myJson){
				alert("Reactie is toegevoegd")
				var reactie = document.querySelector("#opmerking_plaatsen1");
				var p = document.createElement('p');
				var node = document.createTextNode("Opmerking: "+reactie.value);
				p.appendChild(node);
				var element = document.querySelector("#opmerkingen");
				element.appendChild(p)
			});		
		}
	});
}
function load_opmerking(){
	fetch("http://localhost:4471/app/restservices/producten/opmerkingen")
	.then(function(response){
		return response.json();
		
	})
	.then(function(myJson){
		for (const opmerking of myJson){
			var array =[]
			array.push(opmerking.ID)
			
			for(const id of array){
				if (id == localStorage.getItem('ID') ){
					var p = document.createElement('p');
					var node = document.createTextNode("Opmerking: "+opmerking.Opmerking);
					p.appendChild(node);
					var element = document.querySelector("#opmerkingen");
					element.appendChild(p)
				}
			}	
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

function add_winkelwagen(){
//	var button = document.querySelector("#toevoegen1");
//	button.addEventListener('click', function(){
//	
//	var formData = new FormData(document.querySelector("#winkelwagen"))
//	var encData = new URLSearchParams(formData.entries());
//	
//	fetch("http://localhost:4471/app/restservices/producten", {method: "PUT", body: encData})
//		.then(function(response){
//			return response.json();
//		})
//		.then(function(myJson){
//			alert("werkt")
//		});
//	});
}
