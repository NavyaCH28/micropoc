package com.microservices.crud.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteEmployee(Integer id) {
		try {
			Optional<Employee> employee = employeeRepository.findById(id);
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
		try {
			Optional<Employee> employee = employeeRepository.findById(emp_id);
			if (employee != null && employee.get() != null) {
				return Mapper.getEmployeeDTO(employee.get());
			}
		} catch (IllegalArgumentException ex) {
			return null;
		}
		return null;
	}

	@Override
	public List<EmployeeDTO> findEmployees(String lastname, String birthDate, String gender) {
		if (lastname != null && birthDate != null && gender != null) {
			Date date;
			try {
				date = new SimpleDateFormat("yyyy-mm-dd").parse(birthDate);
				System.out.println(date);
				System.out.println(new java.sql.Date(date.getTime()));
				List<Employee> employeeList = employeeRepository.findAll(Specification
						.where(EmployeeRepository.hasLastName(lastname)).and(EmployeeRepository.hasGender(gender)));

				System.out.print(
						employeeRepository.findAll(Specification.where(EmployeeRepository.hasLastName(lastname))));
				System.out.print(employeeRepository.findAll(Specification.where(EmployeeRepository.hasGender(gender))));
				System.out.print(employeeRepository.findAll(
						Specification.where(EmployeeRepository.hasBirthDate(new java.sql.Date(date.getTime())))));
				return employeeList.stream().map(employee -> Mapper.getEmployeeDTO(employee))
						.collect(Collectors.toList());

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

}
