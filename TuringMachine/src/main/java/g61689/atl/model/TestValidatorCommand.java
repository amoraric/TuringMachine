package g61689.atl.model;

import util.Command;

public class TestValidatorCommand implements Command {
    private final int chosenValidatorIndex;
    private final Model model;
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
