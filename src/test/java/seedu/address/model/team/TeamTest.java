package seedu.address.model.team;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;

public class TeamTest {

    private static final List<Person> VALID_FIVE_PERSONS = Arrays.asList(ALICE, BOB, CARL, DANIEL, ELLE);
    private static final List<Person> INVALID_FOUR_PERSONS = Arrays.asList(ALICE, BOB, CARL, DANIEL);
    private static final List<Person> INVALID_SIX_PERSONS = Arrays.asList(ALICE, BOB, CARL, DANIEL, ELLE, FIONA);

    @Test
    public void constructor_validPersons_success() {
        Team team = new Team(VALID_FIVE_PERSONS);
        assertEquals(VALID_FIVE_PERSONS, team.getPersons());
    }

    @Test
    public void constructor_invalidPersonCount_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Team(INVALID_FOUR_PERSONS));
        assertThrows(IllegalArgumentException.class, () -> new Team(INVALID_SIX_PERSONS));
    }

    @Test
    public void constructor_nullPersons_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Team((List<Person>) null));
    }

    @Test
    public void hasPerson_existingPerson_returnsTrue() {
        Team team = new Team(VALID_FIVE_PERSONS);
        assertTrue(team.hasPerson(ALICE));
        assertTrue(team.hasPerson(BOB));
    }

    @Test
    public void hasPerson_nonExistingPerson_returnsFalse() {
        Team team = new Team(VALID_FIVE_PERSONS);
        assertFalse(team.hasPerson(FIONA));
    }



    @Test
    public void equals_sameTeam_returnsTrue() {
        Team team = new Team("test-id", VALID_FIVE_PERSONS);
        Team otherTeam = new Team("test-id", VALID_FIVE_PERSONS);
        assertTrue(team.equals(otherTeam));
    }

    @Test
    public void equals_differentTeam_returnsFalse() {
        Team team = new Team("test-id-1", VALID_FIVE_PERSONS);
        Team otherTeam = new Team("test-id-2", VALID_FIVE_PERSONS);
        assertFalse(team.equals(otherTeam));
    }

    @Test
    public void toString_validTeam_correctFormat() {
        Team team = new Team("test-id", VALID_FIVE_PERSONS);
        String result = team.toString();
        
        assertTrue(result.contains("test-id"));
        for (Person person : VALID_FIVE_PERSONS) {
            assertTrue(result.contains(person.toString()));
        }
    }
}
