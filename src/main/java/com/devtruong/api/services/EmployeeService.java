package com.devtruong.api.services;

import java.util.List;

import com.devtruong.api.model.Employee;

// define method need , not logic
public interface EmployeeService {

    Employee createEmployee(Employee employee);

    List<Employee> getAllEmployees();

    boolean deleteEmployee(Long id);

    Employee getEmployee(Long id);

    Employee updateEmployee(Long id, Employee employee);

}
