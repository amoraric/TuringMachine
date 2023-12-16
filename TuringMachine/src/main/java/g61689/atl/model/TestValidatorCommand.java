package g61689.atl.model;

import util.Command;

/**
 * Command to test a validator in the game model.
 * Implements the Command interface for execution and cancellation actions.
 */
public class TestValidatorCommand implements Command {
    private final int chosenValidatorIndex;
    private final Model model;

    /**
     * Constructor initializing the test validator command.
     */
    public TestValidatorCommand(int chosenValidatorIndex, Model model) {
        this.chosenValidatorIndex = chosenValidatorIndex;
        this.model = model;
    }

    @Override
    public void execute() {
        model.chooseValidator(chosenValidatorIndex);
    }

    @Override
    public void cancel() {
        model.undoValidator(chosenValidatorIndex);
    }
}
