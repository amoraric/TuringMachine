package g61689.atl.model;

import java.util.Collections;
import java.util.List;


/**
 * Represents a problem in the game, including its unique properties and associated validators.
 */
public class Problem {
    private final int number;
    private final int difficulty;
    private final int luck;
    private final int code;
    private final List<List<Integer>> validatorNos;

    /**
     * Constructs a Problem with specified attributes and validator numbers.
     *
     * @param number        The problem number.
     * @param difficulty    The difficulty level of the problem.
     * @param luck          The luck factor of the problem.
     * @param code          The secret code associated with the problem.
     * @param validatorNos  A list of validator numbers for this problem.
     */
    public Problem(int number, int difficulty, int luck, int code, List<Integer> validatorNos) {
        this.number = number;
        this.difficulty = difficulty;
        this.luck = luck;
        this.code = code;
        this.validatorNos = Collections.singletonList(validatorNos);
    }

    /**
     * Gets the secret code of the problem.
     *
     * @return The secret code.
     */
    public int getCode() {
        return code;
    }

    /**
     * Gets the list of validator numbers associated with the problem.
     *
     * @return A list of lists of validator numbers.
     */
    public List<List<Integer>> getValidatorNos() {
        return validatorNos;
    }

    /**
     * Provides a descriptive string of the problem's attributes.
     *
     * @return A description of the problem.
     */
    public String getDescription() {
        return String.format("Problem %d - Difficulty: %d, Luck: %d", number, difficulty, luck);
    }
}
