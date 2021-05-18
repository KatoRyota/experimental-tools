package com.example.employeesapp.repository;

import com.example.employeesapp.entity.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
}
