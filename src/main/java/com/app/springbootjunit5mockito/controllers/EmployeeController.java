package com.app.springbootjunit5mockito.controllers;

import com.app.springbootjunit5mockito.model.Employee;
import com.app.springbootjunit5mockito.service.EmployeeService;
import com.app.springbootjunit5mockito.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

//    @Autowired
//    EmployeeService employeeService;

    @Autowired
    EmployeeServiceImpl employeeService;

    @GetMapping("/firstname/{firstName}/lastname/{lastName}")
    public Employee getEmployeeByFullName(@PathVariable String firstName, @PathVariable String lastName) {
        Employee employee = employeeService.addEmployee(firstName, lastName);
        return employee;
    }

    @DeleteMapping("/{id}")
    public String removeEmployee(@RequestParam Long id) {
        if (employeeService.deleteEmployee(id)) {
            return "employee deleted";
        }
        return "employee not found";
    }

    @GetMapping("/salary/{id}")
    public Long getEmployeeSalary(@PathVariable Long id) {
        return employeeService.displaySalary(id);
    }

    @GetMapping("{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/find/{fname}")
    public Employee findEmployee(@PathVariable String fname) {
        return employeeService.find(fname);
    }
}
