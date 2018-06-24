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
	
	var opsturen1 = document.querySelector("#productnaam2")
	opsturen1.value=product_naam;
	var opsturen2 = document.querySelector("#product_prijs2")
	opsturen2.value=product_prijs;

	//Aan de button toevoegen1 wordt een event listener toegevoegd.
	var button = document.querySelector("#toevoegen1")
	button.addEventListener("click", function(){
		
		var formData = new FormData(document.querySelector("#winkelwagen"))
		var encData = new URLSearchParams(formData.entries());
		//Hier stuur ik een POST request om het product wat zojuist is gekozen toe te voegen aan de winkelwagen.
		fetch("https://ipassluuk.herokuapp.com/restservices/producten/post_winkelwagen", {method: "POST", body: encData})
		//http://localhost:4471/app/restservices/producten/post_winkelwagen
			.then(function(response){
				return response.json();
			})
			.then(function(myJson){
				alert("Product is toegevoegd aan de winkelwagen")
			});
	});

}

function add_opmerking(){
	var reactie = document.querySelector("#opmerking_plaatsen2")
	reactie.value = localStorage.getItem('ID')
	//Aan de button opmerking_plaatsen wordt een event listener toegevoegd.
	var button = document.querySelector("#opmerking_plaatsen")
	button.addEventListener("click", function(){
		var reactie = document.querySelector("#opmerking_plaatsen1");
		//Checkt of er wel wat in staat bij het veld van opmerking
		if(reactie.value == ""){
			alert("U moet wel iets neerzetten bij de opmerking!")
		}
		else{
    	var formData = new FormData(document.querySelector("#reactie"))
		var encData = new URLSearchParams(formData.entries());
    	//Hier stuur ik een POST request om de opmerking toe te voegen aan de database.
		fetch("https://ipassluuk.herokuapp.com/restservices/producten", {method: "PUT", body: encData})
		//http://localhost:4471/app/restservices/producten
			.then(function(response){
				return response.json();
			})
			.then(function(myJson){
				//als de reactie is toegevoegd aan de database moet hij ook op scherm staan dat gebeurt door een element p aan te maken en die toe te voegen aan een DIV
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
	//Hier haal ik met een GET request alle opmerkingen op uit de database.
	fetch("https://ipassluuk.herokuapp.com/restservices/producten/opmerkingen")
	//http://localhost:4471/app/restservices/producten/opmerkingen 
	.then(function(response){
		return response.json();
		
	})
	.then(function(myJson){
		
		for (const opmerking of myJson){
			var array =[]
			array.push(opmerking.ID)
			//Alle product id's zitten nu in een array
			for(const id of array){
				//Voor elke opmerking kijkt hij bij welk product de opmerking hoort.
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
				window.sessionStorage.removeItem("myJWT")
				var url ="home.html";
	        	document.location.href = url;
			
		}
		else{
			alert("U bent niet uitgelogd!")
		}
	});
}


