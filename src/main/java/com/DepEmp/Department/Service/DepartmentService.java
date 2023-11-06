package com.DepEmp.Department.Service;

import com.DepEmp.Department.Entity.Department;
import java.util.List;
public interface DepartmentService {
    Department createDepartment(Department department);
    List<Department> getAllDepartments();
    Department getDepartmentById(String id);
    Department updateDepartment(String id, Department departmentDetails);
    void deleteDepartment(String id);
}


