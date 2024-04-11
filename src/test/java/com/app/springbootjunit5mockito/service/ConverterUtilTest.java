package com.app.springbootjunit5mockito.service;

import com.app.springbootjunit5mockito.model.util.ConverterUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class ConverterUtilTest {
    int[][] celsiusFahrenheitMapping = new int[][] { { 10, 50 }, { 40, 104 }, { 0, 32 } };

    ConverterUtil converterUtil;

    @BeforeEach
    void setUp() {
        converterUtil = new ConverterUtil();
    }

    @TestFactory
    public Stream<DynamicTest> ensureThatCelsiumConvertsToFahrenheit() {
        int[][] data = new int[][] { { 10,50 }, { 40, 104}, { 0, 32 } };
        return Arrays.stream(data).map(entry -> {
            int celsius = entry[0];
            int fahrenheit = entry[1];
            return dynamicTest(celsius + " Celsius are " + fahrenheit, () -> {
                assertEquals(fahrenheit, converterUtil.convertCelsiusToFahrenheit(celsius));
            });
        });
    }

    @TestFactory
    Stream<DynamicTest> ensureThatFahrenheitToCelsiumConverts() {
        int[][] data = new int[][] { { 10,50 }, { 40, 104}, { 0, 32 } };
        return Arrays.stream(data).map(entry -> {
            int celsius = entry[0];
            int fahrenheit = entry[1];
            return DynamicTest.dynamicTest("convert faenheit to celsius", () ->
                    assertEquals(celsius, converterUtil.convertFahrenheitToCelsius(fahrenheit)));
        });
    }
}
