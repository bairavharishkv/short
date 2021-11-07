<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>

	<title>Home</title>
	
	<!-- Reference style css file -->
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/home-style.css" />
	
</head>
<body>

	<!-- Logo -->
	<div class="container">
		<span class="container-font">URL Shortener</span>
	</div>
	<br/><br/>

	<!--  Page Content -->
	
	<div>
		<span class= "description">Enter a long URL below OR use the web service.</span>
	</div>


	<br/><br/><br/>

	<div >
	
		<!-- send long URL to the URL controller -->
	  
		<form:form action="${pageContext.request.contextPath}/postLongUrl" modelAttribute="url" method="POST">
		
			<!-- entry for long URL -->   
            <div class = "entryrow"  >
       			
                <div class= "enterLongUrl" >
                   
                    <form:input path="longUrl"  id="longUrl" type="text" placeholder=" Enter Long URL Here... http://www.example.com" />   
                
        			<input type="submit" value="Get Short URL!" >
        			
    			</div>
    			
    		</div>
    		
		</form:form>
		
		<br/><br/>
		
		<!-- return short URL to page -->
		<span class="shortUrl">${theShortenedUrl}</span>
		
	</div>

	<br/> <br/>

	<!-- links to other parts of the website -->

	<div class="hold-tiles">
	
		<!-- link to statistics page -->
		
		<button class="square-tile font-style green-tile"  name="Statistics" 
			onclick="window.location.href='${pageContext.request.contextPath}/statistics/home'; return false;">Statistics <br/>
			<span class = "small-description">Track Visitors</span>
		</button>
		
		&nbsp; 
		&nbsp; 
		
		<!-- link to web service instruction page -->

		<button class="square-tile font-style green-tile"  name="webservice" 
			onclick="window.location.href='${pageContext.request.contextPath}/webservice'; return false;">URL Shortener as a Web Service <br/>
			<span class = "small-description">Statistics are also available</span>	
		</button>
		
	</div>

</body>
</html>
