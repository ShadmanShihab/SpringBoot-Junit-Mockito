package com.app.springbootjunit5mockito.mockitoExample;

import com.app.springbootjunit5mockito.model.Database;
import com.app.springbootjunit5mockito.model.Service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {
    @Mock
    Database databaseMock;
    @Mock
    Iterator<String> i;
    Comparable<String> c;

    @Test
    public void testQuery()  {
        assertNotNull(databaseMock);
        when(databaseMock.isAvailable()).thenReturn(true);
        Service t  = new Service(databaseMock);
        boolean check = t.query("* from t");
        assertTrue(check);
    }

    @Test
    void ensureMockitoReturnsTheConfiguredValue() {

        // define return value for method getUniqueId()
        when(databaseMock.getUniqueId()).thenReturn(65);

        Service service = new Service(databaseMock);
        // use mock in test....
        assertEquals(service.toString(), "Using database with id: 65");
    }

    // demonstrates the return of multiple values
    @Test
    void testMoreThanOneReturnValue() {
        when(i.next()).thenReturn("Mockito").thenReturn("rocks");
        String result = i.next() + " " + i.next();
        // assert
        assertEquals("Mockito rocks", result);
    }

    @Test
    void testReturnValueDependingOnMethodParameter(@Mock Comparable<String> c) {
        when(c.compareTo("Mockito")).thenReturn(1);
        when(c.compareTo("rocks")).thenReturn(2);

        assertEquals(1, c.compareTo("Mockito"));
        assertEquals(2, c.compareTo("rocks"));
    }

    @Test
    void testReturnValueDependingOnMethodParameter2(@Mock Comparable<Integer> c) {
        when(c.compareTo(isA(Integer.class))).thenReturn(0);
        assertEquals(0, c.compareTo(isA(Integer.class)));
    }
}

