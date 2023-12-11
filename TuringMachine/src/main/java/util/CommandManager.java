package util;

import java.util.EmptyStackException;
import java.util.Stack;

public class CommandManager {
    private final Stack<Command> undoStack = new Stack<>();
    private final Stack<Command> redoStack = new Stack<>();

    /**
     * Executes a new command
     *
     * @param command a new command
     */
    public void newCommand(Command command) {
        command.execute();
        undoStack.add(command);
        redoStack.clear();
    }

    /**
     * Undoes the command
     */
    public void undo() {
        if(!undoStack.empty()) {
            Command command = undoStack.pop();
            command.cancel();
            redoStack.push(command);
        }

    }

    /**
     * Redoes the command
     */
    public void redo() {
        if(!redoStack.empty()) {
            Command command = redoStack.pop();
            command.execute();
            undoStack.push(command);
        }
    }
}
