package g61689.atl.model;

import java.util.Collections;
import java.util.List;

public class Problem {
    private final int number;
    private final int difficulty;
    private final int luck;
    private final int code;

    private final List<List<Integer>> validatorNos;

    public Problem(int number, int difficulty, int luck, int code, List<Integer> validatorNos) {
        this.number = number;
        this.difficulty = difficulty;
        this.luck = luck;
        this.code = code;
        this.validatorNos = Collections.singletonList(validatorNos);
    }

    public int getCode() {
        return code;
    }

    public List<List<Integer>> getValidatorNos() {
        return validatorNos;
    }

    public String getDescription() {
        return String.format("Problem %d - Difficulty: %d, Luck: %d", number, difficulty, luck);
    }
}
