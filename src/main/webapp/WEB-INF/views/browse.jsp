<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="firstUrl" value="/browse?page=1&sort=${sort}" />
<c:url var="lastUrl" value="/browse?page=${beersPage.totalPages}&sort=${sort}" />
<c:url var="prevUrl" value="/browse?page=${currentIndex - 1}&sort=${sort}" />
<c:url var="nextUrl" value="/browse?page=${currentIndex + 1}&sort=${sort}" />

<!-- Set context path to request scope for navbar.jsp - http://stackoverflow.com/questions/16619015/how-to-pass-parameter-to-jspinclude-via-cset-what-are-the-scopes-of-the-varia -->
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Browse OpenBeerDB</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/stylesheet.css" rel="stylesheet">
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet" >
	<link href="${contextPath}/resources/css/barrating-themes/fontawesome-stars-o.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<jsp:include page="navbar.jsp" /> <!-- the inclusion is done at runtime and the include page has its own scope. -->
	<%-- <%@include file="navbar.jsp"%> --%>  <!-- any variable declared on the parent JSP will be in scope in the subview  -->
	
	
    
    <div class="container-fluid">
    <div class="row">
    <div class="col-xs-0 col-md-2"></div>
    <div class="col-xs-12 col-md-8">        
        
    <table class="table table-hover">
    	<thead>
	    	<tr>
				<th>Name</th><th>Style</th><th>Country</th><th>Rating</th>
			</tr>
    	</thead>
    	
    	<tbody>
    		<c:forEach items="${beers}" var="beer" varStatus="current">
			    <tr class='clickable-row' data-href='${contextPath}/beer/${beer.id}'>
					<td class="col-xs-4">${beer.name}</td>
					<td class="col-xs-4">${beer.style.name}</td>
					<td class="col-xs-2">${beer.brewery.country}</td>
					<td class="col-xs-2">
						<div id="ratingContainer${current.index}" style="visibility:hidden;">
							<span style="display:inline;" id="beerRating${current.index}">${beersRatings[current.index]}   </span>	
							<select class="starsContainer" id="stars${current.index}">
								<option value="0"></option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
							</select>						
						</div>						
					</td>
				</tr>
				
			</c:forEach>
    	</tbody>

    </table>
        
    <!-- Pagination -->
    <div style="text-align: center">
   		<ul class="pagination" style="display: inline-block;">
	        <c:choose>
	            <c:when test="${currentIndex == 1}">
	                <li class="disabled"><a href="#">&lt;&lt;</a></li>
	                <li class="disabled"><a href="#">&lt;</a></li>
	            </c:when>
	            <c:otherwise>
	                <li><a href="${firstUrl}">&lt;&lt;</a></li>
	                <li><a href="${prevUrl}">&lt;</a></li>
	            </c:otherwise>
	        </c:choose>
	        <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
	            <c:url var="pageUrl" value="/browse?page=${i}&sort=${sort}" />
	            <c:choose>
	                <c:when test="${i == currentIndex}">
	                    <li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
	                </c:when>
	                <c:otherwise>
	                    <li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
	                </c:otherwise>
	            </c:choose>
	        </c:forEach>
	        <c:choose>
	            <c:when test="${currentIndex == beersPage.totalPages}">
	                <li class="disabled"><a href="#">&gt;</a></li>
	                <li class="disabled"><a href="#">&gt;&gt;</a></li>
	            </c:when>
	            <c:otherwise>
	                <li><a href="${nextUrl}">&gt;</a></li>
	                <li><a href="${lastUrl}">&gt;&gt;</a></li>
	            </c:otherwise>
	        </c:choose>
	    </ul>        
    </div>
    
    </div>    
    <div class="col-xs-0 col-md-2"></div>
    </div>
	</div>
			
	<!-- /container -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<script src="${contextPath}/resources/js/jquery.barrating.min.js"></script>	
	<script>
		$(function($) {
		    $(".clickable-row").click(function() {
		        window.document.location = $(this).data("href");
		    });
		});
		
		$(function() {
			var rating;
			for(i=0;i<20;i++){
				rating=parseFloat($("#beerRating"+i)[0].innerHTML);
				$("#stars"+i).barrating({
					theme: 'fontawesome-stars-o',
					allowEmpty: true,
					emptyValue: "0",
					initialRating: rating,
					readonly: true
				});
				$("#beerRating"+i)[0].innerHTML="";
				$("#ratingContainer"+i).css("visibility", "visible");
			}
		});
	</script>
	
</body>
</html>
