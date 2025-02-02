package com.paolo.springboot.repository;

import com.paolo.springboot.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends CustomDepartmentRepository, JpaRepository<Department, Long> {

    /**
     * https://www.youtube.com/watch?v=zvR-Oif_nxg
     * At 2:52:38 stops the CRUD methods
     * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#upgrading
     */

    //TODO: Understand special naming convention MAGIC??
    // https://medium.com/@lsampath210/how-spring-instantiate-jparepository-interfaces-52e97c8875e5
    // How is in normal Jakarta: https://thorben-janssen.com/implementing-the-repository-pattern-with-jpa-and-hibernate/
    // https://vladmihalcea.com/custom-spring-data-repository/
    List<Department> findByName(String DepartmentName);

    List<Department> findByNameIgnoreCase(String DepartmentName);

    /**
     * We can use JPQL Query
     */
    @Query(value = "select d from Department d where d.name=?1", nativeQuery = false)
    List<Department> findByNameJPQL(String departmentName);

    /**
     * We can use Native Query (need to set nativeQuery = true)
     */
    @Query(value = "select * from Department where name=?1", nativeQuery = true)
    List<Department> findByNameNativeQuery(String departmentName);


}
