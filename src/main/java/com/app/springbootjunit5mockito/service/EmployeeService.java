package com.app.springbootjunit5mockito.service;

import com.app.springbootjunit5mockito.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    static Logger log = LoggerFactory.getLogger(EmployeeService.class);

    public Employee addEmployee(String firstName, String lastName) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setSecondName(lastName);

        return employee;
    }

    public boolean deleteEmployee(Long id) {
        Employee employee = new Employee();
        employee.setId(id);
//        log.info("Deleting employee with id: " + id);
        return true;
    }

    public Long displaySalary(Long id) {
        log.info("Employee id: " + id);
        log.info("Salary is : 10000");
        return 10000L;
    }

    public Employee find(String fname) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
