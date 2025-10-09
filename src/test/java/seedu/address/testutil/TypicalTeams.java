package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.team.Team;

/**
 * A utility class containing a list of {@code Team} objects to be used in tests.
 */
public class TypicalTeams {
    public static final Person TOP_PLAYER_A = new PersonBuilder().withName("Top Player A")
            .withRole("top").withChampion("Garen").withRank("gold").build();
    public static final Person JUNGLE_PLAYER_A = new PersonBuilder().withName("Jungle Player A")
            .withRole("jungle").withChampion("Lee Sin").withRank("platinum").build();
    public static final Person MID_PLAYER_A = new PersonBuilder().withName("Mid Player A")
            .withRole("mid").withChampion("Ahri").withRank("diamond").build();
    public static final Person ADC_PLAYER_A = new PersonBuilder().withName("ADC Player A")
            .withRole("adc").withChampion("Jinx").withRank("gold").build();
    public static final Person SUPPORT_PLAYER_A = new PersonBuilder().withName("Support Player A")
            .withRole("support").withChampion("Thresh").withRank("platinum").build();

    public static final Person TOP_PLAYER_B = new PersonBuilder().withName("Top Player B")
            .withRole("top").withChampion("Darius").withRank("silver").build();
    public static final Person JUNGLE_PLAYER_B = new PersonBuilder().withName("Jungle Player B")
            .withRole("jungle").withChampion("Nidalee").withRank("gold").build();
    public static final Person MID_PLAYER_B = new PersonBuilder().withName("Mid Player B")
            .withRole("mid").withChampion("Zed").withRank("platinum").build();
    public static final Person ADC_PLAYER_B = new PersonBuilder().withName("ADC Player B")
            .withRole("adc").withChampion("Kai'Sa").withRank("gold").build();
    public static final Person SUPPORT_PLAYER_B = new PersonBuilder().withName("Support Player B")
            .withRole("support").withChampion("Leona").withRank("silver").build();

    private static final List<Person> TEAM_A_ROSTER = new ArrayList<>(Arrays.asList(
            TOP_PLAYER_A, JUNGLE_PLAYER_A, MID_PLAYER_A, ADC_PLAYER_A, SUPPORT_PLAYER_A));

    public static final Team TEAM_A = new TeamBuilder().withPersons(
            TOP_PLAYER_A, JUNGLE_PLAYER_A, MID_PLAYER_A, ADC_PLAYER_A, SUPPORT_PLAYER_A).build();

    private static final List<Person> TEAM_B_ROSTER = new ArrayList<>(Arrays.asList(
            TOP_PLAYER_B, JUNGLE_PLAYER_B, MID_PLAYER_B, ADC_PLAYER_B, SUPPORT_PLAYER_B));

    public static final Team TEAM_B = new TeamBuilder().withPersons(
            TOP_PLAYER_B, JUNGLE_PLAYER_B, MID_PLAYER_B, ADC_PLAYER_B, SUPPORT_PLAYER_B).build();

    private TypicalTeams() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons and a typical team.
     * This is useful for integration tests.
     */
    public static AddressBook getTypicalAddressBookWithTeams() {
        AddressBook ab = new AddressBook();
        // Add the players to the address book first
        for (Person person : TEAM_A_ROSTER) {
            ab.addPerson(person);
        }
        // Then add the team
        ab.addTeam(TEAM_A);
        return ab;
    }

    /**
     * Returns a list of all persons who are part of the typical teams.
     * Useful for storage tests.
     */
    public static List<Person> getTypicalTeamPlayers() {
        return new ArrayList<>(TEAM_A_ROSTER);
    }
}
