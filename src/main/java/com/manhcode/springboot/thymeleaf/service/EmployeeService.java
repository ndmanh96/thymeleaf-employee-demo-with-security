package com.manhcode.springboot.thymeleaf.service;

import java.util.List;

import com.manhcode.springboot.thymeleaf.entity.Employee;

public interface EmployeeService {
	//getall
	public List<Employee> getEmployees();
	
	//get by id
	public Employee getEmployee(int theId);
	
	//save
	public void saveEmployee(Employee theEmployee);
	
	//delete
	public void deleteEmployee(int theId);

	public List<Employee> searchBy(String theName);
	
}
