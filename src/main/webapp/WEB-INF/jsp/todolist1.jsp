<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>TODOs</title>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
<div class="container">
		<table class="table table-striped">
	
	
		<caption>Your Todos are</caption>
		<thead>
			<tr>
				<th>ID</th>
				<th>Description</th>
				<th>Target Date</th>
				<th>Is it Done</th>
				<th>Delete</th>
				<th>update</th>
				
				
			</tr>
		</thead>
		<tbody> 
			<c:forEach items="${todoDisplay}" var="todo">
				<tr>
				
				<td>${todo.id}</td>
					<td>${todo.desc}</td>
					<td>${todo.targetDate}</td>
					<td>${todo.isDone}</td>
					<td><a href="/deleteList?id=${todo.id}"	 type="button">Delete</a></td>
					<td><a href="/updateList?id=${todo.id}"	 type="button">Update</a></td>
					



				</tr>
			</c:forEach>

		</tbody>

	</table>
	<div> <a class="button" href="/addtodo">Add a Todo</a></div>
	<div> <a class="button" href="/logout">Logout</a></div>

		<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
		<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	</div>
	
</body>

</html>