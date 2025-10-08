package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidChampion(String)}
 */
public class Champion {

    public static final String MESSAGE_CONSTRAINTS =
            "Champion must be the exact name of a League of Legends champion (e.g. 'Ahri', 'Lee Sin').";

    private static final Set<String> VALID_CHAMPIONS = loadChampionList();

    public final String championName;

    /**
     * Constructs a {@code Champion}.
     *
     * @param name A valid champion name.
     */
    public Champion(String name) {
        requireNonNull(name);
        checkArgument(isValidChampion(name), MESSAGE_CONSTRAINTS);
        championName = name;
    }

    /**
     * Returns true if a given string is a valid champion name.
     */
    public static boolean isValidChampion(String test) {
        return VALID_CHAMPIONS.contains(test);
    }

    private static Set<String> loadChampionList() {
        try {
            InputStream in = Champion.class.getResourceAsStream("/champions.txt");
            if (in == null) {
                throw new IllegalStateException("champions.txt not found in resources!");
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                return reader.lines().collect(Collectors.toSet());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load champion list", e);
        }
    }

    @Override
    public String toString() {
        return championName;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Champion
                && championName.equals(((Champion) other).championName);
    }

    @Override
    public int hashCode() {
        return championName.hashCode();
    }
}
