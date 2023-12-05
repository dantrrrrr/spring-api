package com.devtruong.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.devtruong.api.entity.EmployeeEntity;
import com.devtruong.api.model.Employee;
import com.devtruong.api.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        // Create a new instance of EmployeeEntity
        EmployeeEntity employeeEntity = new EmployeeEntity();

        // Use BeanUtils.copyProperties to copy properties from the 'employee' object to
        // 'employeeEntity'
        // This is a common way to transfer data from one object to another with
        // matching property names
        BeanUtils.copyProperties(employee, employeeEntity);

        // Save the 'employeeEntity' (mapped from 'employee') to the database using the
        // employeeRepository
        employeeRepository.save(employeeEntity);

        // Return the original 'employee' object (it might be useful to have the ID
        // assigned by the database)
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();

        List<Employee> employees = employeeEntities
                .stream()
                .map(emp -> new Employee(
                        emp.getId(),
                        emp.getFirstName(),
                        emp.getLastName(),
                        emp.getEmailId()))
                .collect(Collectors.toList());
        return employees;
    }

    @Override
    public boolean deleteEmployee(Long id) {
        // find emp by the id
        EmployeeEntity employee = employeeRepository.findById(id).get();
        // delete the employee find
        employeeRepository.delete(employee);

        return true;
    }

    @Override
    public Employee getEmployee(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();

        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeEntity, employee);// conver enity to normal Employee
        return employee;
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();

        employeeEntity.setEmailId(employee.getEmailId());
        employeeEntity.setFirstName(employee.getFirstName());
        employeeEntity.setLastName(employee.getLastName());

        employeeRepository.save(employeeEntity);
        Employee employeeUpdate = new Employee();
        BeanUtils.copyProperties(employeeEntity, employeeUpdate);

        return employeeUpdate;
    }

}
