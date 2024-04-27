package com.app.springbootjunit5mockito.mockitoExample;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class MockitoSpyWithListTest {
    @Spy
    List<String> spy = new LinkedList<>();

    @BeforeEach
    void setup() {
        // Alternative way of creating a spy
        // List<String> list = new LinkedList<>();
        // List<String> spy = spy(list);
    }

    @Test
    void testLinkedListSpyCorrect() {

        // when(spy.get(0)).thenReturn("foo");
        // would not work as the delegate it called so spy.get(0)
        // throws IndexOutOfBoundsException (list is still empty)

        // you have to use doReturn() for stubbing
//        doReturn("foo").when(spy).get(0);
//
//        assertEquals("foo", spy.get(0));
    }

    @Test
    void ensureSpyForListWorks() {
        var list = new ArrayList<String>();
        var spiedList = spy(list);

        doReturn("42").when(spiedList).get(99);
        String value = (String) spiedList.get(99);

        assertEquals("42", value);
    }
}
