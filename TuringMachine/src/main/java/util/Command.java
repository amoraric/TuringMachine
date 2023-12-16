package util;

/**
 * Represents a command in the command pattern.
 * This interface defines the structure for classes that encapsulate a command or an action.
 */
public interface Command {
    /**
     * Executes the command.
     * Implementing classes should define the specific execution logic here.
     */
    void execute();

    /**
     * Cancels or undoes the command.
     * Implementing classes should define the logic for undoing the action performed by execute().
     */
    void cancel();
}
