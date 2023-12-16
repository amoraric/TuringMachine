package g61689.atl.view;

import g61689.atl.model.ModelFacade;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import util.Observable;
import util.Observer;

public class UIView extends Application implements Observer {
    private GridPane uiContainer;
    private Problems problems;
    private ModelFacade modelFacade;
    private Validators validators;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Turing Machine");
        primaryStage.setMinWidth(1400);
        primaryStage.setMinHeight(900);
        setup();
        MenuBar menuBar = createMenuBar(primaryStage);
        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(uiContainer);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void setup() {
        modelFacade = new ModelFacade();
        modelFacade.register(this);
        uiContainer = new GridPane();
        createUI();
    }

    private void createUI() {
        problems = new Problems(modelFacade);
        problems.register(this);

        uiContainer.getChildren().clear();
        HBox hBox = new HBox(problems);
        hBox.setAlignment(Pos.CENTER);
        uiContainer.getChildren().add(hBox);
        uiContainer.setAlignment(Pos.CENTER);
    }

    private MenuBar createMenuBar(Stage primaryStage) {
        MenuBar menuBar = new MenuBar();
        menuBar.setStyle("-fx-font-family: Arial; -fx-font-size: 12;");
        Menu fileMenu = new Menu("File");
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(e -> primaryStage.close());
        fileMenu.getItems().add(exitMenuItem);
        menuBar.getMenus().add(fileMenu);
        return menuBar;
    }

    private void showGame() {
        try {
            modelFacade.startGame(problems.getChosenProblemIndex());

            uiContainer.getChildren().clear(); // new page

            ColumnConstraints column1 = new ColumnConstraints();
            column1.setPercentWidth(33.33);
            ColumnConstraints column2 = new ColumnConstraints();
            column2.setPercentWidth(33.33);
            ColumnConstraints column3 = new ColumnConstraints();
            column3.setPercentWidth(33.33);
            uiContainer.getColumnConstraints().addAll(column1, column2, column3);

            RowConstraints row1 = new RowConstraints();
            row1.setPercentHeight(40);
            RowConstraints row4 = new RowConstraints();
            row4.setPercentHeight(60);
            uiContainer.getRowConstraints().addAll(row1, row4);

            showValidators();
            showEnterCode();
            showState();
        } catch (IllegalArgumentException e) {
            popupMessage("Error", e.getMessage());
        }
    }

    private void showValidators() {
        this.validators = new Validators(modelFacade);
        validators.register(this);

        uiContainer.add(validators, 0, 0, 3, 1);
    }

    private void showEnterCode() {
        VBox userCodeContainer = new VBox(10);
        UserCode userCode = new UserCode(modelFacade, false);
        userCode.register(this);
        Button undo = new UndoRedo(modelFacade, true);
        undo.setAlignment(Pos.CENTER);
        userCodeContainer.setAlignment(Pos.CENTER);
        userCodeContainer.getChildren().addAll(userCode, undo);

        VBox finalCodeContainer = new VBox(10);
        UserCode enterFinalCode = new UserCode(modelFacade, true);
        enterFinalCode.register(this);
        Button redo = new UndoRedo(modelFacade, false);
        redo.setAlignment(Pos.CENTER);
        finalCodeContainer.setAlignment(Pos.CENTER);
        finalCodeContainer.getChildren().addAll(enterFinalCode, redo);

        uiContainer.add(userCodeContainer, 0, 1, 1, 1);
        uiContainer.add(finalCodeContainer, 2, 1, 1, 1);
    }

    private void testValidator() {
        int chosenValidator = validators.getChosenValidator();
        System.out.println(chosenValidator);
        if (modelFacade.canApplyValidator()) {
            if (modelFacade.isUserCodeSet()) {
                modelFacade.chooseValidator(chosenValidator);
                if (modelFacade.getValidatorState(chosenValidator)) {
                    validators.setResult(chosenValidator, "You passed!");
                } else {
                    validators.setResult(chosenValidator, "You didn't pass!");
                }
            } else {
                popupMessage("Error", "You have to set a code first.");
            }
        } else {
            popupMessage("Error", "Only 3 validators can be tested per round.");
        }
    }

    public static void popupMessage(String title, String message) {
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        Font font = new Font("Arial", 12);
        String style = "-fx-font-family: '" + font.getFamily() + "'; -fx-font-size: " + font.getSize() + "pt;";
        alert.getDialogPane().setStyle(style);
        alert.showAndWait();
    }

    private void showState() {
        State state = new State(modelFacade);

        Button skipButton = getButton("Skip Round");
        skipButton.setOnAction(event -> {
            modelFacade.moveToNextRound();
            validators.clearResults();
        });
        Button quitButton = getButton("Restart Game");
        quitButton.setOnAction(event -> resetGame());

        VBox stateContainer = new VBox(20);
        stateContainer.setAlignment(Pos.CENTER);
        stateContainer.getChildren().addAll(skipButton, state, quitButton);

        uiContainer.add(stateContainer, 1, 1);
    }

    private Button getButton(String text) {
        Button button = new Button(text);
        button.setPrefHeight(40);
        button.setMaxWidth(200);
        button.setAlignment(Pos.CENTER);
        VBox.setMargin(button, new Insets(20));
        return button;
    }

    private void resetGame() {
        modelFacade.resetGame();
        uiContainer.getChildren().clear();

        uiContainer.getColumnConstraints().clear();
        uiContainer.getRowConstraints().clear();
        uiContainer.setAlignment(Pos.CENTER);

        createUI();
    }

    @Override
    public void update(Observable observable) {
        if (observable instanceof Problems) {
            showGame();
        } else if (observable instanceof Validators) {
            testValidator();
        } else if (observable instanceof UserCode) {
            if (modelFacade.getUserResult()) {
                popupMessage("Game finished", "You won!");
            } else {
                popupMessage("Game finished", "You lost!");
            }
            resetGame();
        }
    }
}
