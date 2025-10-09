package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Champion;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Rank;
import seedu.address.model.person.Role;

public class JsonAdaptedPersonTest {
    private static final String DUMMY_ID = "";

    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_ROLE = BENSON.getRole().toString();
    private static final String VALID_RANK = BENSON.getRank().toString();
    private static final String VALID_CHAMPION = BENSON.getChampion().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(DUMMY_ID, INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_ROLE, VALID_RANK, VALID_CHAMPION, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(DUMMY_ID, null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_ROLE, VALID_RANK, VALID_CHAMPION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(DUMMY_ID, VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_ROLE, VALID_RANK, VALID_CHAMPION, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(DUMMY_ID, VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                VALID_ROLE, VALID_RANK, VALID_CHAMPION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(DUMMY_ID, VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS,
                        VALID_ROLE, VALID_RANK, VALID_CHAMPION, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(DUMMY_ID, VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
                VALID_ROLE, VALID_RANK, VALID_CHAMPION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(DUMMY_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS,
                        VALID_ROLE, VALID_RANK, VALID_CHAMPION, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(DUMMY_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_ROLE, VALID_RANK, VALID_CHAMPION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullRole_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(DUMMY_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                null, VALID_RANK, VALID_CHAMPION, VALID_TAGS);
        String expectedMessage = String.format(JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT,
                Role.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidRole_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(DUMMY_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                "invalid role", VALID_RANK, VALID_CHAMPION, VALID_TAGS);
        assertThrows(IllegalValueException.class, Role.MESSAGE_CONSTRAINTS, person::toModelType);
    }

    @Test
    public void toModelType_nullRank_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(DUMMY_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_ROLE, null, VALID_CHAMPION, VALID_TAGS);
        String expectedMessage = String.format(JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT,
                Rank.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidRank_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(DUMMY_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_ROLE, "invalid rank", VALID_CHAMPION, VALID_TAGS);
        assertThrows(IllegalValueException.class, Rank.MESSAGE_CONSTRAINTS, person::toModelType);
    }

    @Test
    public void toModelType_nullChampion_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(DUMMY_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_ROLE, VALID_RANK, null, VALID_TAGS);
        String expectedMessage = String.format(JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT,
                Champion.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidChampion_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(DUMMY_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_ROLE, VALID_RANK, "invalid champion", VALID_TAGS);
        assertThrows(IllegalValueException.class, Champion.MESSAGE_CONSTRAINTS, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(DUMMY_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_ROLE, VALID_RANK, VALID_CHAMPION, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
