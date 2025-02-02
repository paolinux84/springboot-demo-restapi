package com.paolo.springboot.service;

import com.paolo.springboot.entity.Department;
import com.paolo.springboot.error.DepartmentNotFoundException;
import com.paolo.springboot.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

//Service contains all services used by Controllers
//Of course it uses a lot of Repositories, which normally are injected

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> val = departmentRepository.findById(departmentId);
        if (val.isPresent())
            return val.get();
        else
            throw new DepartmentNotFoundException("Department " + departmentId + " not found");
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartmentById(Long departmentId, Department department) {
        Department departmentFromDB = departmentRepository.findById(departmentId).get();

        if (Objects.nonNull(department.getName()) && !department.getName().equals(departmentFromDB.getName())) {
            departmentFromDB.setName(department.getName());
        }

        if (Objects.nonNull(department.getCode()) && !department.getCode().equals(departmentFromDB.getCode())) {
            departmentFromDB.setCode(department.getCode());
        }

        if (Objects.nonNull(department.getAddress()) && !department.getAddress().equals(departmentFromDB.getAddress())) {
            departmentFromDB.setAddress(department.getAddress());
        }

        return departmentRepository.save(departmentFromDB);

    }

    @Override
    public List<Department> getDepartmentByName(String name) {
        //return departmentRepository.findByNameIgnoreCase(name);
        return departmentRepository.findByNameNativeQuery(name);
        //return departmentRepository.findByNameJPQL(name);
    }

    @Override
    public Department getCustomDepartment() {
        return departmentRepository.findCustomMethod();
    }


}
