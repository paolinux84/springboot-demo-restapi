package com.paolo.springboot.controller;

import com.paolo.springboot.entity.Department;
import com.paolo.springboot.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DepartmentService service;

    private Department department;

    @BeforeEach
    void setUp() {
        department = Department.builder()
                .departmentId(5L)
                .name("IT")
                .code("CODE")
                .address("ITALY")
                .build();
    }

    @Test
    void saveDepartments() throws Exception {
        Department inputDepartment = Department.builder()
                .name("IT")
                .code("CODE")
                .address("ITALY")
                .build();

        Mockito.when(service.saveDepartment(inputDepartment))
                .thenReturn(department);


        mockMvc.perform(post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(" {\n" +
                        "            \"name\": \"IT\",\n" +
                        "            \"address\": \"ITALY\",\n" +
                        "            \"code\": \"CODE\"\n" +
                        "        }")
                ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getDepartmentById() throws Exception {

        Mockito.when(service.getDepartmentById(5L))
                .thenReturn(department);

        mockMvc.perform(get("/departments/5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name")
                        .value(department.getName()));


    }
}