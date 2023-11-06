package com.DepEmp.Department.Service;

import com.DepEmp.Department.Entity.Department;
import com.DepEmp.Department.Repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department createDepartment(Department department) {
        department.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        department.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(String departmentId) {
        return departmentRepository.findById(departmentId).orElse(null);
    }
    @Override
    public Department updateDepartment(String id, Department departmentDetails) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isPresent()) {
            Department existingDepartment = optionalDepartment.get();
            existingDepartment.setDepartmentName(departmentDetails.getDepartmentName());
            existingDepartment.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            return departmentRepository.save(existingDepartment);
        }
        return null;
    }

    @Override
    public void deleteDepartment(String id) {
        departmentRepository.deleteById(id);
    }
}


