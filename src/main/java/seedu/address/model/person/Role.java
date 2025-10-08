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

    /**
     * Enum representing valid League of Legends roles.
     */
    public enum RoleType {
        TOP,
        JUNGLE,
        MID,
        ADC,
        SUPPORT;

        /**
         * Returns the RoleType matching the given string (case-insensitive),
         * or null if no match is found.
         */
        public static RoleType fromString(String role) {
            for (RoleType r : RoleType.values()) {
                if (r.name().equalsIgnoreCase(role)) {
                    return r;
                }
            }
            throw new IllegalArgumentException(role);
        }
    }

    public final RoleType roleType;

    /**
     * Constructs a {@code Role}.
     *
     * @param role A valid role string.
     */
    public Role(String role) {
        requireNonNull(role);
        checkArgument(isValidRole(role), MESSAGE_CONSTRAINTS);
        RoleType r = RoleType.fromString(role);
        this.roleType = r;
    }

    /**
     * Returns true if a given string is a valid role.
     */
    public static boolean isValidRole(String test) {
        for (RoleType r : RoleType.values()) {
            if (r.name().equalsIgnoreCase(test)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String name = roleType.name();
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Role
                && roleType == ((Role) other).roleType;
    }

    @Override
    public int hashCode() {
        return roleType.hashCode();
    }
}
