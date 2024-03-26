package com.app.springbootjunit5mockito;

import com.app.springbootjunit5mockito.model.Calculator;
import com.app.springbootjunit5mockito.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import static java.time.Duration.ofMinutes;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    Calculator calculator;
    User user;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
        user = new User();
    }

    @Test
    @DisplayName("Simple multiplication should work")
    void simpleMultiplication() {
        assertEquals(50, calculator.multiply(10, 5), "Simple multiplication should work");
    }

    @RepeatedTest(3)
    @DisplayName("Repeated multiplication should be work")
    void repeatedMultiplication() {
        assertEquals(50, calculator.multiply(10, 5), "Repeated multiplication should work");
        assertEquals(0, calculator.multiply(0, 5), "Multiplication with zero should be zero");
    }

    /***
    ** Exception Testing
    ***/
    @Test
    void exceptionTesting() {
        // set up user
        Throwable exception = assertThrows(Exception.class, () -> {
            user.setAge(5 / 0);
        });
        assertEquals("/ by zero", exception.getMessage());
    }

    /***
     ** Multiple/Grouped Assertions
     ***/
    @Test
    void groupedAssertions() {
        User user = new User();
        assertAll("user name",
                () -> assertNull("John", user.getFirstName()),
                () -> assertEquals("David", user.getLastName()));
    }

    /***
     ** Timeout in tests
     ***/
    @Test
    void timeoutNotExceeded() {
        assertTimeout(ofSeconds(10), () -> checkMethod());
    }

    void checkMethod() throws InterruptedException {
//        Thread.sleep(20000);
        System.out.println("Time limit not exceeded");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hello", "World", "JUnit"})
    public void testStringLength(String input) {
        int length = input.length();
        assertTrue(length > 0);  // Assert that all provided strings have a length greater than 0
    }
}