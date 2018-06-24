function get_winkelwagen(){
	fetch("http://localhost:4471/app/restservices/producten/winkelwagen")
	.then(function(response){
		return response.json();		
	})
	.then(function(myJson){

		var array = [];
		var array2 = [];
		for(const winkelwagen of myJson){
			
			var table = document.querySelector("#table1");
			var row = table.insertRow(1);
			var cell1 = row.insertCell(0);
			var cell2 = row.insertCell(1);
			
			cell1.innerHTML = winkelwagen.winkelwagen_naam;
            cell2.innerHTML = winkelwagen.winkelwagen_prijs +" $ ";
            
            var btn = document.createElement('input');
			btn.type = "button";
			btn.className = "btn3";
			btn.value="Verwijderen";
			row.appendChild(btn);
			
			var button = document.querySelector(".btn3")
			button.addEventListener("click", function(){
				var code = winkelwagen.winkelwagen_id;
				fetch("http://localhost:4471/app/restservices/producten/"+ code, {method: 'DELETE' })
				//, headers: {'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")}
                .then(function (response) { 
                    if (response.ok){
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

function getAantal(){
	var table = document.querySelector("#table1");
	var prijs = 0

    for(var i = 1; i < table.rows.length; i++)
    {
    	prijs = prijs + parseFloat(table.rows[i].cells[1].innerHTML);

    	
    }
    document.querySelector("#totale_prijs").innerHTML = prijs;
   
}