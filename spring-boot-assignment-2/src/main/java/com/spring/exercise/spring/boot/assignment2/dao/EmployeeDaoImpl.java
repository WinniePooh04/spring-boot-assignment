package com.spring.exercise.spring.boot.assignment2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.spring.exercise.spring.boot.assignment2.model.Employee;

@Repository
@Transactional
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private JdbcTemplate JdbcTemplate;

	@Override
	public List<Employee> getAllEmplooyee() {

		List<Employee> employeeList = new ArrayList<Employee>();
		employeeList = JdbcTemplate.query("SELECT * FROM EMPLOYEE", new EmployeeMapper());
		return employeeList;
	}

	class EmployeeMapper implements RowMapper<Employee> {

		@Override
		public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {

			Employee emp = new Employee();
			emp.setId(rs.getInt("id"));
			emp.setName(rs.getString("name"));
			emp.setAge(rs.getInt("age"));
			emp.setAddress(rs.getString("address"));
			return emp;
		}
	}

	@Override
	public List<Employee> searchEmployee(String searchParam) {

		List<Employee> employeeList = new ArrayList<Employee>();
		String sql = "";
		if (!StringUtils.isEmpty(searchParam)) {
			sql = "SELECT * FROM EMPLOYEE WHERE LOWER(name) LIKE '%" + searchParam.toLowerCase() + "%'";
		} else {
			sql = "SELECT * FROM EMPLOYEE";
		}
		employeeList = JdbcTemplate.query(sql, new EmployeeMapper());

		return employeeList;
	}

	@Override
	public void insertEmployee(Employee employee) {
		JdbcTemplate.update("INSERT INTO EMPLOYEE (NAME,AGE,ADDRESS) VALUES (?,?,?)", employee.getName(),
				employee.getAge(), employee.getAddress());
	}

	@Override
	public Employee getEmployeeDetail(Integer employeeId) {

		List<Employee> employee = JdbcTemplate.query("SELECT * FROM EMPLOYEE WHERE id =" + employeeId,
				new EmployeeMapper());
		return employee.get(0);
	}

	@Override
	public void updateEmployee(Employee employee) {
		JdbcTemplate.update("UPDATE EMPLOYEE SET NAME = ?, AGE=?, ADDRESS=? WHERE ID=?", employee.getName(),
				employee.getAge(), employee.getAddress(), employee.getId());

	}

	@Override
	public void deleteEmployee(Integer employeeId) {

		JdbcTemplate.update("DELETE FROM EMPLOYEE WHERE ID=?", employeeId);
	}
}
