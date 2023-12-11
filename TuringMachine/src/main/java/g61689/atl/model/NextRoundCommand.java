package g61689.atl.model;

import util.Command;

import java.util.List;

public class NextRoundCommand implements Command {
    private final Model model;
    private final int validatorsTested;
    private final List<Validator> availableValidators;
    public NextRoundCommand(Model model, int validatorsTested, List<Validator> availableValidators) {
        this.model = model;
        this.validatorsTested = validatorsTested;
        this.availableValidators = availableValidators;
    }

    @Override
    public void execute() {
        model.moveToNextRound();
    }

    @Override
    public void cancel() {
        model.moveToLastRound(validatorsTested, availableValidators);
    }
}
