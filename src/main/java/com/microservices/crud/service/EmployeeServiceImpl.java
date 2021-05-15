package com.microservices.crud.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.microservices.crud.dto.EmployeeDTO;
import com.microservices.crud.model.Employee;
import com.microservices.crud.repository.EmployeeRepository;
import com.microservices.crud.utils.Mapper;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public EmployeeDTO createEmployee(EmployeeDTO dto) {

		Employee employee = employeeRepository.save(Mapper.getEmployee(dto));

		return Mapper.getEmployeeDTO(employee);
	}

	@Override
	public EmployeeDTO updateEmployee(EmployeeDTO dto) {
		Optional<Employee> emp = employeeRepository.findById(dto.getEmployeeId());
		if (emp != null && emp.get() != null ) {
			Employee updatedEmp = Mapper.updateContents(dto, emp.get());
			Employee employee = employeeRepository.save(updatedEmp);
			return Mapper.getEmployeeDTO(employee);
		}
		
		return null;
	}

	@Override
	public String deleteEmployee(Integer id) {
		try {
			Optional<Employee> employee = employeeRepository.findById(id);
			//employeeRepository.deleteById(id);
			if (employee != null && employee.get() != null) {
				employeeRepository.delete(employee.get());
				return "Employee Deleted Successfully";
			} else {
				return "Employee is not present to delete";
			}
		} catch (IllegalArgumentException ex) {
			return "failure";
		}
	}

	@Override
	public EmployeeDTO retrieveEmployee(Integer emp_id) {
			Optional<Employee> employee = employeeRepository.findById(emp_id);
			if (employee != null && employee.get() != null) {
				return Mapper.getEmployeeDTO(employee.get());
			}
		throw new RuntimeException();
	}

	@Override
	public List<EmployeeDTO> findEmployees(String lastname, String birthDate, String gender) {
		if (lastname != null && birthDate != null && gender != null) {

				System.out.println(Date.valueOf(birthDate));

				List<Employee> employeeList = employeeRepository.findAll(Specification
						.where(EmployeeRepository.hasLastName(lastname))
						.and(EmployeeRepository.hasGender(gender))
						.and(EmployeeRepository.hasBirthDate(Date.valueOf(birthDate))));

				
				return employeeList.stream().map(employee -> Mapper.getEmployeeDTO(employee))
						.collect(Collectors.toList());

		}

		throw new RuntimeException();
	}

}
