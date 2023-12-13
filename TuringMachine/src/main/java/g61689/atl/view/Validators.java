package g61689.atl.view;

import g61689.atl.model.ModelFacade;
import g61689.atl.model.Problem;
import g61689.atl.model.Validator;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import util.Observable;
import util.Observer;

import java.util.ArrayList;
import java.util.List;

public class Validators extends FlowPane implements Observable {
    private final List<Observer> observers;
    private final List<Validator> validators;
    private int chosenValidator;
    private List<List<Integer>> validatorNumbers;

    public Validators(ModelFacade modelFacade) {
        this.validators = modelFacade.getAvailableValidators();
        observers = new ArrayList<>();
        setup(modelFacade);
        setupDynamicSpacing();
    }

    private void setup(ModelFacade modelFacade) {
        this.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER);

        validatorNumbers = modelFacade.getValidatorNumbers();

        addImages();
    }

    private void addImages() {
        for (Integer validatorNo : validatorNumbers.get(0)) {
            String imageName = "/card" + validatorNo + ".png";
            Image image = new Image(imageName);

            if (!image.isError()) {
                ImageView imageView = new ImageView(image);
                imageView.setPreserveRatio(true);
                imageView.setOnMouseClicked(event -> handleImageClick(validatorNo));
                this.getChildren().add(imageView);
            } else {
                System.err.println("Error loading image: " + imageName);
            }
        }
    }

    private void handleImageClick(int validatorNo) {
        this.chosenValidator = validatorNo;
        notifyObservers();
    }

    private void setupDynamicSpacing() {
        this.widthProperty().addListener((obs, oldVal, newVal) -> {
            double hgap = Math.max(10, newVal.doubleValue() / 25);
            this.setHgap(hgap);
        });
    }

    public List<Validator> getValidators() {
        return validators;
    }

    public int getChosenValidator() {
        return chosenValidator;
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
