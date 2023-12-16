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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import util.Observable;
import util.Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validators extends FlowPane implements Observable {
    private final List<Observer> observers;
    private final List<Validator> validators;
    private int chosenValidator;
    private List<List<Integer>> validatorNumbers;
    private final List<String> alphabet = new ArrayList<>(List.of("A", "B", "C", "D", "E", "F", "G"));
    private final Map<Integer, Label> resultLabels = new HashMap<>();

    public Validators(ModelFacade modelFacade) {
        this.validators = modelFacade.getAvailableValidators();
        observers = new ArrayList<>();
        setup(modelFacade);
        setupDynamicSpacing();
    }

    private void setup(ModelFacade modelFacade) {
        this.setPadding(new Insets(1));
        this.setAlignment(Pos.CENTER);

        validatorNumbers = modelFacade.getValidatorNumbers();
    }

    private void addImages() {
        double availableWidth = this.getWidth() - this.getPadding().getLeft() - this.getPadding().getRight();
        int numValidators = validatorNumbers.get(0).size();
        double imageWidth = availableWidth / numValidators - this.getHgap();

        int cc = 0;
        for (Integer validatorNo : validatorNumbers.get(0)) {
            String validatorName = "/card" + validatorNo + ".png";
            String imageRobot = "/robot" + alphabet.get(cc) + ".png";
            Image validatorImage = new Image(validatorName);

            if (!validatorImage.isError()) {
                ImageView validatorImageView = new ImageView(validatorImage);
                validatorImageView.setPreserveRatio(true);
                validatorImageView.setFitWidth(imageWidth);
                int finalCc = cc+1;
                validatorImageView.setOnMouseClicked(event -> handleImageClick(finalCc));

                VBox imageBox = new VBox(5);
                imageBox.setAlignment(Pos.CENTER);

                ImageView robotImageView = new ImageView(imageRobot);
                robotImageView.setPreserveRatio(true);
                robotImageView.setFitWidth(imageWidth/5);

                Label letter = new Label(alphabet.get(cc));

                Label resultLabel = new Label("...");
                resultLabels.put(cc+1, resultLabel);

                TextField tf = new TextField();
                imageBox.getChildren().addAll(robotImageView, letter, validatorImageView, resultLabel, tf);
                this.getChildren().add(imageBox);
            } else {
                System.err.println("Error loading image: " + validatorName);
            }
            cc ++;
        }
    }

    public void setResult(int validatorNo, String text) {
        Label label = resultLabels.get(validatorNo);
        if (label != null) {
            label.setText(text);
        }
    }

    private void setupDynamicSpacing() {
        this.widthProperty().addListener((obs, oldVal, newVal) -> {
            double hgap = Math.max(10, newVal.doubleValue() / 100);
            this.setHgap(hgap);
            this.getChildren().clear();
            addImages();
        });
    }

    private void handleImageClick(int chosenValidator) {
        this.chosenValidator = chosenValidator;
        notifyObservers();
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
