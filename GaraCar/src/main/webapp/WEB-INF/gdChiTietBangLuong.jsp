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
   %>

<body>
	<h2>Bảng lương nhân viên: ${salaryMonth.employee.fullname}</h2>
	<h2>Vị trí công việc: ${salaryMonth.employee.position}</h2>
	<h2>Lương cứng: ${salaryMonth.employee.salary_hard}</h2>
			<table border="1" style="border-collapse : collapse;">
				<tr>
					<th>TT</th>
					<th>Tên dịch vụ</th>
					<th>Đơn giá (VNĐ)</th>
					<th>Số lượng</th>
					<th>Thành tiền (VNĐ)</th>
					<th>Hoa hồng (%)</th>
					<th>Tiền Hoa hồng (VNĐ)</th>
				</tr>
				<c:forEach items="${listSalaryDetail }" var="salaryDetail" varStatus="loop">
				<tr>
					<td>
						${loop.index + 1}
					</td>
					<td>
						${salaryDetail.service.name }
					</td>
					<td>
						${salaryDetail.service.price }
					</td>
					<td>
						${salaryDetail.count }
					</td>
					<td>
						${salaryDetail.money }
					</td>
					<td>
						${salaryDetail.discount }
					</td>
					<td>
						${salaryDetail.commission }
					</td>
				</tr>
				</c:forEach>
				<tr>
					<td colspan="6">
						Tổng tiền:
					</td>
					<td>
						${salaryMonth.total_money}
					</td>
				</tr>
			</table>
			<a href="/saveSalary">Thanh toán</a>
</body>
</html>