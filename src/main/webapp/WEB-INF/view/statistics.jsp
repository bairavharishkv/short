<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>

	<title>Statistics</title>
	
	<!-- Reference style css file -->
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/statistics-style.css" />
	
</head>
<body>

	<!-- Logo -->

	<div class="container">
		<span class="container-font">URL Shortener</span>	
	</div>
	
	<br/><br/>

	<div>

	    <!-- send short URL to the statistics controller -->
	    
		<form:form action="${pageContext.request.contextPath}/statistics/list" modelAttribute="url" method="POST">
		
			<!-- send a non-null value -->
			<form:hidden path="id" />
		
			<!-- entry for short Url -->   
            <div class = "entryrow" >
            
            	<div class= "enterShortUrl" >
                 
                    <form:input path="shortUrlkey"  id="shortUrlkey" type="text" placeholder=" Enter short URL Here..."/>   
            
        			<input type="submit" value="Get Statistics" >
        		
        		</div>
    		</div>
    		
		</form:form>
		
	</div>

	<br/><br/>
	
	<!-- link to return to home page -->
	
	<div>
		<button type="button" name="Statistics" class="home" 
				onclick="window.location.href='${pageContext.request.contextPath}/welcome'; return false;">Home</button>
	</div>

</body>