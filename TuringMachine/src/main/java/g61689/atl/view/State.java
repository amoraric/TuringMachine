package g61689.atl.view;

import g61689.atl.model.ModelFacade;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import util.Observable;
import util.Observer;

public class State extends GridPane {
    private final int score;
    private final int validatorsUsed;

    public State(ModelFacade modelFacade) {
        this.score = modelFacade.getScore();
        this.validatorsUsed = modelFacade.getNumberValidatorsTested();
        setup();
    }

    private void setup() {
        this.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER);

        Label scoreLabel = new Label("Score: " + score);
        scoreLabel.setFont(Font.font("Arial", 12));
        scoreLabel.setUnderline(true);

        this.add(scoreLabel, 0, 0);

        Label validatorsUsedLabel = new Label("Validators used: " + validatorsUsed);
        validatorsUsedLabel.setFont(Font.font("Arial", 12));
        validatorsUsedLabel.setUnderline(true);

        this.add(validatorsUsedLabel, 0, 1);
    }
}
