package com.app.springbootjunit5mockito;

import com.app.springbootjunit5mockito.model.Calculator;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class DynamicTestCreationTest {

    @TestFactory
    Stream<DynamicTest> dynamicTestCreation() {
        Calculator calculator = new Calculator();
        int[][] data = new int[][] {
                {1, 2, 2},
                {4, 5, 20},
                {7, 8, 56}
        };

        return Arrays.stream(data).map(entry -> {
           int m1 = entry[0];
           int m2 = entry[1];
           int expected = entry[2];
           return DynamicTest.dynamicTest(m1 + " " + m2 + " = " + expected, () -> {
               assertEquals(expected, calculator.multiply(m1, m2));
           });
        });
    }

//    @ParameterizedTest
//    @ValueSource(strings = {"Hello", "World", "JUnit"})
//    public void testStringLength(String input) {
//        int length = input.length();
//        assertTrue(length > 0);  // Assert that all provided strings have a length greater than 0
//    }
}
