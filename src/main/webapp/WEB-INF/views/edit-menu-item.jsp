<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<style><%@include file="/WEB-INF/views/styles.css"%></style>

<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Menu Item</title>
</head>
<body>
<div class="container-my">
	<header class="header">
	<div class="header-truyum-div">
		<h1 class="heading-truYum">truYum</h1>
	</div>
	<img src="fork-knife.png"
		alt="forandKnife" class="forkImg">

	<div class="nav-links-div">
		<a href="/show-menu-list-customer" class="menu-button">Menu</a> <a
			href="/show-cart?userId=1" class="cart-button">Cart</a>
	</div>
	</header>

	<div class="body">
		<h2 class="body-text-heading">Edit Menu Item</h2>
		<div class="edit-menu-item-form">
			<form:form class="form-edit-menu" modelAttribute="menuItem"
				name="menuItemForm" method="POST" action="edit-menu-item"
				cssClass="edit-form">
				<form:hidden path="id" />
				<form:label path="name">Name</form:label>
				<br>
				<form:input path="name" cssClass="edit-form-name" name="title"
					id="title" value="${menuItem.name}" />
				<br>
				<form:errors path="name" cssClass="text-warning"></form:errors>
				<table>
					<tr class="form-table-edit-menu-row">



						<td class="form-table-edit-menu"><form:label path="price">Price (Rs.)</form:label></td>
						<td class="form-table-edit-menu"><form:label path="active">Active</form:label></td>
						<td class="form-table-edit-menu"><form:label
								path="dateOfLaunch">Date of Launch</form:label></td>
						<td class="form-table-edit-menu"><form:label
								path="freeDelivery">Category</form:label></td>
					</tr>
					<tr class="form-table-edit-menu-row">
						<td class="form-table-edit-menu"><form:input path="price"
								type="number" value="${menuItem.price}" name="price" id="price" /><br>
							<form:errors path="price" cssClass="text-warning"></form:errors>
						</td>
						<td class="form-table-edit-menu"><form:radiobutton
								path="active" value="true" name="inStock" id="inStock" /> <form:label
								path="active">Yes</form:label> <form:radiobutton path="active"
								value="false" name="inStock" id="inStock" /> <form:label
								path="active">No</form:label></td>
						<td class="form-table-edit-menu"><form:input
								path="dateOfLaunch" name="dateOfLaunch" id="dateOfLaunch" /></td>
						<td class="form-table-edit-menu"><form:select path="category">
								<c:forEach var="x" items="${catogoryList}">
									<option value="${x}">${x}</option>
								</c:forEach>
							</form:select>
					</tr>
				</table>
				<form:label path="freeDelivery">Free Delivery</form:label>
				<form:checkbox path="freeDelivery" name="freeDelivery"
					id="freeDelivery" value="${menuItem.freeDelivery}" />
				<br>
				<input class="save-button-edit" type="submit" value="Save">
			</form:form>
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