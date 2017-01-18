<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    

<div>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="${contextPath}/">BeerRate</a>
		    </div>
		    
			<!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="${contextPath}/browse">Browse</a></li>
					<!-- <li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#">Action</a></li>
							<li><a href="#">Another action</a></li>
							<li><a href="#">Something else here</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="#">Separated link</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="#">One more separated link</a></li>
						</ul>
					</li>	 -->				
		      	</ul>
			
				<ul class="nav navbar-nav navbar-right">
					<li><p class="navbar-text">${logoutMessage}</p></li>
					<sec:authorize access="hasAnyRole('ADMIN', 'USER')">
						<li><p class="navbar-text" style="color: lightgreen;font-weight: bold;">Connected</p></li>
						<li>
							<a onclick="document.forms['logoutForm'].submit()">Log out</a>
							<form id="logoutForm" method="POST" action="${contextPath}/logout">
					            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					        </form>
						</li>	
					</sec:authorize>
					<sec:authorize access="!hasAnyRole('ADMIN', 'USER')">
						<li><a href="${contextPath}/login">Login</a></li>
						<li><a href="${contextPath}/register">Register</a></li>
					</sec:authorize>
				</ul>
			</div>
		</div>
	</nav>	
</div>