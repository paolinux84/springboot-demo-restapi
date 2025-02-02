package com.paolo.springboot.repository;

import com.paolo.springboot.entity.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import static java.lang.Long.valueOf;

public class CustomDepartmentRepositoryImpl implements CustomDepartmentRepository {

    @PersistenceContext
    private EntityManager em;
    @Override
    public Department findCustomMethod() {
        return em.find(Department.class, valueOf(1));
    }
}
