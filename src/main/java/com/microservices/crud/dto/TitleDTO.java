package com.microservices.crud.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TitleDTO {
	
	private String titleName;
	
	private Date fromDate;
	
	private Date toDate;

}
