package g61689.atl.view;

import g61689.atl.model.ModelFacade;
import g61689.atl.model.Problem;
import g61689.atl.model.Validator;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import util.Observable;
import util.Observer;

import java.util.ArrayList;
import java.util.List;

public class Validators extends VBox implements Observable {
    private final List<Observer> observers;
    private final List<Validator> validators;
    private final ListView<String> validatorsView;
    private int chosenValidator;
    private List<List<Integer>> validatorNumbers;
    private List<ImageView> images;
    private Pane imageContainer;

    public Validators(ModelFacade modelFacade) {
        this.validators = modelFacade.getAvailableValidators();
        validatorsView = new ListView<>();
        observers = new ArrayList<>();
        imageContainer = new VBox(10);
        setup(modelFacade);
    }

    private void setup(ModelFacade modelFacade) {
        this.setSpacing(10);
        this.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER);

        imageContainer.setPadding(new Insets(10));

        Label validatorLabel = new Label("Choose a validator:");
        validatorLabel.setFont(Font.font("Arial", 12));
        validatorLabel.setUnderline(true);

        validatorNumbers = modelFacade.getValidatorNumbers();

//        updateValidatorListView(validatorsView);

//        Button chooseButton = createChooseButton();

//        this.getChildren().addAll(validatorLabel, validatorsView, chooseButton);

        this.getChildren().addAll(validatorLabel);

        addImages();

        this.getChildren().add(imageContainer);
    }

    private void addImages() {
        for (List<Integer> vnum : validatorNumbers) {
            String imageName = "src/main/resources/card" + vnum.get(0) + ".png";
            System.out.println(imageName); // TODO : check why problems don't show up after changing the model url resources
            Image image = new Image(imageName);

            if (!image.isError()) {
                ImageView imageView = new ImageView(image);

                this.getChildren().add(imageView);
            } else {
                System.err.println("Error loading image: " + imageName);
            }
        }
    }

    public List<Validator> getValidators() {
        return validators;
    }

//    private void updateValidatorListView(ListView<String> listView) {
//        listView.setItems(FXCollections.observableArrayList(
//                validators.stream()
//                        .map(Validator::getDescription)
//                        .toList()));
//        listView.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 12;");
//    }

//    private Button createChooseButton() {
//        Button button = new Button("Choose");
//        button.setFont(Font.font("Arial", 12));
//        button.setMaxWidth(Double.MAX_VALUE);
//        button.setOnAction(e -> {
//            handleSelectProblem(validatorsView);
//        });
//        return button;
//    }

//    private void handleSelectProblem(ListView<String> listView) {
//        chosenValidator = listView.getSelectionModel().getSelectedIndex();
//        notifyObservers();
//    }

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
