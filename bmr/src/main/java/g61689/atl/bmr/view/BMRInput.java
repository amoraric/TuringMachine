package g61689.atl.bmr.view;

import g61689.atl.bmr.model.LifeStyle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * The grid pane responsible for the user inputs
 */
public class BMRInput extends GridPane {
    private TextField tfdHeight;
    private TextField tfdWeight;
    private TextField tfdAge;
    private RadioButton female;
    private RadioButton male;
    private ChoiceBox<LifeStyle> choiceBox;

    /**
     * Default constructor that initiates the labels and the textfields
     */
    public BMRInput () {
        Label data = new Label("Data");
        data.setFont(Font.font("Arial", 12));
        data.setUnderline(true);
        this.add(data, 0, 0);

        createHeight();
        createWeight();
        createAge();
        createGender();
        createLifeStyle();

        GridPane.setFillWidth(tfdHeight, true);
        GridPane.setFillWidth(tfdWeight, true);
        GridPane.setFillWidth(tfdAge, true);
    }

    private void createHeight() {
        Label height = new Label("Height (cm)");
        height.setFont(Font.font("Arial", 12));
        GridPane.setHalignment(height, HPos.LEFT);
        this.add(height, 0, 1);

        tfdHeight = new TextField();
        // Event filter to allow only numeric input
        tfdHeight.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[0-9]")) {
                event.consume();
            }
        });
        tfdHeight.setStyle("-fx-prompt-text-fill: gray; -fx-font-family: Arial; -fx-font-size: 12;");
        tfdHeight.setPromptText("Height in cm");
        this.add(tfdHeight, 1, 1);
    }

    private void createWeight() {
        Label weight = new Label("Weight (kg)");
        weight.setFont(Font.font("Arial", 12));
        GridPane.setHalignment(weight, HPos.LEFT);
        this.add(weight, 0, 2);

        tfdWeight = new TextField();
        tfdWeight.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[0-9]")) {
                event.consume();
            }
        });
        tfdWeight.setStyle("-fx-prompt-text-fill: gray; -fx-font-family: Arial; -fx-font-size: 12;");
        tfdWeight.setPromptText("Weight in kg");
        this.add(tfdWeight, 1, 2);
    }

    private void createAge() {
        Label age = new Label("Age (years)");
        age.setFont(Font.font("Arial", 12));
        GridPane.setHalignment(age, HPos.LEFT);
        this.add(age, 0, 3);

        tfdAge = new TextField();
        tfdAge.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[0-9]")) {
                event.consume();
            }
        });
        tfdAge.setStyle("-fx-prompt-text-fill: gray; -fx-font-family: Arial; -fx-font-size: 12;");
        tfdAge.setPromptText("Age in years");
        this.add(tfdAge, 1, 3);
    }

    private void createGender() {
        Label gender = new Label("Gender");
        gender.setFont(Font.font("Arial", 12));
        GridPane.setHalignment(gender, HPos.LEFT);
        this.add(gender, 0, 4);

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
        this.add(genderBox, 1, 4); // Add the HBox to the grid
    }

    private void createLifeStyle() {
        Label lifeStyle = new Label("Life Style");
        lifeStyle.setFont(Font.font("Arial", 12));
        GridPane.setHalignment(lifeStyle, HPos.LEFT);
        this.add(lifeStyle, 0, 5);

        ObservableList<LifeStyle> options = FXCollections.observableArrayList(LifeStyle.values());
        choiceBox = new ChoiceBox<>(options);
        choiceBox.setValue(LifeStyle.SEDENTARY);
        choiceBox.setStyle("-fx-font-family: Arial; -fx-font-size: 12;");

        // Add the ListView to the VBox
        this.add(choiceBox, 1, 5);
    }

    /**
     * Clears all input fields and result displays in the user interface.
     */
    public void clearFields() {
        tfdHeight.clear();
        tfdWeight.clear();
        tfdAge.clear();
    }

    /**
     * Getter for the height
     *
     * @return height
     */
    public double getHeightBMR() {
        return Double.parseDouble(tfdHeight.getText());
    }

    /**
     * Getter for the weight
     *
     * @return weight
     */
    public double getWeightBMR() {
        return Double.parseDouble(tfdWeight.getText());
    }

    /**
     * Getter for the age
     *
     * @return age
     */
    public int getAgeBMR() {
        return Integer.parseInt(tfdAge.getText());
    }

    /**
     * Getter for the gender, true if it's a woman
     *
     * @return boolean, true if woman
     */
    public boolean isWoman() {
        return female.isSelected();
    }

    /**
     * Getter for the height
     *
     * @return height
     */
    public int getLifeStyle() {
        return choiceBox.getSelectionModel().getSelectedIndex();
    }

    /**
     * Checks if the text fields have valid data for calculations.
     *
     * @return True if the text fields have valid (non-zero) values, false otherwise
     */
    public boolean checkConstraintsTextZero() {
        if (tfdHeight.getText().equals("0") || tfdWeight.getText().equals("0") || tfdAge.getText().equals("0")) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter valid (non-zero) values in the fields.");
            Font font = new Font("Arial", 12);
            String style = "-fx-font-family: '" + font.getFamily() + "'; -fx-font-size: " + font.getSize() + "pt;";
            alert.getDialogPane().setStyle(style);
            alert.showAndWait();
            return false;
        }
        return true;
    }

    /**
     * Checks if the text fields have valid data for calculations.
     *
     * @return True if the text fields have valid (non-zero) values, false otherwise
     */
    public boolean checkConstraintsTextEmpty() {
        if (tfdHeight.getText().isEmpty() || tfdWeight.getText().isEmpty() || tfdAge.getText().isEmpty()) {
            // This is for when the user presses the clear button and the fields aren't filled with anything
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter valid (non-zero) values in the fields.");
            Font font = new Font("Arial", 12);
            String style = "-fx-font-family: '" + font.getFamily() + "'; -fx-font-size: " + font.getSize() + "pt;";
            alert.getDialogPane().setStyle(style);
            alert.showAndWait();
            return false;
        }
        return true;
    }

    /**
     * Checks if the gender is selected.
     *
     * @return True if a gender is chosen, false otherwise
     */
    public boolean checkConstraintsGender() {
        if ((!female.isSelected() && !male.isSelected())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please choose a gender.");
            Font font = new Font("Arial", 12);
            String style = "-fx-font-family: '" + font.getFamily() + "'; -fx-font-size: " + font.getSize() + "pt;";
            alert.getDialogPane().setStyle(style);
            alert.showAndWait();
            return false;
        }
        return true;
    }
}
