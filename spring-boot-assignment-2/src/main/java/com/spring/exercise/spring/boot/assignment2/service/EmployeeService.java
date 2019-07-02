package com.spring.exercise.spring.boot.assignment2.service;

import java.util.List;

import com.spring.exercise.spring.boot.assignment2.model.Employee;

public interface EmployeeService {

	List<Employee> getAllEmplooyee();
	
	List<Employee> searchEmployee(String searchParam);
	
	void insertEmployee(Employee employee);
	
	Employee getEmployeeDetail(Integer employeeId);
	
	void updateEmployee(Employee employee);
	
	void deleteEmployee(Integer employeeId);
}
