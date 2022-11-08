<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<style><%@include file="/WEB-INF/views/styles.css"%></style>

<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Status</title>
</head>
<body>
<div class="container-my">
<header class="header">
	<div class="header-truyum-div">
		<h1 class="heading-truYum">truYum</h1>
	</div>
	
	<img src="fork-knife.png" alt="forandKnife" class="forkImg">

	<div class="nav-links-div">
		<a href="/show-menu-list-admin" class="menu-button">Menu</a>
	</div>
</header>


<div class="body">
        <h1 class="edit-status-heading">Edit Menu Item Status</h1>
        <h3  class="edit-menu-status">Menu Item Details saved successfully</h3>
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