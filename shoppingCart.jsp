<html>
<head>
<title>Your Cart</title>
<style>
	#test{
		color: #C70039;
		font-size: 36px;
		background-color: beige;
		padding: 0.5%;
	}
	body{
		background-image: url("background.jpg");
		background-repeat: no-repeat;
		background-size: 100% 100%;
	}
	#message{
		background-color: #C70039;
		font-size: 28px;
		color: beige;
		float: right;
		padding: 0.5%;
	}
	.qty{
		width: 20px;
	}
	h1{
		text-align: center;
		color: #C70039;
		font-size: 48px;
		background-color: black;
	}
	#cart{
		color: #C70039;
		background-color: beige;
		width: 45%;
		position: relative;
		left: 28%;
		padding-top: 1%;
		padding-bottom: 2%;
	}
	#total{
		background-color: black;
		color: white;
		font-size: 30px;
	}
	.price{
		position: relative;
		bottom: 18px;
		left: 46px;
	}
	a{
		font-size: 24px;
	}
	a:hover{
		text-decoration: underline;
		cursor: pointer;
	}
</style>
</head>
<body>
<div id="test"><b>Madras Cafe</b></div><hr>
<a onclick="window.history.back()">Continue Shopping</a>
<h1 align="center">Your Cart</h1>
<div id="cart"></div>
</body>
<script>
document.getElementById("test").innerHTML += "<span id=\"message\">Hello ${param['name']}</span>";

var request = new XMLHttpRequest();
	request.onreadystatechange=function() {
		if (request.readyState==4 ) {
		  document.getElementById("cart").innerHTML = request.responseText;
		}
	};
	request.open("GET","getShoppingCart?name="+name,true);
	request.send();
	
	function inc(i){
		var qty = parseInt(document.getElementById(i+"qty").value);
		qty++;
		document.getElementById(i+"qty").value = qty;
	}
	
	function dec(i){
		var qty = parseInt(document.getElementById(i+"qty").value);
		if(qty>0)
			qty--;
		document.getElementById(i+"qty").value = qty;
	}
	
	function update(button,i){
		var item = button.id;
		var qty = document.getElementById(i+"qty").value;
		var price = document.getElementById(i+"price").innerHTML;
		if(qty=="0")
			alert("Enter a quantity");
		else{
			var request = new XMLHttpRequest();
			  request.onreadystatechange=function() {
				if (request.readyState==4 ) {
				  document.getElementById("total").innerHTML = request.responseText; 
				}
			  };
			request.open("GET","changeCart?item="+item+"&qty="+qty+"&name="+name+"&price="+price,true);
			request.send();
		}
	}
	
	function del(button){
		var it = button.id;
		var len = (it.length)-3;
		var item = it.slice(0,len);
		
		var request = new XMLHttpRequest();
		  request.onreadystatechange=function() {
			if (request.readyState==4 ) {
			  alert("Item deleted from cart");
			  location.reload();
			}
		  };
		request.open("GET","deleteFromCart?item="+item+"&name="+name,true);
		request.send();
	}
</script>
</html>
