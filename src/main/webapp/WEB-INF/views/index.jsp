<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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

    <title>BeerRate</title>
    
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/stylesheet.css" rel="stylesheet">
    
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
    
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<jsp:include page="navbar.jsp" /> <!-- the inclusion is done at runtime and the include page has its own scope. -->
	
	<!-- TODO : implement search engine with auto complete  -->	
	<div class="row">
		<div class="col-xs-0 col-md-3"></div>
    	<div class="col-xs-12 col-md-6">
			<div class="form-group">
			  <label for="usr"></label>
			  <input type="text" class="form-control" id="usr">
			</div>
			<p>Search engine will come soon! Access the data through our <a href="${contextPath}/browse" >Browse</a> page.</p>
		</div>
		<div class="col-xs-0 col-md-3"></div>
	</div>
	
	<!-- /container -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	
</body>
</html>
