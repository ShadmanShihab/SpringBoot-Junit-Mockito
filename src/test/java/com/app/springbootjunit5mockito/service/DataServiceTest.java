package com.app.springbootjunit5mockito.service;

import com.app.springbootjunit5mockito.model.Movie;
import com.app.springbootjunit5mockito.model.TolkienCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.app.springbootjunit5mockito.enumeration.Race.*;
import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class DataServiceTest {
    //will be initialized before each test
    DataService dataService;

    @BeforeEach
    void setUp() {
        dataService = new DataService();
    }

    @Test
    void ensureThatInitializationOfTolkeinCharactorsWorks() {
        TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, HOBBIT);

        // TODO check that age is 33
        assertTrue(frodo.age == 33, "age is 33");
        // TODO check that name is "Frodo"
        assertEquals(frodo.getName(), "Frodo", "Name should be Frodo");
        // TODO check that name is not "Frodon"
        assertNotEquals(frodo.getName(), "Frodon", "Name should not be Frodon");
//        fail("not yet implemented");
    }

    @Test
    void ensureThatEqualsWorksForCharaters() {
        Object jake = new TolkienCharacter("Jake", 43, HOBBIT);
        Object sameJake = jake;
        Object jakeClone = new TolkienCharacter("Jake", 12, HOBBIT);
        // TODO check that:
        // jake is equal to sameJake
        assertTrue(jake.equals(jakeClone), "Jake should be equal");
        assertEquals(jake, jakeClone);

        // jake is not equal to jakeClone
        assertNotEquals(jake, jakeClone);
    }

    @Test
    void checkInheritance() {
        TolkienCharacter tolkienCharacter = dataService.getFellowship().get(0);
        // TODO check that tolkienCharacter.getClass is not a movie class
        assertNotEquals(tolkienCharacter.getClass(), Movie.class, "This Class is not a movie class");
    }

    @Test
    void ensureFellowShipCharacterAccessByNameReturnsNullForUnknownCharacter() {
        // TODO implement a check that dataService.getFellowshipCharacter returns null for an unknow felllow, e.g. "Lars"
        TolkienCharacter fellowshipCharacter = dataService.getFellowshipCharacter("Lars");
        assertNull(fellowshipCharacter);
    }

    @Test
    void ensureFellowShipCharacterAccessByNameWorksGivenCorrectNameIsGiven() {
        // TODO implement a check that dataService.getFellowshipCharacter returns a fellow for an
        // existing felllow, e.g. "Frodo"
        TolkienCharacter fellowshipCharacter = dataService.getFellowshipCharacter("Frodo");
        assertNotNull(fellowshipCharacter);
    }
}
