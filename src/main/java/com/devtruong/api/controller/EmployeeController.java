package com.devtruong.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devtruong.api.entity.EmployeeEntity;
import com.devtruong.api.model.Employee;
import com.devtruong.api.services.EmployeeService;

import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin(origins = "http://localhost:3000")
@RestController // note as contrller rest
@RequestMapping("/api/v1/") // which route work ing on
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        System.out.println(employee);
        return employeeService.createEmployee(employee);
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        Employee employee = null;
        employee = employeeService.getEmployee(id);

        return ResponseEntity.ok(employee);

    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {

        boolean deleted = false;

        // call the service to delete the employee by id
        deleted = employeeService.deleteEmployee(id);

        // key and value types : deleted =true
        Map<String, Boolean> response = new HashMap<>();
        // add key and value to response
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);

    }

    @PutMapping("employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {

        employee = employeeService.updateEmployee(id, employee);

        return ResponseEntity.ok(employee);
    }

}