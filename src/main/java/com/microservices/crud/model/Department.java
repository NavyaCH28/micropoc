package com.microservices.crud.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "departments")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;

	private String departmentName;

	@Id
	@GeneratedValue
	private String dept_no;

	@OneToMany(targetEntity = Department_Employee.class, mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Department_Employee> dept_Emp;

	@OneToMany(targetEntity = Department_Manager.class, mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Department_Manager> dept_Mngr;

}
