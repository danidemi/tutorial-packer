<%@page import="javax.swing.text.StyledEditorKit.ForegroundAction"%>
<%@ page import="java.util.*" %>
<% List<String> table = (List<String>)request.getAttribute("table"); %>
<html>
	<head></head>
	<body>
	
		<p>
			<h3>Items</h3>
			<%if(table.isEmpty()){%>
				No items.
			<%}else{%>
				<table>
					<%for(String row : table){%>
						<tr><td><%= row %></td><td>[<a href="?action=remove&value=<%=row%>">X</a>]</td></tr>
					<%}%>
				</table>
			<%}%>
		</p>
		
		<h3>New Item</h3>
		<form method="get" action="table">
			<input type="hidden" name="action" value="add" /> 
			<input type="text" name="value" />
			<input type="submit" />
		</form>
		
		<footer>
			hostname:<%= request.getAttribute("hostname") %>
			user:<%= request.getAttribute("user") %> [<a href="?action=logout&user=<%= request.getAttribute("user") %>">logout</a>]
		</footer>		
		
	</body>
</html>