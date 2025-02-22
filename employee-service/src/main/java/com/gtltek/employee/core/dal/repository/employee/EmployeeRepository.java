package com.gtltek.employee.core.dal.repository.employee;

import com.gtltek.employee.core.dal.model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    @Query("select e from Employee e where e.employeeNumber = :employeeNumber")
    Employee findByEmployeeNumber(@Param("employeeNumber") String employeeNumber);

    @Query("SELECT e FROM Employee e WHERE LOWER(e.firstName) LIKE LOWER(concat('%', :name, '%')) OR LOWER(e.lastName) LIKE LOWER(concat('%', :name, '%'))")
    List<Employee> searchByFirstNameOrLastName(@Param("name") String name);

}
