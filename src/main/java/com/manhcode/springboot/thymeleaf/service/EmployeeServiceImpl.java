package com.manhcode.springboot.thymeleaf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manhcode.springboot.thymeleaf.dao.EmployeeRepository;
import com.manhcode.springboot.thymeleaf.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	// inject DAO

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getEmployees() {
		// find all normal
		// return employeeRepository.findAll();

		// findall by orderlastname
		return employeeRepository.findAllByOrderByLastNameAsc();
	}

	@Override
	public Employee getEmployee(int theId) {
		Optional<Employee> result = employeeRepository.findById(theId);

		Employee theEmployee = null;

		if (result.isPresent()) {
			theEmployee = result.get();
		} else {
			// we didn't find the employee
			throw new RuntimeException("Did not find employee id - " + theId);
		}

		return theEmployee;
	}

	@Override
	public void saveEmployee(Employee theEmployee) {
		employeeRepository.save(theEmployee);

	}

	@Override
	public void deleteEmployee(int theId) {
		employeeRepository.deleteById(theId);

	}

	@Override
	public List<Employee> searchBy(String theName) {
		List<Employee> results = null;

		if (theName != null && (theName.trim().length() > 0)) {
			results = employeeRepository.findByFirstNameContainsOrLastNameContainsAllIgnoreCase(theName, theName);
		} else {
			results = getEmployees();
		}

		return results;
	}

}
