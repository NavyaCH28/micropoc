package com.microservices.crud.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Department_ManagerDTO {

	private Date fromDate;

	private Date toDate;

	private EmployeeDTO employeeDTO;

	private DepartmentDTO departmentDTO;

}
