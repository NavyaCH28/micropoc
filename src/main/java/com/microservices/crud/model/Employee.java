package com.microservices.crud.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@Table(name = "employees")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "emp_no")
	private Integer	employeeId;
	
	@JsonFormat(pattern = "YYYY-MM-dd")
	private Date birth_date;
	
	private String first_name;
	
	private String last_name;
	
	private String gender;
	
	private Date hire_date;
	
	@OneToMany(targetEntity = Department_Employee.class,
			orphanRemoval = true,
			mappedBy = "employee", 
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	@Embedded
	private List<Department_Employee> dep_emp;
	
	@OneToMany(targetEntity = Salary.class,
			orphanRemoval = true,
			mappedBy = "employee", 
			cascade = CascadeType.ALL, 
			fetch = FetchType.LAZY )
	private List<Salary> salary;
	
	@OneToMany(targetEntity = Titles.class,
			orphanRemoval = true,
			mappedBy = "employee",
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private List<Titles> titles;
	
	@OneToMany(targetEntity = Department_Manager.class,
			orphanRemoval = true,
			mappedBy = "employee",
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	@Embedded
	private List<Department_Manager> dept_manager;
}
