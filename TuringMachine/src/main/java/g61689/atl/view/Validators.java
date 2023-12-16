package g61689.atl.view;

import g61689.atl.model.ModelFacade;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import util.Observable;
import util.Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validators extends FlowPane implements Observable, Observer {
    private final List<Observer> observers;
    private int chosenValidator;
    private List<List<Integer>> validatorNumbers;
    private final List<String> alphabet = new ArrayList<>(List.of("A", "B", "C", "D", "E", "F", "G"));
    private final Map<Integer, ImageView> resultImages = new HashMap<>();
    private final ModelFacade modelFacade;

    public Validators(ModelFacade modelFacade) {
        observers = new ArrayList<>();
        this.modelFacade = modelFacade;
        modelFacade.register(this);
        setup();
        setupDynamicSpacing();
    }

    private void setup() {
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

                ImageView resultImageView = new ImageView();
                resultImageView.setPreserveRatio(true);
                resultImageView.setFitWidth(30);
                resultImages.put(cc+1, resultImageView);

                TextField tf = new TextField();
                imageBox.getChildren().addAll(robotImageView, letter, validatorImageView, resultImageView, tf);
                this.getChildren().add(imageBox);
            } else {
                System.err.println("Error loading image: " + validatorName);
            }
            cc ++;
        }
    }

    public void setResult(int validatorNo, String text) {
        ImageView imageView = resultImages.get(validatorNo);
        if (imageView != null) {
            String imagePath;
            if ("You passed!".equals(text)) {
                imagePath = "/correct.png";
            } else {
                imagePath = "/incorrect.png";
            }
            Image image = new Image(imagePath);
            imageView.setImage(image);
        }
    }

    public void clearResults() {
        for (ImageView imageView : resultImages.values()) {
            imageView.setImage(null);
        }
    }

    private void updateResultsBasedOnState() {
        Map<Integer, Boolean> validatorsTested = modelFacade.getValidatorsTestedMap();
        clearResults();
        for (Integer ss : validatorsTested.keySet()) {
            String res = getResultImagePath(validatorsTested.get(ss));
            Image img = new Image(res);
            resultImages.get(ss+1).setImage(img);
        }
    }

    private String getResultImagePath(Boolean result) {
        if (result) {
            return "/correct.png";
        } else {
            return "/incorrect.png";
        }
    }

    private void setupDynamicSpacing() {
        this.widthProperty().addListener((obs, oldVal, newVal) -> {
            this.setHgap(Math.max(10, newVal.doubleValue() / 100));
            this.getChildren().clear();
            addImages();
        });
    }

    private void handleImageClick(int chosenValidator) {
        this.chosenValidator = chosenValidator;
        notifyObservers();
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

    @Override
    public void update(Observable observable) {
        if (observable instanceof ModelFacade) {
            updateResultsBasedOnState();
        }
    }
}
