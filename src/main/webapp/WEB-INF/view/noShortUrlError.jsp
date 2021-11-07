<!DOCTYPE html>
<html>
<head>

	<title>No Short URL Error</title>
	
	<!-- Reference style css file -->
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/noShortUrlError.css" /> 
	
</head>
<body>

	<!-- Logo -->

	<div class="container">
		<span class="container-font">URL Shortener</span>	
	</div>

	<br/><br/><br/>
	
	<!-- error Message -->

	<div>
		<span class="description">You have entered an invalid short URL!</span>
	</div>

	<br/><br/>

	<div>

	    <!-- button to return home -->
	    
		<button type="button" name="Statistics" class="home" 
				onclick="window.location.href='${pageContext.request.contextPath}/welcome'; return false;">Home</button>
				
		&nbsp; 
		&nbsp;
				
		<!-- return to statistics page -->
		
		<button type="button" name="Statistics" class="home" 
				onclick="window.location.href='${pageContext.request.contextPath}/statistics/home'; return false;">Back</button>
				
	</div>
	
</body>
</html>