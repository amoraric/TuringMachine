package g61689.atl.bmr.view;

import g61689.atl.bmr.model.Person;
import g61689.atl.util.Observer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * View class that initiates the app, creates the ui with buttons and makes calculations
 */
public class View extends Application implements Observer {
    private BMRInput bmrInput;
    private BMRResult bmrResult;

    private Person person;

    /**
     * Main method to launch the JavaFX application.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Entry point for the application, sets up the primary stage and initializes the UI.
     *
     * @param primaryStage The primary stage of the application
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("BMR Calculator");
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(290);
        person = new Person();
        person.register(this);
        MenuBar menuBar = createMenuBar(primaryStage);
        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(createUI());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Creates the menu bar with an "Exit" option to close the application.
     *
     * @param primaryStage The primary stage of the application
     * @return MenuBar containing the "Exit" option
     */
    private MenuBar createMenuBar(Stage primaryStage) {
        MenuBar menuBar = new MenuBar();
        menuBar.setStyle("-fx-font-family: Arial; -fx-font-size: 12;");
        Menu fileMenu = new Menu("File");
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(e -> primaryStage.close()); // Action to close the application
        fileMenu.getItems().add(exitMenuItem);
        menuBar.getMenus().add(fileMenu);
        return menuBar;
    }

    /**
     * Generates the main layout for the BMR Calculator application.
     *
     * @return A VBox containing the user interface components
     */
    private VBox createUI() {
        VBox vBox = new VBox();
        HBox hBox = new HBox();
        createBMRInput();
        createBMRResult();
        Button bmrButton = createBMRButton();
        Button clearButton = createClearButton();
        hBox.getChildren().addAll(bmrInput, bmrResult);
        vBox.getChildren().addAll(hBox, bmrButton, clearButton);
        return vBox;
    }

    /**
     * Creates the BMRInput object
     */
    private void createBMRInput() {
        bmrInput = new BMRInput();
        bmrInput.setAlignment(Pos.BASELINE_LEFT);
        bmrInput.setPadding(new Insets(10));
        bmrInput.setHgap(5);
        bmrInput.setVgap(7);
    }

    /**
     * Creates the BMRResult object
     */
    private void createBMRResult() {
        bmrResult = new BMRResult();
        bmrResult.setAlignment(Pos.BASELINE_LEFT);
        bmrResult.setPadding(new Insets(10));
        bmrResult.setHgap(5);
        bmrResult.setVgap(7);
    }

    /**
     * Creates the "Calculate BMR" button that triggers the calculation of the BMR.
     *
     * @return A Button for calculating the BMR
     */
    private Button createBMRButton() {
        Button bmrButton = new Button("Calculate the BMR");
        bmrButton.setFont(Font.font("Arial", 12));
        bmrButton.setMaxWidth(Double.MAX_VALUE);
        VBox.setMargin(bmrButton, new Insets(6));
        bmrButton.setOnAction(e -> {
            if (bmrInput.checkConstraintsTextEmpty() && bmrInput.checkConstraintsTextZero()
                    && bmrInput.checkConstraintsGender()) {
                person.set(bmrInput.selectedGender(), bmrInput.getHeightBMR(), bmrInput.getWeightBMR(),
                        bmrInput.getAgeBMR(), bmrInput.getLifeStyle());
            }
        });
        return bmrButton;
    }

    /**
     * Creates the "Clear" button, which resets all user input fields and result displays.
     *
     * @return A Button to clear all input fields and result displays
     */
    private Button createClearButton() {
        Button clearButton = new Button("Clear");
        clearButton.setFont(Font.font("Arial", 12));
        clearButton.setMaxWidth(Double.MAX_VALUE);
        VBox.setMargin(clearButton, new Insets(6));
        clearButton.setOnAction(e -> {
            bmrInput.clearFields();
            bmrResult.clearFields();
        });
        return clearButton;
    }

    @Override
    public void update() {
        double bmr = person.calculateBmr();
        double calories = person.calculateCalories();

        bmrResult.setBMR(Math.round(bmr));
        bmrResult.setCalories(Math.round(calories));
    }
}
