package com.illiapinchuk.learntests.service;

import com.illiapinchuk.learntests.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> findEmployeeForSalary(Integer salary);
    Optional<Employee> findById(Long id);
    List<Employee> findAll();
    Employee save(Employee widget);
    void deleteById(Long id);
}
