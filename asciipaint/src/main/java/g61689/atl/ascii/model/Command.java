package g61689.atl.ascii.model;

/**
 * Command interface
 */
public interface Command {
    /**
     * Executes a command
     */
    void execute();

    /**
     * Cancels a command
     */
    void cancel();
}
