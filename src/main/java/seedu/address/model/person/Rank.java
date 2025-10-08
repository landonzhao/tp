package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Rank {

    public static final String MESSAGE_CONSTRAINTS =
            "Rank should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX =
            "^(Iron|Bronze|Silver|Gold|Platinum|Emerald|Diamond|Master|Grandmaster|Challenger)$";

    public enum RankTier {
        IRON(1),
        BRONZE(2),
        SILVER(3),
        GOLD(4),
        PLATINUM(5),
        EMERALD(6),
        DIAMOND(7),
        MASTER(8),
        GRANDMASTER(9),
        CHALLENGER(10);

        private final int skillLevel;

        RankTier(int skillLevel) {
            this.skillLevel = skillLevel;
        }

        public int getSkillLevel() {
            return skillLevel;
        }

        // Case-sensitive exact match
        public static RankTier fromString(String name) {
            for (RankTier r : RankTier.values()) {
                if (r.name().equalsIgnoreCase(name)) {
                    return r;
                }
            }
            throw new IllegalArgumentException("Invalid rank: " + name);
        }
    }

    public final RankTier rankTier;

    /**
     * Constructs a {@code Rank}.
     *
     * @param rank A valid rank.
     */
    public Rank(String rank) {
        requireNonNull(rank);
        checkArgument(isValidName(rank), MESSAGE_CONSTRAINTS);
        this.rankTier = RankTier.fromString(rank);
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return rankTier.name().substring(0, 1).toUpperCase()
                + rankTier.name().substring(1).toLowerCase();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Rank
                && rankTier == ((Rank) other).rankTier;
    }

    @Override
    public int hashCode() {
        return rankTier.hashCode();
    }
}
