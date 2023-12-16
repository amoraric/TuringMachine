package util;

import java.util.Stack;

/**
 * Manages command execution, undo, and redo functionalities.
 * This class uses two stacks to handle the history of commands for undo and redo actions.
 */
public class CommandManager {
    private final Stack<Command> undoStack = new Stack<>();
    private final Stack<Command> redoStack = new Stack<>();

    /**
     * Executes a new command and adds it to the undo history.
     * Clears the redo history upon execution of a new command.
     *
     * @param command The command to execute and add to the history.
     */
    public void newCommand(Command command) {
        command.execute();
        undoStack.add(command);
        redoStack.clear();
    }

    /**
     * Undoes the most recent command if available.
     * Moves the undone command to the redo history.
     */
    public void undo() {
        if(!undoStack.empty()) {
            Command command = undoStack.pop();
            command.cancel();
            redoStack.push(command);
        }

    }

    /**
     * Redoes the most recently undone command if available.
     * Moves the redone command back to the undo history.
     */
    public void redo() {
        if(!redoStack.empty()) {
            Command command = redoStack.pop();
            command.execute();
            undoStack.push(command);
        }
    }

    /**
     * Clears the history of both executed and undone commands.
     */
    public void clearCommands() {
        undoStack.clear();
        redoStack.clear();
    }
}
