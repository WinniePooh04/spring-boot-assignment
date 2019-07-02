package com.spring.exercise.spring.boot.assignment2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.exercise.spring.boot.assignment2.dao.EmployeeDao;
import com.spring.exercise.spring.boot.assignment2.model.Employee;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public List<Employee> getAllEmplooyee() {

		return employeeDao.getAllEmplooyee();
	}

	@Override
	public List<Employee> searchEmployee(String searchParam) {

		return employeeDao.searchEmployee(searchParam);
	}

	@Override
	public void insertEmployee(Employee employee) {

		employeeDao.insertEmployee(employee);
	}

	@Override
	public Employee getEmployeeDetail(Integer employeeId) {
		
		return employeeDao.getEmployeeDetail(employeeId);
	}

	@Override
	public void updateEmployee(Employee employee) {
		
		employeeDao.updateEmployee(employee);
	}

	@Override
	public void deleteEmployee(Integer employeeId) {
		
		employeeDao.deleteEmployee(employeeId);
	}

}
