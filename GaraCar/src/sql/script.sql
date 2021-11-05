/*Lấy danh sách dịch vụ kèm số lượng nhân viên đã làm*/
DROP PROCEDURE IF EXISTS list_salary_month_detail_by_employee;
DELIMITER $$
CREATE PROCEDURE list_salary_month_detail_by_employee(IN employeeId long,IN month int,IN year int)
	BEGIN 
		DROP TEMPORARY TABLE IF EXISTS billbyemployee;
        CREATE TEMPORARY TABLE billbyemployee AS
        SELECT * FROM bill_provisional WHERE technical_employee_id = employeeId 
        AND MONTH(date_created) = month
        AND YEAR(date_created) = year;
        
        DROP TEMPORARY TABLE IF EXISTS salarydetailfake;
        CREATE TEMPORARY TABLE salarydetailfake LIKE salary_detail;
        
		INSERT INTO salarydetailfake(count,service_id)
        SELECT SUM(bd.count) AS count, bd.service_id FROM billbyemployee be JOIN bill_provisional_detail bd ON be.id = bd.bill_provisional_id
        GROUP BY bd.service_id;
        
        SELECT * FROM salarydetailfake;
        
	END$$
DELIMITER ;