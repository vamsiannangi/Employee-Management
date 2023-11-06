package com.DepEmp.Department.Service;

import com.DepEmp.Department.Entity.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Long id, Employee employee);
    ResponseEntity<String> deleteEmployee(Long id);
    Employee getEmployeeByName(String firstName);

}
