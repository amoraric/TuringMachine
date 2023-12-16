package g61689.atl.view;

import g61689.atl.model.ModelFacade;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import util.Observable;
import util.Observer;

/**
 * Represents the state display in the Turing Machine game UI.
 * This class is responsible for showing the current game state, such as score, validators used, and rounds played.
 */
public class State extends GridPane implements Observer {
    private final int score;
    private final int validatorsUsed;
    private final int roundsPlayed;
    private final ModelFacade modelFacade;
    private Label scoreLabel;
    private Label validatorsUsedLabel;
    private Label roundsPlayedLabel;

    /**
     * Constructs a State panel with a given model facade.
     *
     * @param modelFacade The model facade to fetch game state data.
     */
    public State(ModelFacade modelFacade) {
        this.modelFacade = modelFacade;
        modelFacade.register(this);
        this.score = modelFacade.getScore();
        this.validatorsUsed = modelFacade.getNumberValidatorsTested();
        this.roundsPlayed = modelFacade.getRoundsPlayed();
        setup();
    }

    /**
     * Sets up the initial layout and components of the state panel.
     */
    private void setup() {
        this.getStyleClass().add("state-panel");
        this.setPadding(new Insets(10));
        this.setVgap(10);
        this.setHgap(20);
        this.setAlignment(Pos.CENTER);

        scoreLabel = new Label("Score: " + score);
        scoreLabel.setFont(Font.font("Arial", 18));

        validatorsUsedLabel = new Label("Validators used: " + validatorsUsed);
        validatorsUsedLabel.setFont(Font.font("Arial", 18));

        roundsPlayedLabel = new Label("Rounds played: " + roundsPlayed);
        roundsPlayedLabel.setFont(Font.font("Arial", 18));

        this.add(scoreLabel, 0, 0);
        this.add(validatorsUsedLabel, 1, 0);
        this.add(roundsPlayedLabel, 2, 0);
    }

    @Override
    public void update(Observable observable) {
        if (observable instanceof ModelFacade) {
            refreshState();
        }
    }

    /**
     * Refreshes the state display to reflect the current game state.
     */
    private void refreshState() {
        scoreLabel.setText("Score: " + modelFacade.getScore());
        validatorsUsedLabel.setText("Validators used: " + modelFacade.getNumberValidatorsTested());
        roundsPlayedLabel.setText("Rounds played: " + modelFacade.getRoundsPlayed());
    }
}
