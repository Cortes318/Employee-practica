package com.service.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.service.employee.model.Employee;
public interface EmployeeRepository extends JpaRepository<Employee,Integer>{
}
