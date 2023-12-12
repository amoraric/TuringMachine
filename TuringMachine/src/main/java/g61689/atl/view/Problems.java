package g61689.atl.view;

import g61689.atl.model.ModelFacade;
import g61689.atl.model.Problem;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class Problems extends GridPane {
    private boolean random;
    private final ModelFacade modelFacade;

    public Problems(ModelFacade modelFacade) {
        Label problem = new Label("Choose a problem:");
        problem.setFont(Font.font("Arial", 12));
        problem.setUnderline(true);
        GridPane.setHalignment(problem, HPos.CENTER);
        this.add(problem, 0, 0);

        this.modelFacade = modelFacade;
        showProblems();
        getChoice();
    }

    private void showProblems() {
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        List<String> addText = new ArrayList<>();
        for (Problem prob : modelFacade.getAvailableProblems()) {
            addText.add(prob.getDescription());
        }
        textArea.setText(String.join("\n", addText));
        this.add(textArea, 0, 1);
    }

    private void getChoice() {
        Button chooseProblem = new Button("Choose the problem");
        chooseProblem.setFont(Font.font("Arial", 12));
        chooseProblem.setMaxWidth(Double.MAX_VALUE);
        VBox.setMargin(chooseProblem, new Insets(6));
        chooseProblem.setOnAction(e -> {
            random = false;
        });

        Button randomButton = new Button("Get a random problem");
        randomButton.setFont(Font.font("Arial", 12));
        randomButton.setMaxWidth(Double.MAX_VALUE);
        VBox.setMargin(randomButton, new Insets(6));
        randomButton.setOnAction(e -> {
            random = true;
        });

        this.add(chooseProblem, 0, 2);
        this.add(randomButton, 1, 2);
    }


}
