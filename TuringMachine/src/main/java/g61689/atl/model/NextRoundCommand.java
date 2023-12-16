package g61689.atl.model;

import util.Command;

import java.util.List;
import java.util.Map;

/**
 * Command to move the game to the next round.
 * Implements the Command interface for execution and cancellation actions.
 */
public class NextRoundCommand implements Command {
    private final Model model;
    private final Map<Integer, Boolean> validatorsTestedMap;
    private final List<Validator> availableValidators;

    /**
     * Constructor initializing the next round command.
     */
    public NextRoundCommand(Model model, Map<Integer, Boolean> validatorsTestedMap, List<Validator> availableValidators) {
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
