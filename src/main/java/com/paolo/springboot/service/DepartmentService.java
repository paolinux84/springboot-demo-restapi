package com.paolo.springboot.service;

import com.paolo.springboot.entity.Department;
import com.paolo.springboot.error.DepartmentNotFoundException;

import java.util.List;

public interface DepartmentService {
    Department saveDepartment(Department department);


    List<Department> getDepartments();

    Department getDepartmentById(Long departmentId) throws DepartmentNotFoundException;

    void deleteDepartmentById(Long departmentId);

    Department updateDepartmentById(Long departmentId, Department department);

    List<Department> getDepartmentByName(String name);

    Department getCustomDepartment();
}
