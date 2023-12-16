package g61689.atl.view;

import g61689.atl.model.ModelFacade;
import g61689.atl.model.Problem;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import util.Observable;
import util.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents the problems section in the Turing Machine game UI.
 * This class is responsible for displaying available problems and handling user selections.
 */
public class Problems extends VBox implements Observable {
    private final List<Observer> observers;
    private final ListView<String> problemListView;
    private final List<Problem> problems;
    private int chosenProblemIndex;

    /**
     * Constructs a Problems section with a given model facade.
     *
     * @param modelFacade The model facade to fetch available problems.
     */
    public Problems(ModelFacade modelFacade) {
        this.problems = modelFacade.getAvailableProblems();
        this.problemListView = new ListView<>();
        observers = new ArrayList<>();
        setup();
    }

    /**
     * Sets up the UI components for displaying problems.
     */
    private void setup() {
        this.setSpacing(10);
        this.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER);

        Label problemLabel = new Label("Choose a problem:");
        problemLabel.setFont(Font.font("Arial", 12));
        problemLabel.setUnderline(true);

        updateProblemListView(problemListView);

        Button chooseButton = createChooseButton();
        Button randomButton = createRandomButton();

        HBox buttonHBox = new HBox();
        buttonHBox.setAlignment(Pos.CENTER);
        buttonHBox.setSpacing(50);
        buttonHBox.getChildren().addAll(chooseButton, randomButton);

        this.getChildren().addAll(problemLabel, problemListView, buttonHBox);
    }

    /**
     * Updates the problem list view with available problems.
     *
     * @param listView The ListView to update with problem descriptions.
     */
    private void updateProblemListView(ListView<String> listView) {
        listView.setItems(FXCollections.observableArrayList(
                problems.stream()
                .map(Problem::getDescription)
                .toList()));
        listView.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 12;");
    }

    /**
     * Creates a 'Choose' button for selecting a problem.
     *
     * @return A Button for choosing a problem.
     */
    private Button createChooseButton() {
        Button button = new Button("Choose");
        button.setFont(Font.font("Arial", 12));
        button.setMaxWidth(Double.MAX_VALUE);
        button.setOnAction(e -> {
            handleSelectProblem(problemListView);
        });
        return button;
    }

    /**
     * Creates a 'Random' button for randomly selecting a problem.
     *
     * @return A Button for randomly choosing a problem.
     */
    private Button createRandomButton() {
        Button button = new Button("Random");
        button.setFont(Font.font("Arial", 12));
        button.setMaxWidth(Double.MAX_VALUE);
        button.setOnAction(e -> {
            handleRandomProblem();
        });
        return button;
    }

    /**
     * Handles the event of selecting a problem from the list.
     *
     * @param listView The ListView containing the problem options.
     */
    private void handleSelectProblem(ListView<String> listView) {
        chosenProblemIndex = listView.getSelectionModel().getSelectedIndex();
        notifyObservers();
    }

    /**
     * Handles the event of randomly selecting a problem.
     */
    private void handleRandomProblem() {
        Random random = new Random();
        chosenProblemIndex = random.nextInt(problems.size());
        notifyObservers();
    }

    /**
     * Retrieves the index of the chosen problem.
     *
     * @return The index of the selected problem.
     */
    public int getChosenProblemIndex() {
        return chosenProblemIndex;
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
