package com.microservices.crud.repository;


import java.sql.Date;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.microservices.crud.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor<Employee> {

	@Query("SELECT e FROM Employee e WHERE e.last_name = ?1")
	Employee findEmployee_by_lastname(String last_name);

	@Query("SELECT e FROM Employee e WHERE e.last_name = ?1 and e.first_name = ?2")
	Employee findEmployee_by_lastname_firstname(String last_name, String first_name);

	@Query("SELECT e FROM Employee e WHERE e.last_name = ?1 and e.first_name = ?2 and e.gender =?3")
	Employee findEmployee_by_lastname_firstname_gender(String last_name, String first_name, String gender);

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
