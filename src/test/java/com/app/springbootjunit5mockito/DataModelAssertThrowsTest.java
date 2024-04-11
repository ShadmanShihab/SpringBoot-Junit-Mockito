package com.app.springbootjunit5mockito;

import com.app.springbootjunit5mockito.enumeration.Race;
import com.app.springbootjunit5mockito.model.TolkienCharacter;
import com.app.springbootjunit5mockito.service.DataService;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.app.springbootjunit5mockito.enumeration.Race.HOBBIT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DataModelAssertThrowsTest {
    @Test
    @DisplayName("Ensure that access to the fellowship throws exception outside the valid range")
    void exceptionTesting() {
        DataService dataService = new DataService();
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> dataService.getFellowship().get(20));
        assertEquals("Index 20 out of bounds for length 9", exception.getMessage());
    }

    @Test
//    @Disabled("Please fix and enable")
    public void ensureThatAgeMustBeLargerThanZeroViaSetter() {
        TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, HOBBIT);
        // use assertThrows() rule to check that the message is:
        // Age is not allowed to be smaller than zero
        Throwable exception = assertThrows(Exception.class, () -> frodo.setAge(-1));
        assertEquals("Age is not allowed to be smaller than zero", exception.getMessage());
    }

    @Test
    void ensureThatAgeMustBeLargerThanZeroViaConstructor() {
        // Age is not allowed to be smaller than zero
        //This test will run only in linux
        Assumptions.assumeTrue(System.getProperty("os.name").contains("Linux"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new TolkienCharacter("Frodo", -1, HOBBIT));
        assertEquals("Age is not allowed to be smaller than zero", exception.getMessage());
    }

}
