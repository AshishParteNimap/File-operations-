package com.excel.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excel.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>{

}
