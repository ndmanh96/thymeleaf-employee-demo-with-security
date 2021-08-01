package com.manhcode.springboot.thymeleaf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.manhcode.springboot.thymeleaf.entity.Employee;
import com.manhcode.springboot.thymeleaf.service.EmployeeService;

@Controller()
public class EmployeeController {

	//inject service
	@Autowired
	private EmployeeService employeeService;
	
	//get mapping
	@GetMapping("/")
	public String showEmployee(Model theModel) {
		//get list form service
		List<Employee> list = employeeService.getEmployees();
		System.out.println(list);
		
		theModel.addAttribute("employees", list);
		return "employees/employee";
	}
	
	//show form for add
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Employee newEmployee = new Employee();
		
		theModel.addAttribute("employee", newEmployee);
		
		return "employees/showformforadd";
	}
	
	
	//save new Employ
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		employeeService.saveEmployee(theEmployee);
		
		return "redirect:/";
	}
	
	//update
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {
		//get employee
		Employee theEmployee = employeeService.getEmployee(theId);
		
		theModel.addAttribute("employee", theEmployee);
		
		return "employees/showformforadd";
	}
	
	//delete
	@GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam("employeeId") int theId) {
		//get employee
		employeeService.deleteEmployee(theId);
		
		return "redirect:/";
	}
	
	//search by lastname or first name
	@GetMapping("/search")
	public String delete(@RequestParam("employeeName") String theName,
						 Model theModel) {
		
		// delete the employee
		List<Employee> theEmployees = employeeService.searchBy(theName);
		
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);
		
		// send to /employees/list
		return "/employees/employee";
		
	}
	
	//create a mapping for hello
		@GetMapping("/hello")
		public String sayHello(Model theModel) {
			theModel.addAttribute("theDate", new java.util.Date());
			return "helloworld";
		}
}
