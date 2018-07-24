package com.dev.finalproject.repository;

import com.dev.finalproject.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(nativeQuery = true, value = "select * from employee where state_id = (select id from state where name like '%working%')")
    List<Employee> findWorking();

    Employee findByEmail(String email);
}
