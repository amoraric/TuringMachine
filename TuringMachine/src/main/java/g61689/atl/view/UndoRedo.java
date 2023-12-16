package g61689.atl.view;

import g61689.atl.model.ModelFacade;
import javafx.scene.control.Button;
import util.Observable;
import util.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an undo/redo button in the Turing Machine game UI.
 * This class provides buttons for undoing and redoing actions within the game.
 */
public class UndoRedo extends Button implements Observable {
    private final List<Observer> observers;

    /**
     * Constructs an UndoRedo button with a given model facade and position indicator.
     *
     * @param modelFacade The model facade to handle undo/redo actions.
     * @param left        Indicates whether the button is for undo (true) or redo (false).
     */
    public UndoRedo(ModelFacade modelFacade, boolean left) {
        observers = new ArrayList<>();
        setup(modelFacade, left);
    }

    /**
     * Sets up the button's properties and action event.
     *
     * @param modelFacade The model facade used for performing actions.
     * @param left        Indicates whether the button is for undo (true) or redo (false).
     */
    private void setup(ModelFacade modelFacade, boolean left) {
        if (left) {
            this.setText("<-");
            this.setOnAction(e -> {
                modelFacade.undo();
                notifyObservers();
            });
        } else {
            this.setText("->");
            this.setOnAction(e -> {
                modelFacade.redo();
                notifyObservers();
            });
        }
    }

    @Override
    public boolean register(Observer obs) {
        if (!observers.contains(obs)) {
            return observers.add(obs);
        }
        return false;
    }

    @Override
    public boolean unregister(Observer obs) {
        return observers.remove(obs);
    }

    /**
     * Notifies all registered observers of any changes.
     */
    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}
