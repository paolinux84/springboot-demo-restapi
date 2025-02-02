package com.paolo.springboot.repository;

import com.paolo.springboot.entity.Department;

//definisce un repository custom
//poi questa interfaccia verra fatta ereditare da quella standard usata per l'entita voluta
public interface CustomDepartmentRepository {
    Department findCustomMethod();

}
