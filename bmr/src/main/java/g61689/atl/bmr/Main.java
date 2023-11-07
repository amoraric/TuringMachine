package g61689.atl.bmr;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Main class of the BMR (Basal Metabolic Rate) Calculator Application.
 *
 * @author Morari Augustin-Constantin
 */
public class Main extends Application {
    private static final double[] multipliers = {1.2, 1.375, 1.55, 1.725, 1.9};
    private TextField tfdHeight, tfdWeight, tfdAge, tfdBmr, tfdCalories;
    private RadioButton female, male;
    private ChoiceBox<ActivityLevel> choiceBox;

    /**
     * Enumeration representing different activity levels for the BMR Calculator.
     */
    public enum ActivityLevel {
        SEDENTARY("Sedentary"),
        A_LITTLE_ACTIVE("A little active"),
        ACTIVE("Active"),
        VERY_ACTIVE("Very active"),
        EXTREMELY_ACTIVE("Extremely active");

        private final String label;

        /**
         * Constructs an ActivityLevel with a descriptive label.
         *
         * @param label A string representing the label for the activity level.
         */
        ActivityLevel(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

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

        GridPane leftRoot = createLeftGridPane();
        leftRoot.setAlignment(Pos.BASELINE_LEFT);
        leftRoot.setPadding(new Insets(10));
        leftRoot.setHgap(5);
        leftRoot.setVgap(7);

        GridPane rightRoot = createRightGridPane();
        rightRoot.setAlignment(Pos.BASELINE_LEFT);
        rightRoot.setPadding(new Insets(10));
        rightRoot.setHgap(5);
        rightRoot.setVgap(7);

        Button bmrButton = createBMRButton();
        Button clearButton = createClearButton();

        hBox.getChildren().addAll(leftRoot, rightRoot);
        vBox.getChildren().addAll(hBox, bmrButton, clearButton);

        return vBox;
    }

