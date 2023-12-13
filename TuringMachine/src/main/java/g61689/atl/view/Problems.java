package g61689.atl.view;

import g61689.atl.model.ModelFacade;
import g61689.atl.model.Problem;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import util.Observable;
import util.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Problems extends VBox implements Observable {
    private final List<Observer> observers;
    private final ListView<String> problemListView;
    private final List<Problem> problems;
    private String chosenProblem;
    private int chosenProblemIndex;

    public Problems(ModelFacade modelFacade) {
        this.problems = modelFacade.getAvailableProblems();
        this.problemListView = new ListView<>();
        observers = new ArrayList<>();
        setup();
    }

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

        this.getChildren().addAll(problemLabel, problemListView, chooseButton, randomButton);
    }

    private void updateProblemListView(ListView<String> listView) {
        listView.setItems(FXCollections.observableArrayList(
                problems.stream()
                .map(Problem::getDescription)
                .toList()));
        listView.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 12;");
    }

    private Button createChooseButton() {
        Button button = new Button("Choose");
        button.setFont(Font.font("Arial", 12));
        button.setMaxWidth(Double.MAX_VALUE);
        button.setOnAction(e -> {
            handleSelectProblem(problemListView);
        });
        return button;
    }

    private Button createRandomButton() {
        Button button = new Button("Random");
        button.setFont(Font.font("Arial", 12));
        button.setMaxWidth(Double.MAX_VALUE);
        button.setOnAction(e -> {
            handleRandomProblem();
        });
        return button;
    }

    private void handleSelectProblem(ListView<String> listView) {
        chosenProblem = listView.getSelectionModel().getSelectedItem();
        chosenProblemIndex = listView.getSelectionModel().getSelectedIndex();
        notifyObservers();
    }

    private void handleRandomProblem() {
        Random random = new Random();
        int randomIndex = random.nextInt(problems.size());
        chosenProblem = problems.get(randomIndex).getDescription();
        chosenProblemIndex = randomIndex;
        notifyObservers();
    }

    public String getChosenProblem() {
        return chosenProblem;
    }

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

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}
