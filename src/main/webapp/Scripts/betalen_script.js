function betalen(){
	//Hier stuur ik een GET request om de alle items uit de winkelwagen in de database te halen.
	fetch("http://localhost:4471/app/restservices/producten/winkelwagen")
	.then(function(response){
		return response.json();		
	})
	.then(function(myJson){
		//Hier pak ik de date van vandaag
		//Dan pak ik het jaar, maand, dag, uren, minuten en seconden en zet ik die in aparte input en die stuur ik dan op naar bestelling
		var d = new Date();	
		var datum= d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
		var tijd= (d.getHours())+":"+(d.getMinutes())+":"+(d.getSeconds())
		var input = document.querySelector("#datum")
		input.value=datum;
		var input2 = document.querySelector("#tijd")
		input2.value = tijd;
		
		var array2 = [];
		for(const winkelwagen of myJson){
			
			var table = document.querySelector("#table3");
			var row = table.insertRow(1);
			var cell1 = row.insertCell(0);
			var cell2 = row.insertCell(1);
			
			cell1.innerHTML = winkelwagen.winkelwagen_naam;
            cell2.innerHTML = winkelwagen.winkelwagen_prijs +" $ ";
            getAantal()
        	var button = document.querySelector("#betalen")
        	button.addEventListener("click", function(){
        			array2.push(winkelwagen.winkelwagen_id)
        			for(const id of array2){
        				//Hier gebruik ik een DELETE request om een item uit de database te verwijderen.
        				fetch("http://localhost:4471/app/restservices/producten/"+ id, {method: 'DELETE' })
                        .then(function (response) { 
                            if (response.ok){
                                
                            	}
                            else {
                            	alert("De producten zijn niet betaald er is iets misgegaan");
                            }
                        })
                       
        			}      			
        		
        	});
        	
		}
		 
	});

}
//Deze functie voegt alle prijzen in de winkelwagen bij elkaar
function getAantal(){
	var table = document.querySelector("#table3");
	var prijs = 0
	//voor elke prijs in de tabel en doet hij die bij elkaar
    for(var i = 1; i < table.rows.length; i++)
    {
    	prijs = prijs + parseFloat(table.rows[i].cells[1].innerHTML);
    	//Hier rond ik het getal nog 
    	var nieuwe_prijs= Math.round(prijs * 100) / 100
    }
	//De totale prijs wordt hier toegevoegd aan de innerHTML
    document.querySelector("#totale_prijs1").innerHTML = "Totale prijs: " + nieuwe_prijs;
   
}
function post_bestelling(){
	var button = document.querySelector("#betalen")
	button.addEventListener("click", function(){
	var id= document.querySelector("#id")
	id.value= localStorage.getItem('klant_ID');
	var formData = new FormData(document.querySelector("#bestelling"))
	var encData = new URLSearchParams(formData.entries());
	//Hier stuur ik een POST request om de bestelling toe te voegen aan de database.
	fetch("http://localhost:4471/app/restservices/producten/post_bestelling", {method: "POST", body: encData})
	.then(function(response){
		//Hier wordt je terug gewezen naar de home pagina
		alert("Bestelling is geplaats")
		var url ="home.html";
		document.location.href = url;
		return response.json();
	})
	.then(function(myJson){
		
		});
	});
}