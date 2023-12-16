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

/**
 * Main user interface class for the Turing Machine game using JavaFX.
 * This class sets up and displays the graphical user interface for the game.
 */
public class UIView extends Application implements Observer {
    private GridPane uiContainer;
    private Problems problems;
    private ModelFacade modelFacade;
    private Validators validators;

    /**
     * Main method to launch the JavaFX application.
     *
     * @param args Command line arguments.
     */
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

    /**
     * Sets up the initial state and components of the UI.
     */
    private void setup() {
        modelFacade = new ModelFacade();
        modelFacade.register(this);
        uiContainer = new GridPane();
        createUI();
    }

    /**
     * Creates the main UI layout and components.
     */
    private void createUI() {
        problems = new Problems(modelFacade);
        problems.register(this);

        uiContainer.getChildren().clear();
        HBox hBox = new HBox(problems);
        hBox.setAlignment(Pos.CENTER);
        uiContainer.getChildren().add(hBox);
        uiContainer.setAlignment(Pos.CENTER);
    }

    /**
     * Creates the menu bar for the UI.
     *
     * @param primaryStage The primary stage of the application.
     * @return A MenuBar object.
     */
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

    /**
     * Displays the game UI, allowing user interaction with the game.
     */
    private void showGame() {
        try {
            modelFacade.startGame(problems.getChosenProblemIndex());

            uiContainer.getChildren().clear(); // new page

            applyGameUIConstraints();

            showValidators();
            showEnterCode();
            showState();
        } catch (IllegalArgumentException e) {
            popupMessage("Error", e.getMessage());
        }
    }

    /**
     * Applies layout constraints for the game UI.
     */
    private void applyGameUIConstraints() {
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(33.33);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(33.33);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(33.33);
        uiContainer.getColumnConstraints().addAll(column1, column2, column3);

        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(40);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(60);
        uiContainer.getRowConstraints().addAll(row1, row2);
    }

    /**
     * Displays the validators interface in the game UI.
     */
    private void showValidators() {
        this.validators = new Validators(modelFacade);

        uiContainer.add(validators, 0, 0, 3, 1);
    }

    /**
     * Displays the interface for entering the user code.
     */
    private void showEnterCode() {
        VBox userCodeContainer = getUserCodeInterface(false, true);
        VBox finalCodeContainer = getUserCodeInterface(true, false);

        uiContainer.add(userCodeContainer, 0, 1, 1, 1);
        uiContainer.add(finalCodeContainer, 2, 1, 1, 1);
    }

    /**
     * Creates a user code interface box.
     *
     * @param isFinalCode Specifies if the interface is for the final code.
     * @param isLeft      Specifies if the interface is on the left side.
     * @return A VBox containing the user code interface.
     */
    private VBox getUserCodeInterface(boolean isFinalCode, boolean isLeft) {
        VBox userCodeContainer = new VBox(10);
        UserCode userCode = new UserCode(modelFacade, isFinalCode);
        userCode.register(this);
        Button undo = new UndoRedo(modelFacade, isLeft);
        undo.setAlignment(Pos.CENTER);
        userCodeContainer.setAlignment(Pos.CENTER);
        userCodeContainer.getChildren().addAll(userCode, undo);
        return userCodeContainer;
    }

    /**
     * Displays the current state of the game, including controls and status.
     */
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

    /**
     * Creates and displays a popup message window.
     *
     * @param title   The title of the popup window.
     * @param message The message to be displayed.
     */
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

    /**
     * Creates a button with specified text.
     *
     * @param text The text to display on the button.
     * @return A Button object.
     */
    private Button getButton(String text) {
        Button button = new Button(text);
        button.setPrefHeight(40);
        button.setMaxWidth(200);
        button.setAlignment(Pos.CENTER);
        VBox.setMargin(button, new Insets(20));
        return button;
    }

    /**
     * Resets the game to its initial state and updates the UI.
     */
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
