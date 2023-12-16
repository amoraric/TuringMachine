package g61689.atl.model;

import util.Command;

/**
 * Command to set the user code in the game model.
 * Implements the Command interface for execution and cancellation actions.
 */
public class SetCodeCommand implements Command {
    private final int userCode;
    private final Model model;

    /**
     * Constructor initializing the set code command.
     */
    public SetCodeCommand(int userCode, Model model) {
        this.userCode = userCode;
        this.model = model;
    }

    @Override
    public void execute() {
        model.setUserCode(userCode);
    }

    @Override
    public void cancel() {
        model.removeUserCode();
    }
}
