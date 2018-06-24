function showProducten(){
	fetch("http://localhost:4471/app/restservices/producten")
	.then(function(response){
		return response.json();
		
		
	})
	.then(function(myJson){

		for(const product of myJson){
			var table = document.querySelector("table");
			var row = table.insertRow(1);
			var cell1 = row.insertCell(0);
			var cell2 = row.insertCell(1);
			var cell3 = row.insertCell(2);
			cell1.innerHTML = product.Naam;
            cell2.innerHTML = product.Prijs +" $ ";
            cell3.innerHTML = product.Alcoholpercentage +"%";
            
            var btn = document.createElement('input');
			btn.type = "button";
			btn.className = "btn";
			btn.value="Toevoegen";
			row.appendChild(btn);
			
			var opsturen1 = document.querySelector("#productnaam2")
			opsturen1.value=product.Naam;
			var opsturen2 = document.querySelector("#product_prijs2")
			opsturen2.value=product.Prijs;
			
			var button = document.querySelector(".btn")
			button.addEventListener("click", function(){
				var opsturen1 = document.querySelector("#productnaam2")
				opsturen1.value=product.Naam;
				var opsturen2 = document.querySelector("#product_prijs2")
				opsturen2.value=product.Prijs;
				
				var formData = new FormData(document.querySelector("#winkelwagen"))
				var encData = new URLSearchParams(formData.entries());
				
				fetch("http://localhost:4471/app/restservices/producten/post_winkelwagen", {method: "POST", body: encData})
					.then(function(response){
						return response.json();
					})
					.then(function(myJson){
						alert("Product is toegevoegd aan de winkelwagen")
					});

			});
			row.addEventListener("click", function(e){
				if(e.path[0].value ==="Toevoegen"){
					return;
				}
				else{
					localStorage.setItem('ID', product.ID);
					localStorage.setItem('product_naam', product.Naam);
					localStorage.setItem('product_smaak', product.Smaak);
					localStorage.setItem('product_alcoholpercentage', product.Alcoholpercentage);
					localStorage.setItem('product_prijs', product.Prijs);
					var url ="product.html";
	            	document.location.href = url;
				}
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