    /**
     * Creates the left-side grid pane for user data input.
     *
     * @return A GridPane representing the input section of the user interface
     */
    private GridPane createLeftGridPane() {
        GridPane root = new GridPane();
        Label data = new Label("Data");
        data.setFont(Font.font("Arial", 12));
        data.setUnderline(true);
        root.add(data, 0, 0);

        Label height = new Label("Height (cm)");
        height.setFont(Font.font("Arial", 12));
        GridPane.setHalignment(height, HPos.LEFT);
        root.add(height, 0, 1);

        tfdHeight = new TextField();
        // Event filter to allow only numeric input
        tfdHeight.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[0-9]")) {
                event.consume();
            }
        });
        tfdHeight.setStyle("-fx-prompt-text-fill: gray; -fx-font-family: Arial; -fx-font-size: 12;");
        tfdHeight.setPromptText("Height in cm");
        root.add(tfdHeight, 1, 1);

        Label weight = new Label("Weight (kg)");
        weight.setFont(Font.font("Arial", 12));
        GridPane.setHalignment(height, HPos.LEFT);
        root.add(weight, 0, 2);

        tfdWeight = new TextField();
        tfdWeight.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[0-9]")) {
                event.consume();
            }
        });
        tfdWeight.setStyle("-fx-prompt-text-fill: gray; -fx-font-family: Arial; -fx-font-size: 12;");
        tfdWeight.setPromptText("Weight in kg");
        root.add(tfdWeight, 1, 2);

        Label age = new Label("Age (years)");
        age.setFont(Font.font("Arial", 12));
        GridPane.setHalignment(age, HPos.LEFT);
        root.add(age, 0, 3);

        tfdAge = new TextField();
        tfdAge.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[0-9]")) {
                event.consume();
            }
        });
        tfdAge.setStyle("-fx-prompt-text-fill: gray; -fx-font-family: Arial; -fx-font-size: 12;");
        tfdAge.setPromptText("Age in years");
        root.add(tfdAge, 1, 3);

        Label gender = new Label("Gender");
        gender.setFont(Font.font("Arial", 12));
        GridPane.setHalignment(gender, HPos.LEFT);
        root.add(gender, 0, 4);

        female = new RadioButton("Female");
        female.setFont(Font.font("Arial", 12));
        male = new RadioButton("Male");
        male.setFont(Font.font("Arial", 12));

        // Create an HBox to hold the radio buttons
        HBox genderBox = new HBox(10);

        ToggleGroup toggleGroup = new ToggleGroup(); // Group the radio buttons
        female.setToggleGroup(toggleGroup);
        male.setToggleGroup(toggleGroup);

        genderBox.getChildren().addAll(female, male);
        root.add(genderBox, 1, 4); // Add the HBox to the grid

        Label lifeStyle = new Label("Life Style");
        lifeStyle.setFont(Font.font("Arial", 12));
        GridPane.setHalignment(lifeStyle, HPos.LEFT);
        root.add(lifeStyle, 0, 5);

        ObservableList<ActivityLevel> options = FXCollections.observableArrayList(ActivityLevel.values());
        choiceBox = new ChoiceBox<>(options);
        choiceBox.setValue(ActivityLevel.SEDENTARY);
        choiceBox.setStyle("-fx-font-family: Arial; -fx-font-size: 12;");

        // Add the ListView to the VBox
        root.add(choiceBox, 1, 5);

        GridPane.setFillWidth(tfdHeight, true);
        GridPane.setFillWidth(tfdWeight, true);
        GridPane.setFillWidth(tfdAge, true);
        return root;
    }

    /**
     * Creates the right-side grid pane for displaying BMR and calorie estimation results.
     *
     * @return A GridPane representing the results section of the user interface
     */
    private GridPane createRightGridPane() {
        GridPane root = new GridPane();
        Label res = new Label("Results");
        res.setFont(Font.font("Arial", 12));
        res.setUnderline(true);
        root.add(res, 0, 0);

        Label bmr = new Label("BMR");
        bmr.setFont(Font.font("Arial", 12));
        GridPane.setHalignment(bmr, HPos.LEFT);
        root.add(bmr, 0, 1);

        tfdBmr = new TextField();
        tfdBmr.setEditable(false);
        tfdBmr.setStyle("-fx-prompt-text-fill: gray; -fx-font-family: Arial; -fx-font-size: 12;");
        tfdBmr.setPromptText("BMR Results");
        root.add(tfdBmr, 1, 1);

        Label calories = new Label("Calories");
        calories.setFont(Font.font("Arial", 12));
        GridPane.setHalignment(calories, HPos.LEFT);
        root.add(calories, 0, 2);

        tfdCalories = new TextField();
        tfdCalories.setEditable(false);
        tfdCalories.setStyle("-fx-prompt-text-fill: gray; -fx-font-family: Arial; -fx-font-size: 12;");
        tfdCalories.setPromptText("Calories needs");
        root.add(tfdCalories, 1, 2);
        return root;
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
        bmrButton.setOnAction(e -> calculateBMR());
        return bmrButton;
    }

    /**
     * Handles the logic for calculating the BMR based on user input.
     */
    private void calculateBMR() {
        if (checkConstraintsText() && checkConstraintsGender()) {
            double bmrRes = calculateBmr();
            tfdCalories.setText(String.valueOf(Math.round(bmrRes)));
            tfdBmr.setText(String.valueOf(Math.round(bmrRes/multipliers[choiceBox.getSelectionModel().getSelectedIndex()])));
        }
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
        clearButton.setOnAction(e -> clearFields());
        return clearButton;
    }

    /**
     * Clears all input fields and result displays in the user interface.
     */
    private void clearFields() {
        tfdHeight.clear();
        tfdWeight.clear();
        tfdAge.clear();
        tfdBmr.clear();
        tfdCalories.clear();
    }

    /**
     * Checks if the text fields have valid data for calculations.
     *
     * @return True if the text fields have valid (non-zero) values, false otherwise
     */
    private boolean checkConstraintsText() {
        if (tfdHeight.getText().equals("0") || tfdWeight.getText().equals("0") || tfdAge.getText().equals("0")) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter valid (non-zero) values in the fields.");
            alert.showAndWait();
        } else if (tfdHeight.getText().isEmpty() || tfdWeight.getText().isEmpty() || tfdAge.getText().isEmpty()) {
            // This is for when the user presses the clear button and the fields aren't filled with anything
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter valid (non-zero) values in the fields.");
            alert.showAndWait();
        } else {
            return true;
        }
        return false;
    }

    /**
     * Checks if the gender is selected.
     *
     * @return True if a gender is chosen, false otherwise
     */
    private boolean checkConstraintsGender() {
        if ((!female.isSelected() && !male.isSelected())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please choose a gender.");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    /**
     * Calculates the BMR based on user inputs.
     *
     * @return The calculated BMR value
     */
    private double calculateBmr() {
        int heightInt = Integer.parseInt(tfdHeight.getText());
        int weightInt = Integer.parseInt(tfdWeight.getText());
        int ageInt = Integer.parseInt(tfdAge.getText());
        int selectedIndex = choiceBox.getSelectionModel().getSelectedIndex();
        if (female.isSelected()) {
            return (9.6 * weightInt + 1.8 * heightInt - 4.7 * ageInt + 655) * multipliers[selectedIndex];
        } else {
            return (13.7 * weightInt + 5 * heightInt - 6.8 * ageInt + 66) * multipliers[selectedIndex];
        }
    }
}
