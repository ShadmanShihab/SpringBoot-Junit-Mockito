package com.app.springbootjunit5mockito.service;

import com.app.springbootjunit5mockito.enumeration.Race;
import com.app.springbootjunit5mockito.model.Movie;
import com.app.springbootjunit5mockito.model.TolkienCharacter;
import org.apache.tomcat.util.bcel.classfile.ClassFormatException;
import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.List;

import static com.app.springbootjunit5mockito.enumeration.Race.*;
import static java.time.Duration.ofSeconds;
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
        TolkienCharacter frodo = dataService.getFellowshipCharacter("Frodo");
        assertNotNull(frodo);
    }

    @Test
    void ensureThatFrodoAndGandalfArePartOfTheFellowsip() {
        List<TolkienCharacter> fellowship = dataService.getFellowship();
        TolkienCharacter frodo = dataService.getFellowshipCharacter("Frodo");
        // TODO check that Frodo and Gandalf are part of the fellowship
        assertNotNull(fellowship.contains(frodo), "Fellowship should contain Frodo");
    }

    @Test
    void ensureThatOneRingBearerIsPartOfTheFellowship() {

        List<TolkienCharacter> fellowship = dataService.getFellowship();

        // TODO test that at least one ring bearer is part of the fellowship
        fail("not yet implemented");
    }

    // TODO Use @RepeatedTest(int) to execute this test 1000 times
    @RepeatedTest(1000)
    @Tag("slow")
    @DisplayName("Minimal stress testing: run this test 1000 times to ")
    void ensureThatWeCanRetrieveFellowshipMultipleTimes() {
        dataService = new DataService();
        assertNotNull(dataService.getFellowship());
    }

    @Test
    void ensureOrdering() {
        List<TolkienCharacter> fellowship = dataService.getFellowship();

        // ensure that the order of the fellowship is:
        // frodo, sam, merry,pippin, gandalf,legolas,gimli,aragorn,boromir
        assertEquals(dataService.getFellowshipCharacter("Frodo"), fellowship.get(0));
    }

    @Test
    void ensureAge() {
        List<TolkienCharacter> fellowship = dataService.getFellowship();

        // TODO test ensure that all hobbits and men are younger than 100 years
        assertTrue(
                fellowship.stream().filter(fellow -> fellow.getRace().equals(HOBBIT) ||
                        fellow.getRace().equals(MAN))
                        .allMatch(fellow -> fellow.age < 100)
        );
        // TODO also ensure that the elfs, dwars the maia are all older than 100 years
        assertTrue(
                fellowship.stream().filter(fellow -> fellow.getRace().equals(ELF) ||
                        fellow.getRace().equals(DWARF))
                        .allMatch(fellow -> fellow.age > 100)
        );
    }

    @Test
    void ensureThatFellowsStayASmallGroup() {
        List<TolkienCharacter> fellowship = dataService.getFellowship();

        // TODO Write a test to get the 20 element from the fellowship throws an
        // IndexOutOfBoundsException
        assertThrows(IndexOutOfBoundsException.class, () -> fellowship.get(20));
    }

    @Test
    public void ensureServiceDoesNotRunToLong() {
        assertTimeout(Duration.ofSeconds(2), () -> dataService.update());
    }
}
