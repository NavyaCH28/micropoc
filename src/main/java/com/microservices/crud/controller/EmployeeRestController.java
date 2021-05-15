package com.microservices.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.crud.dto.EmployeeDTO;
import com.microservices.crud.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/employee")
@Api(value="EMS" )
public class EmployeeRestController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/save",
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Create Employee")
	public ResponseEntity<EmployeeDTO> save(@RequestBody EmployeeDTO employeeDTO) {
		EmployeeDTO createdEmployee = employeeService.createEmployee(employeeDTO);
		return new ResponseEntity<EmployeeDTO>(createdEmployee, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/delete/{id}",
			method = RequestMethod.DELETE)
	@ApiOperation(value="Delete Employee")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		String status = employeeService.deleteEmployee(id);
		
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/retrieve/{emp_id}",
			method = RequestMethod.GET)
	@ApiOperation(value="Retrieve Employee")
	public ResponseEntity<EmployeeDTO> retrieveEmployee(@PathVariable Integer emp_id) {
		EmployeeDTO retrieveEmployee = employeeService.retrieveEmployee(emp_id);
		return new ResponseEntity<EmployeeDTO>(retrieveEmployee, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	@ApiOperation(value="Find Employee")
	public ResponseEntity<List<EmployeeDTO>> findEmployees(
			@RequestParam String lastname, 
			@RequestParam String birthDate, 
			@RequestParam String gender) {
		List<EmployeeDTO> employeesList = employeeService.findEmployees(lastname, birthDate, gender);
		return ResponseEntity.ok().body(employeesList);
	}
	
	@RequestMapping(value = "/update",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Update Employee")
	public ResponseEntity<EmployeeDTO> retrieveEmployee(@RequestBody EmployeeDTO employeeDTO) {
		EmployeeDTO employee = employeeService.updateEmployee(employeeDTO);
		return new ResponseEntity<EmployeeDTO>(employee, HttpStatus.OK);
	}

}
