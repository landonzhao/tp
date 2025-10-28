package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.team.Team;
import seedu.address.model.team.exceptions.DuplicateChampionException;
import seedu.address.model.team.exceptions.DuplicateRoleException;
import seedu.address.model.team.exceptions.InvalidTeamSizeException;

/**
 * Creates a new {@code Team} consisting of exactly five existing players
 * already stored in SummonersBook.
 * <p>
 * Ensures that:
 * <ul>
 *   <li>All provided players exist in the model.</li>
 *   <li>No duplicate names are provided.</li>
 *   <li>No player is reused from another team.</li>
 *   <li>Team satisfies role and champion uniqueness rules.</li>
 * </ul>
 */
public class MakeGroupCommand extends Command {

    public static final String COMMAND_WORD = "makegroup";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a team of 5 players. "
            + "All players must already exist in SummonersBook.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME1 "
            + PREFIX_NAME + "NAME2 "
            + PREFIX_NAME + "NAME3 "
            + PREFIX_NAME + "NAME4 "
            + PREFIX_NAME + "NAME5\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Alice "
            + PREFIX_NAME + "Bob "
            + PREFIX_NAME + "Cathy "
            + PREFIX_NAME + "Derek "
            + PREFIX_NAME + "Ella";

    public static final String MESSAGE_SUCCESS = "New team created: %1$s";
    public static final String MESSAGE_INSUFFICIENT_PLAYERS = "Exactly 5 player names must be provided.";
    public static final String MESSAGE_DUPLICATE_NAMES = "Duplicate player names found in the input.";
    public static final String MESSAGE_PLAYER_NOT_FOUND = "Player '%1$s' does not exist in SummonersBook.";
    public static final String MESSAGE_REUSED_PLAYERS = "Some players are already in other teams.";

    private final List<Name> playerNames;

    /**
     * Constructs a {@code MakeGroupCommand} to create a team with the given list of player names.
     *
     * @param playerNames The names of players to form a team. Must not be {@code null}.
     */
    public MakeGroupCommand(List<Name> playerNames) {
        requireNonNull(playerNames);
        this.playerNames = playerNames;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        validatePlayerCount();
        validateNoDuplicateNames();

        Set<Person> teamMembers = fetchValidPlayers(model);
        Team newTeam = createValidatedTeam(teamMembers);

        model.addTeam(newTeam);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(newTeam)));
    }

    /**
     * Ensures exactly 5 player names were provided.
     *
     * @throws CommandException If count is not 5.
     */
    private void validatePlayerCount() throws CommandException {
        if (playerNames.size() != 5) {
            throw new CommandException(MESSAGE_INSUFFICIENT_PLAYERS);
        }
    }

    /**
     * Ensures all provided player names are unique.
     *
     * @throws CommandException If duplicate names exist in the input.
     */
    private void validateNoDuplicateNames() throws CommandException {
        Set<Name> uniqueNames = new HashSet<>(playerNames);
        if (uniqueNames.size() < playerNames.size()) {
            throw new CommandException(MESSAGE_DUPLICATE_NAMES);
        }
    }

    /**
     * Retrieves all players corresponding to the given names and validates they exist and are unused.
     *
     * @param model The application model.
     * @return A set of validated {@code Person} objects ready to form a team.
     * @throws CommandException If a player does not exist or is already in another team.
     */
    private Set<Person> fetchValidPlayers(Model model) throws CommandException {
        Set<Person> teamMembers = new HashSet<>();

        for (Name name : playerNames) {
            Optional<Person> personOpt = model.findPersonByName(name);
            if (personOpt.isEmpty()) {
                throw new CommandException(String.format(MESSAGE_PLAYER_NOT_FOUND, name.fullName));
            }

            Person person = personOpt.get();
            if (model.isPersonInAnyTeam(person)) {
                throw new CommandException(String.format(MESSAGE_REUSED_PLAYERS, name.fullName));
            }

            teamMembers.add(person);
        }

        return teamMembers;
    }

    /**
     * Creates and validates a team using the given members.
     *
     * @param teamMembers A set of validated players.
     * @return A newly created {@code Team}.
     * @throws CommandException If the team violates size, role, or champion constraints.
     */
    private Team createValidatedTeam(Set<Person> teamMembers) throws CommandException {
        try {
            return new Team(new ArrayList<>(teamMembers));
        } catch (InvalidTeamSizeException | DuplicateRoleException | DuplicateChampionException e) {
            throw new CommandException(e.getMessage());
        }
    }



    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof MakeGroupCommand
                && playerNames.equals(((MakeGroupCommand) other).playerNames));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("playerNames", playerNames)
                .toString();
    }
}
