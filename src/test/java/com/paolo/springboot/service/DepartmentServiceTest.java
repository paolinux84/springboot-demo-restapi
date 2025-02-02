package com.paolo.springboot.service;

import com.paolo.springboot.entity.Department;
import com.paolo.springboot.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        List<Department> departments = new ArrayList<>();
        Department department = Department.builder().name("IT").build();
        departments.add(department);

        Mockito.when(departmentRepository.findByNameNativeQuery(department.getName())).thenReturn(departments);

        Department customDepartment = Department.builder()
                .departmentId(1L)
                .code("123")
                .name("Test Custom Department")
                .address("via Test")
                .build();
        Mockito.when(departmentRepository.findCustomMethod()).thenReturn(customDepartment);
    }

    @Test
    //@Disabled --> replacing the @Ignore annotation to skip test
    @DisplayName("Testing Department found case")
    void whenValidDepartmentName_thenDepartmentShouldFound() {
        String departmentName = "IT";
        List<Department> found = departmentService.getDepartmentByName(departmentName);

        assertEquals(departmentName, found.get(0).getName());
    }

    @Test
    @DisplayName("Testing Custom Department find")
    void whenCallingGetCustomDepartment_thenCustomDepartmentShouldBeReturned(){
        Department department = departmentService.getCustomDepartment();

        assertEquals(department.getDepartmentId(), 1L);

    }
}