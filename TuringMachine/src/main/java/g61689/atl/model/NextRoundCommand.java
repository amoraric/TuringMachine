package g61689.atl.model;

import util.Command;

public class NextRoundCommand implements Command {
    private final Model model;
    public NextRoundCommand(Model model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.moveToNextRound();
    }

    @Override
    public void cancel() {
        model.moveToLastRound();
    }
}
