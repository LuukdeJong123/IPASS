function get_winkelwagen(){
	fetch("http://localhost:4471/app/restservices/producten/winkelwagen")
	.then(function(response){
		return response.json();
		
		
	})
	.then(function(myJson){
		for(const winkelwagen of myJson){
			console.log(winkelwagen);
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
				console.log(winkelwagen.winkelwagen_naam)

				
			});
		}
	});
}