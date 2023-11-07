package g61689.atl.bmr.view;

import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

/**
 * The grid pane responsible for the calculation outputs
 */
public class BMRResult extends GridPane {
    private TextField tfdBmr;
    private TextField tfdCalories;

    /**
     * Default constructor that initiates the labels and the textfields
     */
    public BMRResult () {
        Label res = new Label("Results");
        res.setFont(Font.font("Arial", 12));
        res.setUnderline(true);
        this.add(res, 0, 0);

        createBMR();
        createCalories();
    }

    /**
     * Creates the BMR layout
     */
    private void createBMR() {
        Label bmr = new Label("BMR");
        bmr.setFont(Font.font("Arial", 12));
        GridPane.setHalignment(bmr, HPos.LEFT);
        this.add(bmr, 0, 1);

        tfdBmr = new TextField();
        tfdBmr.setEditable(false);
        tfdBmr.setStyle("-fx-prompt-text-fill: gray; -fx-font-family: Arial; -fx-font-size: 12;");
        tfdBmr.setPromptText("BMR Results");
        this.add(tfdBmr, 1, 1);
    }

    /**
     * Creates the Calories layout
     */
    private void createCalories() {
        Label calories = new Label("Calories");
        calories.setFont(Font.font("Arial", 12));
        GridPane.setHalignment(calories, HPos.LEFT);
        this.add(calories, 0, 2);

        tfdCalories = new TextField();
        tfdCalories.setEditable(false);
        tfdCalories.setStyle("-fx-prompt-text-fill: gray; -fx-font-family: Arial; -fx-font-size: 12;");
        tfdCalories.setPromptText("Calories needs");
        this.add(tfdCalories, 1, 2);
    }

    /**
     * Setter of the BMR value
     * @param bmr bmr value
     */
    public void setBMR(double bmr) {
        tfdBmr.setText(bmr+"");
    }

    /**
     * Setter of the Calories value
     *
     * @param calories calories value
     */
    public void setCalories(double calories) {
        tfdCalories.setText(calories+"");
    }

    /**
     * Clears all input fields and result displays in the user interface.
     */
    public void clearFields() {
        tfdBmr.clear();
        tfdCalories.clear();
    }
}
