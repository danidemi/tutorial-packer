<%@page import="javax.swing.text.StyledEditorKit.ForegroundAction"%>
<%@ page import="java.util.*" %>
<html>
	<head></head>
	<body>
		
		Login
		<form method="get" action="table">
			<input type="hidden" name="action" value="login" /> 
			User: <input type="text" name="user" />
			<input type="submit" value="Sign In" />
		</form>
		
		<footer>
			hostname:<%= request.getAttribute("hostname") %>
			user:<%= request.getAttribute("user") %> [<a href="?action=logout&user=<%= request.getAttribute("user") %>">logout</a>]
		</footer>	
		
	</body>
</html>