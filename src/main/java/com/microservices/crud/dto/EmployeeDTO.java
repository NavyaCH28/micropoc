package com.microservices.crud.dto;

import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EmployeeDTO {
	
	private Integer employeeId;
	
	private Date birth_date;
	
	private String first_name;
	
	private String last_name;
	
	private String gender;
	
	private Date hire_date;
	
	
	private List<SalaryDTO> salaries;
	
	private List<TitleDTO> titles;
	
	private List<Department_EmployeeDTO> employeeDepartments;
	
	private List<Department_ManagerDTO> managerDepartments;
	
	

}
