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

    /**
     * Enum representing the different ranked tiers in League of Legends.
     * Each tier has an associated skill level from 1 (lowest) to 10 (highest).
     * <p>
     * The tiers include:
     * <ul>
     *     <li>IRON</li>
     *     <li>BRONZE</li>
     *     <li>SILVER</li>
     *     <li>GOLD</li>
     *     <li>PLATINUM</li>
     *     <li>EMERALD</li>
     *     <li>DIAMOND</li>
     *     <li>MASTER</li>
     *     <li>GRANDMASTER</li>
     *     <li>CHALLENGER</li>
     * </ul>
     * <p>
     * Provides a utility method {@link #fromString(String)} to convert a string representation
     * of a rank to its corresponding enum constant. The conversion is case-insensitive.
     * If an invalid rank string is provided, {@link IllegalArgumentException} is thrown.
     */
    public enum RankTier {
        IRON, BRONZE, SILVER, GOLD, PLATINUM, EMERALD, DIAMOND, MASTER, GRANDMASTER, CHALLENGER;

        /**
         * Returns the corresponding RankTier for a string (case-insensitive).
         * @throws IllegalArgumentException if input is invalid.
         */
        public static RankTier fromString(String name) {
            for (RankTier r : RankTier.values()) {
                if (r.name().equalsIgnoreCase(name)) {
                    return r;
                }
            }
            throw new IllegalArgumentException("Invalid rank: " + name);
        }

        /**
         * Checks if the string is a valid rank tier.
         */
        public static boolean isValid(String name) {
            for (RankTier r : RankTier.values()) {
                if (r.name().equalsIgnoreCase(name)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public String toString() {
            // Capitalize first letter, rest lowercase
            String n = name();
            return n.substring(0, 1).toUpperCase() + n.substring(1).toLowerCase();
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
