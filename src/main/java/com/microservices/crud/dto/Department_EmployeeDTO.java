package com.microservices.crud.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Department_EmployeeDTO {
	

	private Date fromDate;
	
	private Date toDate;
	
	private EmployeeDTO employeeDTO;
	
	private DepartmentDTO departmentDTO;
	
	
}
