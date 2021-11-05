<!DOCTYPE HTML>
<%@page import="com.example.demo.model.SalaryMonth"%>
<%@page import="java.util.List"%>
<%@page import="com.example.demo.model.User"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
<meta charset="UTF-8" />
<title>Quản Lý Bảng Lương</title>
</head>
<%
    User user = (User) session.getAttribute("USER"); 
	if(user == null){
		response.sendRedirect("login");
	}
   %>

<body>
	<h3>
		Welcome,
		<%out.print(user.getUsername());%>
	</h3>
	<h2>Danh sách lương nhân viên tháng ${thang}</h2>
			<table>
				<tr>
					<th>TT</th>
					<th>Tên Nhân Viên</th>
					<th>Vị trí</th>
					<th>Tổng tiền</th>
					<th>Trạng thái</th>
				</tr>
				<c:forEach items="${listSalary }" var="salary" varStatus="loop">
				<tr>
					<td>
						${loop.index + 1}
					</td>
					<td>
						${salary.employee.fullname }
					</td>
					<td>
						${salary.employee.position }
					</td>
					<td>
						${salary.total_money }
					</td>
					<td>
						<a href="/chitietluong/${salary.id }">${salary.status }</a>
					</td>
				</tr>
				</c:forEach>
			</table>
</body>
</html>