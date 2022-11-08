<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cart</title>
</head>

<style><%@include file="/WEB-INF/views/styles.css"%></style>

<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">

<body>
<div class="container-my">
<header class="header">
	<div class="header-truyum-div">
		<h1 class="heading-truYum">truYum</h1>
	</div>
	<img src="fork-knife.png" alt="forandKnife" class="forkImg">
		
	<div class="nav-links-div">
		 <a href="/show-menu-list-customer" class="menu-button">Menu</a> 
		 <a	href="/show-cart?userId=1" class="cart-button">Cart</a>
	</div>
</header>

<div class="body">
		<h1 class="body-text-heading">Cart</h1>
		<div id="cart-result">
			<div class="show-cart-table">
				<table class="table">
					<tr>
						<th>Name</th>
						<th>Free Delivery</th>
						<th>Price</th>
						<th></th>
					</tr>
					<c:forEach var="menuItem" items="${cartItems}">
						<tr>
							<td>${menuItem.name}</td>
							<td><c:choose>
									<c:when test="${menuItem.freeDelivery}">
									Yes
								</c:when>
									<c:otherwise>
									No
								</c:otherwise>
								</c:choose></td>
							<td>${menuItem.price}</td>
							<td><a href="/remove-cart?menuItemId=${menuItem.id}&userId=${userId}">Delete</a>
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td></td>
						<td class="cart-footer-row">Total</td>
						<td class="cart-footer-row">${total}</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	
	<footer class="footer">
	<div class="copyright-div">
		<label class="copyright">Copyright © 2020</label>
	</div>
	</footer>
	
	</div>
	
	<script src="webjars/jquery/3.3.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script
	src="webjars/bootstrap-datepicker/1.0.1/js/bootstrap-datepicker.js"></script>
<script>
	$('#dateOfLaunch').datepicker({
		format : 'yyyy-mm-dd'
	});
</script>	

</body>
</html>