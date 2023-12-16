package g61689.atl.view;

import g61689.atl.model.ModelFacade;
import javafx.scene.control.Button;
import util.Observable;
import util.Observer;

import java.util.ArrayList;
import java.util.List;

public class UndoRedo extends Button implements Observable {
    private final List<Observer> observers;

    public UndoRedo(ModelFacade modelFacade, boolean left) {
        observers = new ArrayList<>();
        setup(modelFacade, left);
    }

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

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}
