package com.paolo.springboot.controller;

import com.paolo.springboot.entity.Department;
import com.paolo.springboot.error.DepartmentNotFoundException;
import com.paolo.springboot.service.DepartmentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    /**
     * Dependency injection with Autowired
     * Basically Spring gets the Bean of DepartmentService and injects it here
     * We can have:
     * - constructor Injection (PREFERRED WAY over internet!)
     * <p>
     * - parameter Injection (THIS CASE HERE)
     * - setter Injection
     */
    @Autowired
    private DepartmentService departmentService;

    /**
     * Guide to various configurations possible
     * https://www.baeldung.com/spring-boot-logging
     */
    private final Logger LOGGER =
            LoggerFactory.getLogger(DepartmentController.class);

    /* Constructor Injection
     @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    */

    /* Setter Injection
    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    */


    @GetMapping("/departments")
    public List<Department> getDepartments() {
        return departmentService.getDepartments();
    }

    @GetMapping("/departments/{id}")
    public Department getDepartmentById(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
        return departmentService.getDepartmentById(departmentId);
    }

    @GetMapping("/departments/name/{name}")
    public List<Department> getDepartmentByName(@PathVariable String name) {
        return departmentService.getDepartmentByName(name);
    }

    @PostMapping("/departments")
    public Department saveDepartment(@Valid @RequestBody Department department) {
        departmentService.saveDepartment(department);
        LOGGER.info("Saved new Department " + department.toString());
        return department;
    }

    @DeleteMapping("/departments/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId) {
        departmentService.deleteDepartmentById(departmentId);
        return "Succesfully deleting Department " + departmentId;
    }

    @PutMapping("/departments/{id}")
    public Department updateDepartmentById(@PathVariable("id") Long departmentId, @RequestBody Department department) {
        return departmentService.updateDepartmentById(departmentId, department);
    }


    @GetMapping("/departments/customdepartment")
    public Department getCustomDepartmentRequest() {
        return departmentService.getCustomDepartment();
    }


}
