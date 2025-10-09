package seedu.address.model.team;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTeams.TEAM_A;
import static seedu.address.testutil.TypicalTeams.TEAM_B;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.team.exceptions.DuplicateTeamException;
import seedu.address.model.team.exceptions.TeamNotFoundException;
import seedu.address.testutil.TeamBuilder;

public class UniqueTeamListTest {

    private final UniqueTeamList uniqueTeamList = new UniqueTeamList();

    @Test
    public void contains_nullTeam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTeamList.contains(null));
    }

    @Test
    public void contains_teamNotInList_returnsFalse() {
        assertFalse(uniqueTeamList.contains(TEAM_A));
    }

    @Test
    public void contains_teamInList_returnsTrue() {
        uniqueTeamList.add(TEAM_A);
        assertTrue(uniqueTeamList.contains(TEAM_A));
    }

    @Test
    public void contains_teamWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTeamList.add(TEAM_A);
        Team editedTeamA = new TeamBuilder(TEAM_A).build(); // Same ID
        assertTrue(uniqueTeamList.contains(editedTeamA));
    }

    @Test
    public void add_nullTeam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTeamList.add(null));
    }

    @Test
    public void add_duplicateTeam_throwsDuplicateTeamException() {
        uniqueTeamList.add(TEAM_A);
        assertThrows(DuplicateTeamException.class, () -> uniqueTeamList.add(TEAM_A));
    }

    @Test
    public void remove_nullTeam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTeamList.remove(null));
    }

    @Test
    public void remove_teamDoesNotExist_throwsTeamNotFoundException() {
        assertThrows(TeamNotFoundException.class, () -> uniqueTeamList.remove(TEAM_A));
    }

    @Test
    public void remove_existingTeam_removesTeam() {
        uniqueTeamList.add(TEAM_A);
        uniqueTeamList.remove(TEAM_A);
        UniqueTeamList expectedUniqueTeamList = new UniqueTeamList();
        assertEquals(expectedUniqueTeamList, uniqueTeamList);
    }

    @Test
    public void setTeam_nullTargetTeam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTeamList.setTeam(null, TEAM_A));
    }

    @Test
    public void setTeam_nullEditedTeam_throwsNullPointerException() {
        uniqueTeamList.add(TEAM_A);
        assertThrows(NullPointerException.class, () -> uniqueTeamList.setTeam(TEAM_A, null));
    }

    @Test
    public void setTeam_targetTeamNotInList_throwsTeamNotFoundException() {
        assertThrows(TeamNotFoundException.class, () -> uniqueTeamList.setTeam(TEAM_A, TEAM_A));
    }

    @Test
    public void setTeam_editedTeamHasDifferentIdentity_success() {
        uniqueTeamList.add(TEAM_A);
        uniqueTeamList.setTeam(TEAM_A, TEAM_B);
        UniqueTeamList expectedUniqueTeamList = new UniqueTeamList();
        expectedUniqueTeamList.add(TEAM_B);
        assertEquals(expectedUniqueTeamList, uniqueTeamList);
    }

    @Test
    public void setTeams_listWithDuplicateTeams_throwsDuplicateTeamException() {
        List<Team> listWithDuplicateTeams = Arrays.asList(TEAM_A, TEAM_A);
        assertThrows(DuplicateTeamException.class, () -> uniqueTeamList.setTeams(listWithDuplicateTeams));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueTeamList.asUnmodifiableObservableList().remove(0));
    }
}
