package g61689.atl.model;

import util.Command;
import util.CommandManager;
import util.Observable;
import util.Observer;

import java.util.ArrayList;
import java.util.List;

public class ModelFacade implements Observable {
    private final Model model;
    private final CommandManager commandManager;
    private final List<Observer> observers = new ArrayList<>();

    public ModelFacade() {
        this.model = new Model();
        commandManager = new CommandManager();
    }

    public void startGame(int selectedProblemIndex) {
        model.startGame(selectedProblemIndex);
        notifyObservers();
    }

    public List<List<Integer>> getValidatorNumbers() {
        return model.getValidatorNumbers();
    }

    public void enterCode(int userChoice) {// notify ??
        model.enterCode(userChoice);
    }

    public boolean canApplyValidator() {
        return model.canApplyValidator();
    }

    public void chooseValidator(int chosenValidatorIndex) {
        Command command = new TestValidatorCommand(chosenValidatorIndex, model);
        commandManager.newCommand(command);
        notifyObservers();
    }

    public boolean getValidatorState(int chosenValidator) {
        return model.getValidatorState(chosenValidator);
    }

    public void moveToNextRound() {
        Command command = new NextRoundCommand(model, model.getValidatorsTestedMap(), getAvailableValidators());
        commandManager.newCommand(command);
        notifyObservers();
    }

    public List<Problem> getAvailableProblems() {
        return model.getAvailableProblems();
    }

    public boolean isGameFinished() {
        return model.isGameFinished();
    }

    public int getScore() {
        return model.getScore();
    }

    public int getNumberValidatorsTested() {
        return model.getNumberValidatorsTested();
    }

    public int getRoundsPlayed() {
        return model.getRoundsPlayed();
    }

    public List<Validator> getAvailableValidators() {
        return model.getAvailableValidators();
    }

    public void setUserCode(int userCode) {
        Command command = new SetCodeCommand(userCode, model);
        commandManager.newCommand(command);
        // notify ??
    }

    public boolean isUserCodeSet() {
        return model.isUserCodeSet();
    }

    public void finishGame() { // notify ??
        model.finishGame();
    }

    /**
     * Undoes the command
     */
    public void undo() {
        commandManager.undo();
        notifyObservers();
    }

    /**
     * Redoes the command
     */
    public void redo() {
        commandManager.redo();
        notifyObservers();
    }

    @Override
    public boolean register(Observer obs) {
        if (!observers.contains(obs)) {
            observers.add(obs);
            return true;
        }
        return false;
    }

    @Override
    public boolean unregister(Observer obs) {
        if (observers.contains(obs)) {
            observers.remove(obs);
            return true;
        }
        return false;
    }

    private void notifyObservers() {
        for (Observer obs : observers) {
            obs.update(this);
        }
    }
}

