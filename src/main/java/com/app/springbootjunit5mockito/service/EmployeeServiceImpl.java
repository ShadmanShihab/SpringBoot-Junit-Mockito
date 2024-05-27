package com.app.springbootjunit5mockito.service;

import com.app.springbootjunit5mockito.model.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends EmployeeService {
    public Employee getEmployeeById(Long id) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName("John");
        employee.setSecondName("doe");

        return employee;
    }
}
