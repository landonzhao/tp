package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RankTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Rank(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Rank(invalidName));
    }

    @Test
    public void isValidRank() {
        // invalid name
        assertFalse(Rank.isValidRank("")); // empty string
        assertFalse(Rank.isValidRank(" ")); // spaces only
        assertFalse(Rank.isValidRank("peter")); // wrong name

        // valid name
        assertTrue(Rank.isValidRank("challenger")); // alphanumeric name

    }

    @Test
    public void equals() {
        Rank rank = new Rank("gold");

        // same values -> returns true
        assertTrue(rank.equals(new Rank("gold")));

        // same object -> returns true
        assertTrue(rank.equals(rank));

        // null -> returns false
        assertFalse(rank.equals(null));

        // different types -> returns false
        assertFalse(rank.equals(5.0f));

        // different values -> returns false
        assertFalse(rank.equals(new Rank("silver")));
    }
}
