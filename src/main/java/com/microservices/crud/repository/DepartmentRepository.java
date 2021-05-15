package com.microservices.crud.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.microservices.crud.model.Department;

@Repository
public interface DepartmentRepository extends CrudRepository<Department,String> {


}
