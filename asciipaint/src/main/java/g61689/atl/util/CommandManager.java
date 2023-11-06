package g61689.atl.util;

import g61689.atl.ascii.model.Command;

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
        try {
            Command command = undoStack.pop();
            command.cancel();
            redoStack.push(command);
        } catch (EmptyStackException e) {
            System.out.println("The stack is empty, you can't execute that command!");
        }
    }

    /**
     * Redoes the command
     */
    public void redo() {
        try {
            Command command = redoStack.pop();
            command.execute();
            undoStack.push(command);
        } catch (EmptyStackException e) {
            System.out.println("The stack is empty, you can't execute that command!");
        }
    }
}
