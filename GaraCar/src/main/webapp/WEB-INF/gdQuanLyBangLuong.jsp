<!DOCTYPE HTML>
<%@page import="com.example.demo.model.User"%>
<%@ page import="java.util.List, java.text.*" %>
<%@page contentType="text/html; charset=UTF-8"%>
<html>

<head>
	<meta charset="UTF-8" />
	<title>Quản Lý Bảng Lương</title>
</head>
<%
      User user = (User) session.getAttribute("USER"); 
      List<SalaryMonth> listSalary = (ArrayList<SalaryMonth>) request.getAttribute("listSalary");
   %>

<body>
	<h3>Welcome, <%out.print(user.getUsername());%></h3>
	<h2>Danh sách lương nhân viên tháng 11<h2>
			<table>
				<tr>
					<th>TT</th>
					<th>Tên Nhân Viên</th>
					<th>Vị trí</th>
					<th>Tổng tiền</th>
					<th>Trạng thái</th>
				</tr>
				<% for(int i = 0;i<listSalary.size();i++){%>
				<tr>
					<th><%i+1;%></th>
					<th><%listSalaryMonth.get(i).getEmployee().getFullname();%></th>
					<th><%listSalaryMonth.get(i).getEmployee().getPosition();%></th>
					<th><%listSalaryMonth.get(i).getTotal_money();%></th>
					<th><%listSalaryMonth.get(i).getStatus();%></th>
				</tr>
				<%}%>
      </table>
   </body>  
</html>