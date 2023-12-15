package g61689.atl.view;

import g61689.atl.model.ModelFacade;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private final int[] userCode = new int[3];
    private final Text codeDisplay = new Text();

    public UserCode(ModelFacade modelFacade) {
        observers = new ArrayList<>();
        setup(modelFacade);
    }

    private void setup(ModelFacade modelFacade) {
        this.setPadding(new Insets(10));
        this.setSpacing(5);
        this.setAlignment(Pos.TOP_CENTER);

        // First Line: Text
        Text descriptionText = new Text("Enter your code:");
        this.getChildren().add(descriptionText);

        // Second Line: Code Display
        this.getChildren().add(codeDisplay);

        // Grid for Shapes and Buttons
        GridPane grid = new GridPane();
        grid.setHgap(10); // Horizontal spacing
        grid.setVgap(10); // Vertical spacing
        grid.setAlignment(Pos.CENTER);

        // Geometrical Shapes
        grid.add(createTriangle(), 0, 0);
        grid.add(createSquare(), 1, 0);
        grid.add(createCircle(), 2, 0);

        // Buttons
        for (int i = 1; i <= 5; i++) {
            for (int j = 0; j < 3; j++) {
                Button btn = new Button(String.valueOf(i));
                final int col = j;
                btn.setOnAction(event -> buttonPressed(col, ((Button) event.getSource()).getText()));
                grid.add(btn, j, i);
            }
        }

        this.getChildren().add(grid);

        // Enter Button
        Button chooseButton = new Button("Enter Code");
        chooseButton.setOnAction(e -> {
            StringBuilder code = new StringBuilder();
            for (int cc : userCode) {
                code.append(cc);
            }
            if (code.length() == 3 && modelFacade.getNumberValidatorsTested() == 0) {
                modelFacade.setUserCode(Integer.parseInt(code.toString()));
            }
        });
        this.getChildren().add(chooseButton);
    }

    private void buttonPressed(int column, String number) {
        userCode[column] = Integer.parseInt(number);
        updateCodeDisplay();
    }

    private void updateCodeDisplay() {
        StringBuilder codeBuilder = new StringBuilder();
        for (int num : userCode) {
            codeBuilder.append(num);
        }
        codeDisplay.setText("Code: " + codeBuilder.toString());
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
