package seedu.address.model.person.exceptions;

/**
 * Signals that the operation encountered an invalid Champion.
 */
public class InvalidChampionException extends RuntimeException {

    public InvalidChampionException() {
        super("Invalid champion provided. "
                + "Champion must be one of the official League of Legends champions.");
    }

    public InvalidChampionException(String champion) {
        super("Invalid champion provided: " + champion
                + ". Champion must be one of the official League of Legends champions.");
    }
}