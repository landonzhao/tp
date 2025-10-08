package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");

    /* to be removed later*/
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");

    public static final Prefix PREFIX_RANK = new Prefix("rk/");
    public static final Prefix PREFIX_ROLE = new Prefix("rl/");
    public static final Prefix PREFIX_CHAMPION = new Prefix("c/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

}
