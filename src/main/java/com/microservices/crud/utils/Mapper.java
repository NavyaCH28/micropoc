package com.microservices.crud.utils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


import com.microservices.crud.dto.DepartmentDTO;
import com.microservices.crud.dto.Department_EmployeeDTO;
import com.microservices.crud.dto.Department_ManagerDTO;
import com.microservices.crud.dto.EmployeeDTO;
import com.microservices.crud.dto.SalaryDTO;
import com.microservices.crud.dto.TitleDTO;
import com.microservices.crud.model.Department;
import com.microservices.crud.model.Department_Employee;
import com.microservices.crud.model.Department_Manager;
import com.microservices.crud.model.Employee;
import com.microservices.crud.model.Salary;
import com.microservices.crud.model.Titles;

public class Mapper {

	public static Employee getEmployee(EmployeeDTO employeeDTO) {
		Employee employee = Employee.builder()
				.first_name(employeeDTO.getFirst_name())
				.last_name(employeeDTO.getLast_name())
				.gender(employeeDTO.getGender())
				.hire_date(new Date(employeeDTO.getHire_date().getTime()))
				.birth_date(new Date(employeeDTO.getBirth_date().getTime()))
				.build();

		List<Salary> salaries = getSalaries(employeeDTO, employee);

		List<Titles> titles = getTitles(employeeDTO, employee);

		List<Department_Employee> departmentEmployees = getDepartmentEmployees(employeeDTO, employee);

		List<Department_Manager> departmentManagers = getDepartmentManagers(employeeDTO, employee);

		employee.setSalary(salaries);
		employee.setTitles(titles);
		employee.setDep_emp(departmentEmployees);
		employee.setDept_manager(departmentManagers);
		return employee;

	}

	private static List<Department_Manager> getDepartmentManagers(EmployeeDTO employeeDTO, Employee employee) {
		List<Department_Manager> department_Managers = new ArrayList<>();
		List<Department_ManagerDTO> managerDepartments = employeeDTO.getManagerDepartments();

		for (Department_ManagerDTO department_ManagerDTO : managerDepartments) {
			department_Managers
					.add(Department_Manager.builder()
							.from_date(new Date(department_ManagerDTO.getFromDate().getTime()))
							.to_date(new Date(department_ManagerDTO.getToDate().getTime()))
							.employee(employee)
							.department(Department.builder()
									.departmentName(department_ManagerDTO.getDepartmentDTO().getDept_name())
									.dept_no(department_ManagerDTO.getDepartmentDTO().getDept_no())
									.build())
							.build());

		}
		return department_Managers;
	}

	private static List<Department_Employee> getDepartmentEmployees(EmployeeDTO employeeDTO, Employee employee) {
		List<Department_Employee> department_Employees = new ArrayList<>();
		List<Department_EmployeeDTO> employeeDepartments = employeeDTO.getEmployeeDepartments();
		for (Department_EmployeeDTO department_EmployeeDTO : employeeDepartments) {
			Department_Employee department_Employee = Department_Employee.builder()
					.from_date(new Date(department_EmployeeDTO.getFromDate().getTime()))
					.to_date(new Date(department_EmployeeDTO.getToDate().getTime()))
					.employee(employee).build();

			department_Employee.setDepartment(getDepartment(department_EmployeeDTO, department_Employee));

			department_Employees.add(department_Employee);
		}

		return department_Employees;
	}

	private static Department getDepartment(Department_EmployeeDTO department_EmployeeDTO,
			Department_Employee department_Employee) {
		List<Department_Employee> emp = new ArrayList<>();
		emp.add(department_Employee);
		return Department.builder()
				.dept_no(department_EmployeeDTO.getDepartmentDTO().getDept_no())
				.departmentName(department_EmployeeDTO.getDepartmentDTO().getDept_name())
				.dept_Emp(emp)
				.build();
	}

	private static List<Titles> getTitles(EmployeeDTO employeeDTO, Employee employee) {
		List<Titles> titles = new ArrayList<>();
		List<TitleDTO> titlesList = employeeDTO.getTitles();
		for (TitleDTO titleDTO : titlesList) {
			titles.add(Titles.builder()
					.from_date(new Date(titleDTO.getFromDate().getTime()))
					.to_date(new Date(titleDTO.getToDate().getTime()))
					.title(titleDTO.getTitleName()).employee(employee)
					.build());
		}
		
		return titles;
	}

