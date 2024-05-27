package com.app.springbootjunit5mockito.aop.logging;

import com.app.springbootjunit5mockito.model.Employee;
import com.app.springbootjunit5mockito.service.EmployeeService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EmployeeServiceAspect {

    static Logger log = LoggerFactory.getLogger(EmployeeServiceAspect.class);


    @Before(value = "execution(* com.app.springbootjunit5mockito.service.EmployeeService.*(..)) && args(fname, lname)")
    public void beforeAdvice(JoinPoint joinPoint, String fname, String lname) {
        System.out.println("Before Advice " + joinPoint.getSignature());
        log.info("Employee Name : " + fname + " " + lname);
    }

    @Pointcut(value = "execution(* com.app.springbootjunit5mockito.service.EmployeeService.*(..)) && args(fname, sname)")
    public void logCreateEmployee(String fname, String sname) {
    }

    @After("logCreateEmployee(fname, sname)")
    public void afterAdvice(JoinPoint joinPoint, String fname, String sname) {
        System.out.println("After method: " + joinPoint.getSignature());
        System.out.println("Creating Employee with first name - " + fname + ", second name - " + sname);
    }

    @Pointcut(value= "execution(* com.app.springbootjunit5mockito.service.EmployeeService.*(..)) && args(id)")
    public void logDisplaySalary(Long id) {
    }

    @Around(value = "logDisplaySalary(id)")
    public void aroundAdvice(ProceedingJoinPoint joinPoint, Long id) throws Throwable {
        System.out.println("The method aroundAdvice() before invokation of the method " + joinPoint.getSignature().getName() + " method");
        try
        {
            joinPoint.proceed();
        }
        finally
        {

        }
        System.out.println("The method aroundAdvice() after invokation of the method " + joinPoint.getSignature().getName() + " method");
    }

    @AfterReturning(value = "execution(* com.app.springbootjunit5mockito.service.EmployeeServiceImpl.*(..)) && args(id)", returning = "employee")
    public void afterReturningAdvice(JoinPoint joinPoint, Long id, Employee employee) {
        System.out.println("after returning " + joinPoint.getSignature().getName() + " method");
        System.out.println(employee.toString());
    }

    @AfterThrowing(value = "execution(* com.app.springbootjunit5mockito.service.EmployeeService.*(..)) && args(fname)", throwing = "e")
    public void afterThrowingAdvice(JoinPoint joinPoint, Exception e, String fname) {
        log.info("After throwing exception in method " + joinPoint.getSignature().getName());
    }
}
