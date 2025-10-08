package seedu.address.model.person.exceptions;

/**
 * Signals that the operation encountered an invalid Rank.
 */
public class InvalidRankException extends RuntimeException {
    public InvalidRankException() {
        super("Invalid rank provided. "
                + "Rank must be one of: Iron, Bronze, Silver, Gold, Platinum, Emerald, Diamond, Master, Grandmaster, Challenger");
    }

    public InvalidRankException(String rank) {
        super("Invalid rank provided: " + rank
                + ". Rank must be one of: Iron, Bronze, Silver, Gold, Platinum, Emerald, Diamond, Master, Grandmaster, Challenger");
    }
}
