package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final String id;
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Role role;
    private final Rank rank;
    private final Champion champion;
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Constructor for creating a new Person with default role, rank, and champion (backwards compatibility).
     *
     * @param name Name of the person.
     * @param phone Phone of the person.
     * @param email Email of the person.
     * @param address Address of the person.
     * @param tags Set of tags associated with the person.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        this(UUID.randomUUID().toString(), name, phone, email, address,
             new Role("mid"), new Rank("gold"), new Champion("Ahri"), tags);
    }

    /**
     * Constructor for creating a new Person with specified role, rank, and champion.
     * Generates a random UUID for the person.
     *
     * @param name Name of the person.
     * @param phone Phone of the person.
     * @param email Email of the person.
     * @param address Address of the person.
     * @param role Role of the person.
     * @param rank Rank of the person.
     * @param champion Champion of the person.
     * @param tags Set of tags associated with the person.
     */
    public Person(Name name, Phone phone, Email email, Address address, Role role, Rank rank,
                  Champion champion, Set<Tag> tags) {
        this(UUID.randomUUID().toString(), name, phone, email, address, role, rank, champion, tags);
    }

    /**
     * Constructor for creating a Person with an explicit ID.
     * This is used for deserialization from JSON to preserve the original ID.
     *
     * @param id Unique identifier for the person.
     * @param name Name of the person.
     * @param phone Phone of the person.
     * @param email Email of the person.
     * @param address Address of the person.
     * @param role Role of the person.
     * @param rank Rank of the person.
     * @param champion Champion of the person.
     * @param tags Set of tags associated with the person.
     */
    public Person(String id, Name name, Phone phone, Email email, Address address, Role role, Rank rank,
                  Champion champion, Set<Tag> tags) {
        requireAllNonNull(id, name, phone, email, address, role, rank, champion, tags);
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.role = role;
        this.rank = rank;
        this.champion = champion;
        this.tags.addAll(tags);
    }

    /**
     * Constructor for creating a new Person with name, role, rank, champion, and tags.
     * Other fields are set to default stub values.
     *
     * @param name Name of the person.
     * @param role Role of the person.
     * @param rank Rank of the person.
     * @param champion Champion of the person.
     * @param tags Set of tags associated with the person.
     */
    public Person(Name name, Rank rank, Role role, Champion champion, Set<Tag> tags) {
        this(name,
                new Phone("00000000"), // stub phone
                new Email("stub@example.com"), // stub email
                new Address("123 Stub Street"), // stub address
                role,
                rank,
                champion,
                tags);
    }

    public String getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Role getRole() {
        return role;
    }

    public Rank getRank() {
        return rank;
    }

    public Champion getChampion() {
        return champion;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tags", tags)
                .toString();
    }

}
