package util;

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
