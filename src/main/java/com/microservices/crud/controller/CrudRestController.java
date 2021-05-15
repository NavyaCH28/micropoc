package com.microservices.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.crud.dto.EmployeeDTO;
import com.microservices.crud.service.EmployeeService;

@RestController

public class CrudRestController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/save",
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public EmployeeDTO save(@RequestBody EmployeeDTO employeeDTO) {
		EmployeeDTO createdEmployee = employeeService.createEmployee(employeeDTO);
		return createdEmployee;
	}
	
	@RequestMapping(value = "/delete/{id}",
			method = RequestMethod.DELETE)
	public String delete(@PathVariable Integer id) {
		String status = employeeService.deleteEmployee(id);
		return status;
	}
	
	@RequestMapping(value = "/retrieve/{emp_id}",
			method = RequestMethod.GET)
	public EmployeeDTO retrieveEmployee(@PathVariable Integer emp_id) {
		EmployeeDTO retrieveEmployee = employeeService.retrieveEmployee(emp_id);
		return retrieveEmployee;
	}
	
	
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public List<EmployeeDTO> findEmployees(
			@RequestParam String lastname, 
			@RequestParam String birthDate, 
			@RequestParam String gender) {
		List<EmployeeDTO> employeesList = employeeService.findEmployees(lastname, birthDate, gender);
		return employeesList;
	}
	
	@RequestMapping(value = "/update",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public EmployeeDTO retrieveEmployee(@RequestBody EmployeeDTO employeeDTO) {
		EmployeeDTO employee = employeeService.updateEmployee(employeeDTO);
		return employee;
	}

}
