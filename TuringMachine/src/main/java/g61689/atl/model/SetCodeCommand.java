package g61689.atl.model;

import util.Command;

public class SetCodeCommand implements Command {
    private final int userCode;
    private final Model model;
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
