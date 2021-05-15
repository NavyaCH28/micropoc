package com.microservices.crud.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@Table(name="salaries")
@NoArgsConstructor
@AllArgsConstructor
public class Salary  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer salary;
	@JsonFormat(pattern = "YYYY-MM-dd")
	private Date from_date;
	@JsonFormat(pattern = "YYYY-MM-dd")
	private Date to_date;
	
	@ManyToOne
	@JoinColumn(name="emp_no")
	@Id
	private Employee employee;

}
