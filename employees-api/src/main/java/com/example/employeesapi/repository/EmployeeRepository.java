package com.example.employeesapi.repository;


import com.example.employeesapi.entity.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
}
