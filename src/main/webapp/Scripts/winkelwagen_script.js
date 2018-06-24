function get_winkelwagen(){
	//Hier haal ik met een GET request alle winkelwagen items op uit de database.
	fetch("http://localhost:4471/app/restservices/producten/winkelwagen")
	
	.then(function(response){
		return response.json();		
	})
	.then(function(myJson){

		var array = [];
		var array2 = [];
		for(const winkelwagen of myJson){
			//Voor elke item in de winkelwagen zet ik die in de tabel
			var table = document.querySelector("#table1");
			var row = table.insertRow(1);
			var cell1 = row.insertCell(0);
			var cell2 = row.insertCell(1);
			
			cell1.innerHTML = winkelwagen.winkelwagen_naam;
            cell2.innerHTML = winkelwagen.winkelwagen_prijs +" $ ";
            //Ik voeg bij elke rij ook een button toe want die moet je hebben zodat er producten uit de winkelwagen verwijderd kunnen worden.
            var btn = document.createElement('input');
			btn.type = "button";
			btn.className = "btn3";
			btn.value="Verwijderen";
			row.appendChild(btn);
			
			var button = document.querySelector(".btn3")
			button.addEventListener("click", function(){
				var code = winkelwagen.winkelwagen_id;
				//Hier gebruik ik een DELETE request om een item uit de database te verwijderen.
				fetch("http://localhost:4471/app/restservices/producten/"+ code, {method: 'DELETE' })

                .then(function (response) { 
                    if (response.ok){
                    	//Als het is gelukt laad ik de pagina opnieuw 
                    	var url ="winkelwagen.html";
    	            	document.location.href = url;
                        alert(winkelwagen.winkelwagen_naam + "  is verwijderd");
                    	}
                    else {
                    	alert(winkelwagen.winkelwagen_naam + "  kan niet worden verwijderd");
                    }
                })

				
			});
	


			
			var button = document.querySelector("#bestellen1");
			button.addEventListener("click", function(){
		    	var url ="betalen.html";
		    	document.location.href = url;
			});
			// Ik roep hier de functie aan zodat alle prijzen bij elkaar worden berekend
			getAantal();
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
//Deze functie voegt alle prijzen in de winkelwagen bij elkaar
function getAantal(){
	var table = document.querySelector("#table1");
	var prijs = 0
	//voor elke prijs in de tabel en doet hij die bij elkaar
    for(var i = 1; i < table.rows.length; i++)
    {
    	prijs = prijs + parseFloat(table.rows[i].cells[1].innerHTML);
    	var nieuwe_prijs= Math.round(prijs * 100) / 100
    
    	


    	
    }
	//De totale prijs wordt hier toegevoegd aan de innerHTML
    document.querySelector("#totale_prijs").innerHTML = nieuwe_prijs;
   
}