<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:url var="firstUrl" value="/browse/1" />
<c:url var="lastUrl" value="/browse/${beersPage.totalPages}" />
<c:url var="prevUrl" value="/browse/${currentIndex - 1}" />
<c:url var="nextUrl" value="/browse/${currentIndex + 1}" />

<!-- Set context path to request scope for navbar.jsp - http://stackoverflow.com/questions/16619015/how-to-pass-parameter-to-jspinclude-via-cset-what-are-the-scopes-of-the-varia -->
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    
    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    
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
    <div class="col-xs-0 col-md-3"></div>
    <div class="col-xs-12 col-md-6">
	
	<div class="row">
		<div class="col-xs-6"><h3>${beer.name}</h3></div>
		<div class="col-xs-6">
			<div class="col-xs-6">
				<p>Overall</p>
				<select id="overall-rating">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
				</select>
			</div>			
			<div class="col-xs-6">
				<sec:authorize access="hasAnyRole('ADMIN', 'USER')">
					<p>You</p>
					<select id="your-rating">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
					</select>
					<p id="ratingFeedback"></p>
				</sec:authorize>
				<sec:authorize access="!hasAnyRole('ADMIN', 'USER')">
					<p>Login to rate this beer !</p>	
				</sec:authorize>
			</div>
		</div>
	</div>
	
	
	<p>Alcohol By Volume : ${beer.abv}°</p>
    <p>Description : ${beer.descript}</p>
	<p>Style : ${beer.style.name}</p>
	<p>Category : ${beer.category.name}</p>
	<h4>Brewery : ${beer.brewery.name}</h4>
    <p>Address : ${beer.brewery.address1}</p>
    <p>Address 2 : ${beer.brewery.address2}</p>
    <p>City : ${beer.brewery.city}</p>
    <p>State : ${beer.brewery.state}</p>
    <p>Code : ${beer.brewery.code}</p>
    <p>Country : ${beer.brewery.country}</p>
    <p>Phone : ${beer.brewery.phone}</p>
    <p>Website : ${beer.brewery.website}</p>
    <p>Description : ${beer.brewery.descript}</p>
			
    </div>    
    <div class="col-xs-0 col-md-3"></div>
    </div>
	</div>	
	
	<!-- A FAIRE : mettre en READ ONLY qd on a fait un rating. mettre un bouton CHANGER VOTE ou qque chose comme ça -->
				
	<!-- /container -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<script src="${contextPath}/resources/js/jquery.barrating.min.js"></script>
	<script type="text/javascript">
		var userRating = '${userRating}';
		var overallRating = '${overallRating}';
		
		$(function() {
	      $('#your-rating').barrating({
	        theme: 'fontawesome-stars-o',
	        initialRating: userRating,
	        onSelect:function(value, text, event){
	        	var data={"rating":value, "userid":"${user.id}", "beerid":"${beer.id}"};
	        	console.log(data);
	        	document.getElementById("ratingFeedback").innerHTML="Sending rating";
	    		
	        	$.ajax({
	        		type : "POST",
	        		contentType : "application/json",
	        		url : "${contextPath}/api/submitRating",
	        		data : JSON.stringify(data),
	        		beforeSend: function (xhr) {	
	    	    		var token = $("meta[name='_csrf']").attr("content"); // CRFS : https://spring.io/blog/2013/08/21/spring-security-3-2-0-rc1-highlights-csrf-protection/
	    	    	    var header = $("meta[name='_csrf_header']").attr("content");
	    	    	    xhr.setRequestHeader(header, token);
	        		},
	        		dataType : 'json',
	        		timeout : 100000,
	        		success : function(data) {
	        			console.log("SUCCESS: ", data);
	    	        	document.getElementById("ratingFeedback").innerHTML=data.message;
	        		},
	        		error : function(e) {
	        			console.log("ERROR: ", e);
	    	        	document.getElementById("ratingFeedback").innerHTML="Error sending your rating. Please try again.";
	        		}
	        	});
	        }
	      });
	   });
	   $(function() {
	      $('#overall-rating').barrating({
	        theme: 'fontawesome-stars-o',
	        initialRating: overallRating,
	        readonly: true
	      });
	   });
	   
	</script>
	
</body>
</html>
