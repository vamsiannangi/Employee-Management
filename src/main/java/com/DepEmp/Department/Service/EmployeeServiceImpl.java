package com.DepEmp.Department.Service;

import com.DepEmp.Department.Entity.Employee;
import com.DepEmp.Department.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.orElse(null);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        employee.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();
            existingEmployee.setFirstName(employee.getFirstName());
            existingEmployee.setLastName(employee.getLastName());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setDepartmentId(employee.getDepartmentId());
            existingEmployee.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            return employeeRepository.save(existingEmployee);
        }
        return null;
    }

    @Override
    public Employee getEmployeeByName(String firstName) {
        return employeeRepository.findByFirstName(firstName);
    }

    public ResponseEntity<String> deleteEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employeeRepository.deleteById(id);
            return new ResponseEntity<>("Employee with ID " + id + " has been successfully deleted.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Employee with ID " + id + " does not exist.", HttpStatus.NOT_FOUND);
        }
    }
}
