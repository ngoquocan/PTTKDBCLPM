<!DOCTYPE HTML>
<%@page import="com.example.demo.model.User"%>
<%@page contentType="text/html; charset=UTF-8"%>
<html>
   <head>
      <meta charset="UTF-8" />
      <title>Quản Lý Bảng Lương</title>
   </head>
   <%User user = (User) session.getAttribute("USER"); %>
   <body>
      <h3>Welcome, <%out.print(user.getUsername());%></h3>
   </body>
  
</html>