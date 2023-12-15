package g61689.atl.model;

import util.Command;

import java.util.List;
import java.util.Map;

public class NextRoundCommand implements Command {
    private final Model model;
    private final Map<Integer, String> validatorsTestedMap;
    private final List<Validator> availableValidators;
    public NextRoundCommand(Model model, Map<Integer, String> validatorsTestedMap, List<Validator> availableValidators) {
        this.model = model;
        this.validatorsTestedMap = validatorsTestedMap;
        this.availableValidators = availableValidators;
    }

    @Override
    public void execute() {
        model.moveToNextRound();
    }

    @Override
    public void cancel() {
        model.moveToLastRound(validatorsTestedMap, availableValidators);
    }
}
