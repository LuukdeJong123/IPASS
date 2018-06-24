function betalen(){
	fetch("http://localhost:4471/app/restservices/producten/winkelwagen")
	.then(function(response){
		return response.json();		
	})
	.then(function(myJson){
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
        		var pc = document.getElementById("postcode");
        		var st = document.getElementById("straat");
        		var hn = document.getElementById("huisnummer");
        		var tv = document.getElementById("toevoegingen");
        		if (pc.value === ""){
        			alert("Postocde is nog leeg gelaten!");		
        		}else if (st.value === ""){
        			alert("Straat is nog leeg gelaten!");				
        		}else if (hn.value === ""){
        			alert("Huisnummer is nog leeg gelaten!");				
        		}else if (tv.value === ""){
        			alert("Toevoegingen is nog leeg gelaten!");
        		}
        		else{
        			array2.push(winkelwagen.winkelwagen_id)
        			for(const id of array2){
        				fetch("http://localhost:4471/app/restservices/producten/"+ id, {method: 'DELETE' })
        				//, headers: {'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")}
                        .then(function (response) { 
                            if (response.ok){
                                
                            	}
                            else {
                            	alert("De producten zijn niet betaald er is iets misgegaan");
                            }
                        })
                       
        			}
        			var id= document.querySelector("#id")
        			id.value= localStorage.getItem('klant_ID');
        			var formData = new FormData(document.querySelector("#bestelling"))
        			var encData = new URLSearchParams(formData.entries());
        			
        			fetch("http://localhost:4471/app/restservices/producten/post_bestelling", {method: "POST", body: encData})
        			.then(function(response){
        				alert("Bestelling is geplaats")
        				var url ="home.html";
        				document.location.href = url;
        				return response.json();
        			})
        			.then(function(myJson){
        				
        			});
        			

        		}
        		
        	});
        	
		}
		 
	});

}
function getAantal(){
	var table = document.querySelector("#table3");
	var prijs = 0
    for(var i = 1; i < table.rows.length; i++)
    {
    	prijs = prijs + parseFloat(table.rows[i].cells[1].innerHTML);

    	
    }
    document.querySelector("#totale_prijs1").innerHTML = "Totale prijs: " + prijs;
   
}