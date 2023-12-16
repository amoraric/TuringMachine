package g61689.atl.model;

/**
 * Abstract class representing a generic validator.
 * Provides the basic structure and utility methods for specific validators.
 */
public abstract class Validator {
    private final String description;

    /**
     * Constructs a Validator with a given description.
     *
     * @param description A descriptive string for the validator.
     */
    public Validator(String description) {
        this.description = description;
    }

    /**
     * Gets the description of the validator.
     *
     * @return The description of the validator.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Abstract method to validate a code.
     * Implementations should define the specific validation logic.
     *
     * @param code The code to be validated.
     * @return true if the code passes the validation, false otherwise.
     */
    public abstract boolean validate(int code);

    /**
     * Abstract method to categorize a code.
     * Implementations should define the specific categorization logic.
     *
     * @param secretCode The code to be categorized.
     * @return A string representing the category of the code.
     */
    public abstract String categorizeCode(int secretCode);

    /**
     * Extracts a specific digit from a given code.
     *
     * @param code        The code from which to extract the digit.
     * @param digitNumber The position of the digit to extract (0 for first digit, etc.).
     * @return The extracted digit.
     */
    int getDigit(int code, int digitNumber) {
        int[] codeArray = new int[3];
        codeArray[0] = code / 100;
        codeArray[1] = (code / 10) % 10;
        codeArray[2] = code % 10;
        return codeArray[digitNumber];
    }

    /**
     * Converts a code into an array of its digits.
     *
     * @param code The code to be converted.
     * @return An array containing the individual digits of the code.
     */
    int[] convertToArray(int code) {
        int[] codeArray = new int[3];
        codeArray[0] = code / 100;
        codeArray[1] = (code / 10) % 10;
        codeArray[2] = code % 10;
        return codeArray;
    }
}
