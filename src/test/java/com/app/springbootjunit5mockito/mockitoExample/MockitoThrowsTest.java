package com.app.springbootjunit5mockito.mockitoExample;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class MockitoThrowsTest {
    @Test
    void testMockitoThrows() {
        Properties properties = Mockito.mock(Properties.class);
        when(properties.get(Mockito.anyString())).thenThrow(new IllegalArgumentException("Stuff"));
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> properties.get("any string"));
        assertEquals("Stuff", exception.getMessage());
    }

    @Test
    public void testForIOException() throws IOException {
        OutputStream mockStream = Mockito.mock(OutputStream.class);
        doThrow(new IOException()).when(mockStream).close();
        OutputStreamWriter streamWriter = new OutputStreamWriter(mockStream);
        assertThrows(IOException.class, () -> streamWriter.close());
    }
}
