package com.employees.infrastructure;

import com.employees.domain.Employee;
import com.employees.domain.EmployeeRepository;

import java.util.List;

public class InMemoryEmployeeRepository implements EmployeeRepository {
    private final List<Employee> employees;

    public InMemoryEmployeeRepository(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public List<Employee> getEmployees() {
        return employees;
    }
}
