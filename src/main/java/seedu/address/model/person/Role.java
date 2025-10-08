package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a Player's role in League of Legends.
 * Guarantees: immutable; is valid as declared in {@link #isValidRole(String)}
 */
public class Role {

    public static final String MESSAGE_CONSTRAINTS =
            "Role must be one of: Top, Jungle, Mid, ADC, Support";

    private static final Set<String> VALID_ROLES = new HashSet<>();

    static {
        VALID_ROLES.add("Top");
        VALID_ROLES.add("Jungle");
        VALID_ROLES.add("Mid");
        VALID_ROLES.add("ADC");
        VALID_ROLES.add("Support");
    }

    public final String roleName;

    /**
     * Constructs a {@code Role}.
     *
     * @param role A valid role string.
     */
    public Role(String role) {
        requireNonNull(role);
        checkArgument(isValidRole(role), MESSAGE_CONSTRAINTS);
        this.roleName = role;
    }

    /**
     * Returns true if a given string is a valid role.
     */
    public static boolean isValidRole(String test) {
        return VALID_ROLES.contains(test);
    }

    @Override
    public String toString() {
        return roleName;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Role
                && roleName.equals(((Role) other).roleName);
    }

    @Override
    public int hashCode() {
        return roleName.hashCode();
    }
}
