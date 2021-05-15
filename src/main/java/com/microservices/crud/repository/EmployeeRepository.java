package com.microservices.crud.repository;


import java.sql.Date;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.microservices.crud.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor<Employee> {


	static Specification<Employee> hasLastName(String last_name) {
		return (employee, cq, cb) -> cb.equal(employee.get("last_name"), last_name);
	}

	static Specification<Employee> hasBirthDate(Date birthDate) {
		return (employee, cq, cb) -> cb.equal(employee.get("birth_date"), birthDate);
	}

	static Specification<Employee> hasGender(String gender) {
		return (employee, cq, cb) -> cb.equal(employee.get("gender"), gender);
	}

}
