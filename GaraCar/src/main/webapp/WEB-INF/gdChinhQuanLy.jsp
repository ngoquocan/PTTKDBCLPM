<!DOCTYPE HTML>
<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="com.example.demo.model.Employee"%>
<%@page import="com.example.demo.model.User"%>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Trang chủ</title>
   </head>
   <% 
      	User user = (Employee) session.getAttribute("USER");
   %>
   <body>
      <h3>Welcome, <%out.print(user.getUsername());%></h3>
      <a href="logout">Đăng xuất</a>
      <a href="quanlybangluong">Quản Lý Bảng Lương</a>
   </body>  
</html>