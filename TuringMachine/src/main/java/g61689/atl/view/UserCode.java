package g61689.atl.view;

import g61689.atl.model.ModelFacade;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import util.Observable;
import util.Observer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserCode extends VBox implements Observable {
    private final List<Observer> observers;
    private final Text[] columnTexts = new Text[3];
    private int[] userCode = new int[3];

    public UserCode(ModelFacade modelFacade) {
        observers = new ArrayList<>();
        setup(modelFacade);
    }

    private void setup(ModelFacade modelFacade) {
        this.setPadding(new Insets(10));

        HBox displayArea = new HBox(0);
        for (int i = 0; i < columnTexts.length; i++) {
            columnTexts[i] = new Text("_"); // Initialize with underscore or space
            displayArea.getChildren().add(columnTexts[i]);
        }

        GridPane grid = new GridPane();
        grid.setHgap(10); // Horizontal spacing
        grid.setVgap(10); // Vertical spacing

        // Create geometric figures
        Polygon triangle = createTriangle();
        Rectangle square = createSquare();
        Circle circle = createCircle();

        // Adding shapes to the grid
        grid.add(triangle, 0, 0);
        grid.add(square, 1, 0);
        grid.add(circle, 2, 0);

        // Creating buttons for each number
        for (int i = 1; i <= 5; i++) {
            for (int j = 0; j < 3; j++) {
                Button btn = new Button(String.valueOf(i));
                final int col = j;
                btn.setOnAction(event -> buttonPressed(col, ((Button) event.getSource()).getText()));
                grid.add(btn, j, i);
            }
        }

        Button chooseButton = new Button("CHOISISSEZ CE CODE");
        chooseButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-border-radius: 20; -fx-background-radius: 20;");
        chooseButton.setPadding(new Insets(1, 2, 1, 2));
        chooseButton.setMaxWidth(Double.MAX_VALUE);
        HBox.setMargin(chooseButton, new Insets(1, 0, 0, 0));
        chooseButton.setOnAction(e -> {
            StringBuilder code = new StringBuilder();
            for (int cc : userCode) {
                code.append(cc);
            }
            if (code.length() == 3 && modelFacade.getNumberValidatorsTested() == 0) {
                modelFacade.setUserCode(Integer.parseInt(code.toString()));
            }
        });

        // Adding components to the root container
        this.getChildren().addAll(displayArea, grid, chooseButton);
    }

    private void buttonPressed(int column, String number) {
        columnTexts[column].setText(number);
        userCode[column] = Integer.parseInt(number);
    }

    private Polygon createTriangle() {
        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(new Double[]{10.0, 0.0, 20.0, 20.0, 0.0, 20.0});
        triangle.setFill(Color.BLUE);
        return triangle;
    }

    private Rectangle createSquare() {
        Rectangle square = new Rectangle(20, 20);
        square.setFill(Color.ORANGE);
        return square;
    }

    private Circle createCircle() {
        Circle circle = new Circle(10);
        circle.setFill(Color.PURPLE);
        return circle;
    }

    @Override
    public boolean register(Observer obs) {
        if (!observers.contains(obs)) {
            return observers.add(obs);
        }
        return false;
    }

    @Override
    public boolean unregister(Observer obs) {
        return observers.remove(obs);
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}
