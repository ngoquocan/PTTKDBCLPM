<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
   <head>
      <meta charset="UTF-8" />
      <title>Welcome</title>
   </head>
   <h4>${checkLogin }</h4>
   <body>
      <form action="/home" method="get">
      	<input type="text" name = "username">
      	<input type="text" name = "password">
      	<input type="submit" value="Login">
      </form>     
   </body>
  
</html>