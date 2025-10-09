package seedu.address.model.team;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Represents a Team in the summoners book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 * A team must have exactly 5 persons with unique roles (Top, Jungle, Mid, Bottom, Support).
 */
public class Team {

    public static final int TEAM_SIZE = 5;
    public static final String MESSAGE_CONSTRAINTS = "A team must have exactly 5 persons with unique roles.";

    // Identity fields
    private final String id;

    // Data fields
    private final List<Person> persons;

    /**
     * Constructor for creating a new Team with a randomly generated unique ID.
     *
     * @param persons List of 5 persons for the team.
     */
    public Team(List<Person> persons) {
        this(UUID.randomUUID().toString(), persons);
    }

    /**
     * Constructor for creating a Team with an explicit ID.
     * This is used for deserialization from JSON to preserve the original ID.
     *
     * @param id Unique identifier for the team.
     * @param persons List of 5 persons for the team.
     */
    public Team(String id, List<Person> persons) {
        requireAllNonNull(id, persons);
        validateTeamComposition(persons);
        this.id = id;
        this.persons = new ArrayList<>(persons);
    }

    /**
     * Validates that the team has exactly 5 persons with unique roles.
     *
     * @param persons List of persons to validate.
     * @throws IllegalArgumentException if team composition is invalid.
     */
    private void validateTeamComposition(List<Person> persons) {
        if (persons.size() != TEAM_SIZE) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }

        // TODO: Check for unique
    }

    public String getId() {
        return id;
    }

    /**
     * Returns an immutable list of persons.
     */
    public List<Person> getPersons() {
        return new ArrayList<>(persons);
    }


    /**
     * Returns true if this team contains the specified person.
     */
    public boolean hasPerson(Person person) {
        return persons.contains(person);
    }

    /**
     * Returns true if both teams have the same identity and data fields.
     * This defines a stronger notion of equality between two teams.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Team)) {
            return false;
        }

        Team otherTeam = (Team) other;
        return id.equals(otherTeam.id)
                && persons.equals(otherTeam.persons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, persons);
    }

    @Override
    public String toString() {
        String personsString = persons.stream()
                                     .map(Person::toString)
                                     .collect(java.util.stream.Collectors.joining(", "));
        return new ToStringBuilder(this)
                .add("id", id)
                .add("persons", personsString)
                .toString();
    }
}
