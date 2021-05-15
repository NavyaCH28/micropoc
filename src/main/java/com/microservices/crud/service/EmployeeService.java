package com.microservices.crud.service;

import java.util.List;

import com.microservices.crud.dto.EmployeeDTO;

public interface EmployeeService {
	
	public EmployeeDTO createEmployee(EmployeeDTO dto);
	
	public EmployeeDTO updateEmployee(EmployeeDTO dto);
	
	public String deleteEmployee(Integer id);
	
	public EmployeeDTO retrieveEmployee(Integer emp_id);
	
	public List<EmployeeDTO> findEmployees(String lastname, String birthDate, String Gender);
	

}