	private static List<Salary> getSalaries(EmployeeDTO dto, Employee employee) {
		List<Salary> salaries = new ArrayList<>();
		List<SalaryDTO> salariesList = dto.getSalaries();
		for (SalaryDTO salaryDTO : salariesList) {
			salaries.add(Salary.builder()
					.from_date(new Date(salaryDTO.getFromDate().getTime()))
					.to_date(new Date(salaryDTO.getToDate().getTime()))
					.salary(salaryDTO.getAmount().intValue())
					.employee(employee)
					.build());
		}
		return salaries;
	}

	public static EmployeeDTO getEmployeeDTO(Employee employee) {
		return EmployeeDTO.builder()
				.employeeId(employee.getEmployeeId())
				.first_name(employee.getFirst_name())
				.last_name(employee.getLast_name())
				.gender(employee.getGender())
				.hire_date(new Date(employee.getHire_date().getTime()))
				.birth_date(new Date(employee.getBirth_date().getTime()))
				.salaries(getSalary(employee))
				.titles(getTitle(employee))
				.employeeDepartments(getEmployeeDepartments(employee))
				.managerDepartments(getManagerDepartments(employee))
				.build();

	}

	private static List<Department_ManagerDTO> getManagerDepartments(Employee employee) {
		List<Department_ManagerDTO> department_ManagerDTOs = new ArrayList<>();
		List<Department_Manager> dept_manager = employee.getDept_manager();
		
		for (Department_Manager department_Manager : dept_manager) {
			department_ManagerDTOs.add(Department_ManagerDTO.builder()
					.fromDate(new Date(department_Manager.getFrom_date().getTime()))
					.toDate(new Date(department_Manager.getTo_date().getTime()))
					.departmentDTO(DepartmentDTO.builder()
							.dept_name(department_Manager.getDepartment().getDepartmentName())
							.dept_no(department_Manager.getDepartment().getDept_no())
							.build())
					.build());
		}
		
		return department_ManagerDTOs;
	}

	private static List<Department_EmployeeDTO> getEmployeeDepartments(Employee employee) {
		List<Department_EmployeeDTO> department_EmployeeDTOs = new ArrayList<>();
		List<Department_Employee> dep_emp = employee.getDep_emp();
		for (Department_Employee department_Employee : dep_emp) {
			department_EmployeeDTOs.add(Department_EmployeeDTO.builder()
					.fromDate(department_Employee.getFrom_date())
					.toDate(department_Employee.getTo_date())
					.departmentDTO(DepartmentDTO.builder()
							.dept_name(department_Employee.getDepartment().getDepartmentName())
							.dept_no(department_Employee.getDepartment().getDept_no())
							.build())
					.build());
		}
		return department_EmployeeDTOs;
	}

	private static List<SalaryDTO> getSalary(Employee employee) {
		List<SalaryDTO> salariesList = new ArrayList<>();
		List<Salary> salaries = employee.getSalary();
		if (salaries != null) {
			for (Salary salary : salaries) {
				salariesList.add(SalaryDTO.builder()
						.fromDate(new Date(salary.getFrom_date().getTime()))
						.toDate(new Date(salary.getTo_date().getTime()))
						.amount(salary.getSalary().longValue())
						.build());
			}
		}
		return salariesList;
	}

	private static List<TitleDTO> getTitle(Employee employee) {
		List<TitleDTO> titlesList = new ArrayList<>();
		List<Titles> titles = employee.getTitles();
		if (titles != null) {
			for (Titles title : titles) {
				titlesList.add(TitleDTO.builder()
						.fromDate(new Date(title.getFrom_date().getTime()))
						.toDate(new Date(title.getFrom_date().getTime()))
						.titleName(title.getTitle())
						.build());
			}
		}

		return titlesList;
	}

	public static Employee updateContents(EmployeeDTO dto, Employee employee) {
		
		employee.setBirth_date(new Date(dto.getBirth_date().getTime()));
		employee.setFirst_name(dto.getFirst_name());
		employee.setGender(dto.getGender());
		employee.setLast_name(dto.getLast_name());
		employee.setHire_date(new Date(dto.getHire_date().getTime()));
		
		
		return employee;
		
	}

}
