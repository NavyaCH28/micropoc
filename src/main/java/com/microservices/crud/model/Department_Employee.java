package com.microservices.crud.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "dept_emp")
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Department_Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "YYYY-MM-dd")
	private Date from_date;
	@JsonFormat(pattern = "YYYY-MM-dd")
	private Date to_date;

	@ManyToOne()
	@JoinColumn(name = "emp_no")
	@EmbeddedId
	private Employee employee;

	@ManyToOne
	@EmbeddedId
	@JoinColumn(name = "dept_no")
	private Department department;

}
