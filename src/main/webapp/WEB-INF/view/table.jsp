<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>

	<title>Table</title>
	
	<!-- Reference style css file -->
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/table-style.css" />
	
</head>
<body>

	<!-- Logo -->

	<div class="container">
		<span class="container-font">URL Shortener</span>	
	</div>
	
	<br/><br/>

    <!-- links to other pages on the web site -->
    
	<div>
	
		<!-- return to home page -->
		
		<button type="button" name="Statistics" class="home-back" 
				onclick="window.location.href='${pageContext.request.contextPath}/welcome'; return false;">Home</button>			
				
		&nbsp; 
		&nbsp;
		
		<!-- return to statistics page -->
		
		<button type="button" name="Statistics" class="home-back" 
				onclick="window.location.href='${pageContext.request.contextPath}/statistics/home'; return false;">Back</button>
	</div>

	<br/><br/>
	
	<!-- table displays short URL statistics (visitor time and IP address) -->
	
	<div>
	
		<!-- the short URL that the table refers to is displayed in the title  -->
		
		<span class="shortUrl"> ${tableTitle}</span>
		
		<br/><br/>
		
		<table class="table-fill" >
		
			<thead>
				<tr>
					<th>Time</th>    <!-- the time at which the short link was clicked -->
					<th>IP Address</th>   <!-- the IP address of the visitor -->
				</tr>
			</thead>
			
			<tbody>
			
				<!-- loop over statistics sent from the statistics controller -->
				
				<c:forEach var="statistic" items="${theStatistics}">
				
					<tr>
						<td> ${statistic.time} </td>
						<td> ${statistic.ipAddress} </td>
					</tr>
				
				</c:forEach>
			
			</tbody>
		
		</table>
	
	</div>

</body>

</html>