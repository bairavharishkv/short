<!DOCTYPE html>
<html>
<head>

	<title>Web Service</title>
	
	<!-- Reference style css file -->
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/webservice-style.css" />
	
</head>
<body>

	<!-- Logo -->

	<div class="container">
		<span class="container-font">URL Shortener Web Service</span>	
	</div>
	
	<br/><br/>

	<!-- link to return to home page -->
	
	<div>
		<button type="button" name="Statistics" class="home"  
				onclick="window.location.href='${pageContext.request.contextPath}/welcome'; return false;">Home</button>
	</div>
	
	<br/>

	<!-- instructions to shorten a URL via the web service -->

	<div class="shortener">
	
		<h2>Get Short URL</h2>
		
	    Send the following POST request to: http://localhost:8080/short/shortener: 
	    
	    <br/>
	    
		<div class = "white">
		
			<p>
				&nbsp; {  <br>
				&nbsp;&nbsp;&nbsp; "longUrl" : "long URL ..." <br/>
				&nbsp; }
			</p>
			
		</div>
		
		<br/>
		
		Example:
		
		<br/>
		
		<div class = "white">
		
			<p> 
				&nbsp; {  <br>
				&nbsp;&nbsp;&nbsp; "longUrl" : "https://www.thekennelclub.org.uk/" <br/>
				&nbsp; }
			</p>
			
		</div>
		
		<br/>
		
		A unique id will be returned. The short URL then becomes: 
		
		<br/>
		
		<div class = "white">
		
			<p>
				&nbsp; http://localhost:8080/short/unique-id
			</p>
			
		</div>
		
		<br/>
		
		For example if the unique id is 'ABC' then the short URL is:
		
		<br/>
		
		<div class = "white">
		
			<p>
				&nbsp;http://localhost:8080/short/ABC
			</p>
			
		</div>
		
		<br/>
		
	</div>
	
	<br/>
	
	<!-- instructions to get short URL statistics via the web service -->
	
	<div class="statistics">
		
		<h2>Statistics</h2>
		
		 Send a GET request to:
		 
		 <br/>
		 
		 <div class="white">
		 
		 	<p>
		    	&nbsp; http://localhost:8080/short/info/unique-id
		 	</p>
		 	
		 </div>
		 
		  <br/>
		  
		     For example if your short URL is: http://localhost:8080/short/ABC and the unique id is 'ABC' 
					the GET request should go to:
		  <br/>
		  
		  <div class="white">
		  
		  	<p>
				&nbsp; http://localhost:8080/short/info/ABC
		  	</p>
		  	
		  </div>
		  
		  <br/>
		  
	</div>
	
	<br/><br/>

 
</body>
</html>