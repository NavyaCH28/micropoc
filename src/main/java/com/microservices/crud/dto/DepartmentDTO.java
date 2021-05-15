package com.microservices.crud.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DepartmentDTO {
	
	private String dept_no;
	
	private String dept_name;

}
