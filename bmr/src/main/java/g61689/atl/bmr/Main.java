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

public class Main extends Application {
    private static final double[] multipliers = {1.2, 1.375, 1.55, 1.725, 1.9};

    public enum ActivityLevel {
        SEDENTARY("Sedentary"),
        A_LITTLE_ACTIVE("A little active"),
        ACTIVE("Active"),
        VERY_ACTIVE("Very active"),
        EXTREMELY_ACTIVE("Extremely active");

        private final String label;

        ActivityLevel(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("BMR Calculator");
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(290);

        // MenuBar
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem exitMenuItem = new MenuItem("Exit");

        exitMenuItem.setOnAction(e -> primaryStage.close()); // Action to close the application

        fileMenu.getItems().add(exitMenuItem);
        menuBar.getMenus().add(fileMenu);

        VBox vBox = new VBox();
        HBox hBox = new HBox();

        GridPane leftRoot = new GridPane();
        leftRoot.setAlignment(Pos.BASELINE_LEFT);
        leftRoot.setPadding(new Insets(10));
        leftRoot.setHgap(5);
        leftRoot.setVgap(7);

        GridPane rightRoot = new GridPane();
        rightRoot.setAlignment(Pos.BASELINE_LEFT);
        rightRoot.setPadding(new Insets(10));
        rightRoot.setHgap(5);
        rightRoot.setVgap(7);

        // Left GridPane
        Label data = new Label("Data");
        data.setFont(Font.font("System", 12));
        data.setUnderline(true);
        leftRoot.add(data, 0, 0);

        Label height = new Label("Height (cm)");
        height.setFont(Font.font("System", 12));
        GridPane.setHalignment(height, HPos.LEFT);
        leftRoot.add(height, 0, 1);

        TextField tfdHeight = new TextField();
        // Event filter to allow only numeric input
        tfdHeight.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[0-9]")) {
                event.consume();
            }
        });
        tfdHeight.setPromptText("Height in cm");
        leftRoot.add(tfdHeight, 1, 1);

        Label weight = new Label("Weight (kg)");
        weight.setFont(Font.font("System", 12));
        GridPane.setHalignment(height, HPos.LEFT);
        leftRoot.add(weight, 0, 2);

        TextField tfdWeight = new TextField();
        tfdWeight.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[0-9]")) {
                event.consume();
            }
        });
        tfdWeight.setPromptText("Weight in kg");
        leftRoot.add(tfdWeight, 1, 2);

        Label age = new Label("Age (years)");
        age.setFont(Font.font("System", 12));
        GridPane.setHalignment(age, HPos.LEFT);
        leftRoot.add(age, 0, 3);

        TextField tfdAge = new TextField();
        tfdAge.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[0-9]")) {
                event.consume();
            }
        });
        tfdAge.setPromptText("Age in years");
        leftRoot.add(tfdAge, 1, 3);

        Label gender = new Label("Gender");
        gender.setFont(Font.font("System", 12));
        GridPane.setHalignment(gender, HPos.LEFT);
        leftRoot.add(gender, 0, 4);

        RadioButton female = new RadioButton("Female");
        RadioButton male = new RadioButton("Male");

        // Create an HBox to hold the radio buttons
        HBox genderBox = new HBox(10);

        ToggleGroup toggleGroup = new ToggleGroup(); // Group the radio buttons
        female.setToggleGroup(toggleGroup);
        male.setToggleGroup(toggleGroup);

        genderBox.getChildren().addAll(female, male);
        leftRoot.add(genderBox, 1, 4); // Add the HBox to the grid

        Label lifeStyle = new Label("Life Style");
        lifeStyle.setFont(Font.font("System", 12));
        GridPane.setHalignment(lifeStyle, HPos.LEFT);
        leftRoot.add(lifeStyle, 0, 5);

        ObservableList<ActivityLevel> options = FXCollections.observableArrayList(ActivityLevel.values());
        ChoiceBox<ActivityLevel> choiceBox = new ChoiceBox<>(options);
        choiceBox.setValue(ActivityLevel.SEDENTARY);
        // Event handler for a button to get the chosen value
        choiceBox.setOnAction(e -> {
            ActivityLevel chosenValue = choiceBox.getValue();
            System.out.println("Chosen value: " + chosenValue);
        });

        // Add the ListView to the VBox
        leftRoot.add(choiceBox, 1, 5);

        GridPane.setFillWidth(tfdHeight, true);
        GridPane.setFillWidth(tfdWeight, true);
        GridPane.setFillWidth(tfdAge, true);

        // Right GridPane
        Label res = new Label("Results");
        res.setFont(Font.font("System", 12));
        res.setUnderline(true);
        rightRoot.add(res, 0, 0);

        Label bmr = new Label("BMR");
        bmr.setFont(Font.font("System", 12));
        GridPane.setHalignment(bmr, HPos.LEFT);
        rightRoot.add(bmr, 0, 1);

        TextField tfdBmr = new TextField();
        tfdBmr.setEditable(false);
        tfdBmr.setPromptText("BMR Results");
        rightRoot.add(tfdBmr, 1, 1);

        Label calories = new Label("Calories");
        calories.setFont(Font.font("System", 12));
        GridPane.setHalignment(calories, HPos.LEFT);
        rightRoot.add(calories, 0, 2);

        TextField tfdCalories = new TextField();
        tfdCalories.setEditable(false);
        tfdCalories.setPromptText("Calories needs");
        rightRoot.add(tfdCalories, 1, 2);

        // BMR button
        Button bmrButton = new Button("Calculate the BMR");
        bmrButton.setMaxWidth(Double.MAX_VALUE);
        VBox.setMargin(bmrButton, new Insets(6));
        // Verifies the fields and calculates the BMR
        bmrButton.setOnAction(e -> {
            if (checkConstraintsText(tfdHeight, tfdWeight, tfdAge) && checkConstraintsGender(female, male)) {
                double bmrRes;
                if (female.isSelected()) {
                    bmrRes = calculateBmr(choiceBox.getSelectionModel().getSelectedIndex(), true, tfdWeight, tfdHeight, tfdAge);
                } else {
                    bmrRes = calculateBmr(choiceBox.getSelectionModel().getSelectedIndex(), false, tfdWeight, tfdHeight, tfdAge);
                }
                tfdBmr.setText(String.valueOf(Math.round(bmrRes)));
            }
        });

        // Clear button
        Button clearButton = new Button("Clear");
        clearButton.setMaxWidth(Double.MAX_VALUE);
        VBox.setMargin(clearButton, new Insets(6));
        // Clears the textFields
        clearButton.setOnAction(e -> {
            tfdHeight.clear();
            tfdWeight.clear();
            tfdAge.clear();
            tfdBmr.clear();
            tfdCalories.clear();
        });

        hBox.getChildren().addAll(leftRoot, rightRoot);
        vBox.getChildren().addAll(hBox, bmrButton, clearButton);

        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);

        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(vBox); // Add the VBox with your UI to the center of the BorderPane

        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        primaryStage.show();
    }

    private boolean checkConstraintsText(TextField... tfs) {
        if (tfs[0].getText().equals("0")||tfs[1].getText().equals("0")||tfs[2].getText().equals("0")) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter valid (non-zero) values in the fields.");
            alert.showAndWait();
        } else if (tfs[0].getText().isEmpty()||tfs[1].getText().isEmpty()||tfs[2].getText().isEmpty()) {
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

    private boolean checkConstraintsGender(RadioButton... rbs) {
        if ((!rbs[0].isSelected() && !rbs[1].isSelected())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please choose a gender.");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    private double calculateBmr(int selectedIndex, boolean isFemale, TextField... tfs) {
        int weightInt = Integer.parseInt(tfs[0].getText());
        int heightInt = Integer.parseInt(tfs[1].getText());
        int ageInt = Integer.parseInt(tfs[2].getText());
        if (isFemale) {
            return (9.6 * weightInt + 1.8 * heightInt - 4.7 * ageInt + 655) * multipliers[selectedIndex];
        } else {
            return (13.7 * weightInt + 5 * heightInt - 6.8 * ageInt + 66) * multipliers[selectedIndex];
        }
    }
}
