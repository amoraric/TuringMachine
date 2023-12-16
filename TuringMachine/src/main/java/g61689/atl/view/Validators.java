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

/**
 * Represents the validators section in the Turing Machine game UI.
 * This class is responsible for displaying validator images and handling user interactions with them.
 */
public class Validators extends FlowPane implements Observer {
    private List<List<Integer>> validatorNumbers;
    private final List<String> alphabet = new ArrayList<>(List.of("A", "B", "C", "D", "E", "F", "G"));
    private final Map<Integer, ImageView> resultImages = new HashMap<>();
    private final ModelFacade modelFacade;

    /**
     * Constructs a Validators pane with a given model facade.
     *
     * @param modelFacade The model facade to be used for game logic.
     */
    public Validators(ModelFacade modelFacade) {
        this.modelFacade = modelFacade;
        modelFacade.register(this);
        setup();
        setupDynamicSpacing();
    }

    /**
     * Sets up initial properties and layout of the validators pane.
     */
    private void setup() {
        this.setPadding(new Insets(1));
        this.setAlignment(Pos.CENTER);

        validatorNumbers = modelFacade.getValidatorNumbers();
    }

    /**
     * Adds validator images to the pane dynamically based on available width.
     */
    private void addImages() {
        double availableWidth = this.getWidth() - this.getPadding().getLeft() - this.getPadding().getRight();
        int numValidators = validatorNumbers.get(0).size();
        double imageWidth = availableWidth / numValidators - this.getHgap();

        int countValidators = 0;
        for (Integer validatorNo : validatorNumbers.get(0)) {
            String validatorName = "/card" + validatorNo + ".png";
            String imageRobot = "/robot" + alphabet.get(countValidators) + ".png";
            Image validatorImage = new Image(validatorName);

            if (!validatorImage.isError()) {
                ImageView validatorImageView = new ImageView(validatorImage);
                validatorImageView.setPreserveRatio(true);
                validatorImageView.setFitWidth(imageWidth);
                int finalCc = countValidators+1;
                validatorImageView.setOnMouseClicked(event -> handleImageClick(finalCc));

                VBox imageBox = new VBox(5);
                imageBox.setAlignment(Pos.CENTER);

                ImageView robotImageView = new ImageView(imageRobot);
                robotImageView.setPreserveRatio(true);
                robotImageView.setFitWidth(imageWidth/5);

                Label letter = new Label(alphabet.get(countValidators));

                ImageView resultImageView = new ImageView();
                resultImageView.setPreserveRatio(true);
                resultImageView.setFitWidth(30);
                resultImages.put(countValidators+1, resultImageView);

                TextField tf = new TextField();
                imageBox.getChildren().addAll(robotImageView, letter, validatorImageView, resultImageView, tf);
                this.getChildren().add(imageBox);
            } else {
                System.err.println("Error loading image: " + validatorName);
            }
            countValidators ++;
        }
    }

    /**
     * Clears the result images from the validators.
     */
    public void clearResults() {
        for (ImageView imageView : resultImages.values()) {
            imageView.setImage(null);
        }
    }

    /**
     * Updates the result images based on the current game state.
     */
    private void updateResultsBasedOnState() {
        Map<Integer, Boolean> validatorsTested = modelFacade.getValidatorsTestedMap();
        clearResults();
        for (Integer ss : validatorsTested.keySet()) {
            String res = getResultImagePath(validatorsTested.get(ss));
            Image img = new Image(res);
            resultImages.get(ss+1).setImage(img);
        }
    }

    /**
     * Generates a path to the result image based on the validation result.
     *
     * @param result The result of the validation.
     * @return The path to the corresponding result image.
     */
    private String getResultImagePath(Boolean result) {
        if (result) {
            return "/correct.png";
        } else {
            return "/incorrect.png";
        }
    }

    /**
     * Sets up dynamic spacing for the validators pane based on its width.
     */
    private void setupDynamicSpacing() {
        this.widthProperty().addListener((obs, oldVal, newVal) -> {
            this.setHgap(Math.max(10, newVal.doubleValue() / 100));
            this.getChildren().clear();
            addImages();
        });
    }

    /**
     * Handles click events on validator images.
     *
     * @param chosenValidator The index of the clicked validator.
     */
    private void handleImageClick(int chosenValidator) {
        if (modelFacade.canApplyValidator()) {
            if (modelFacade.isUserCodeSet()) {
                modelFacade.chooseValidator(chosenValidator);
            } else {
                UIView.popupMessage("Error", "You have to set a code first.");
            }
        } else {
            UIView.popupMessage("Error", "Only 3 validators can be tested per round.");
        }
    }

    @Override
    public void update(Observable observable) {
        if (observable instanceof ModelFacade) {
            updateResultsBasedOnState();
        }
    }
}
