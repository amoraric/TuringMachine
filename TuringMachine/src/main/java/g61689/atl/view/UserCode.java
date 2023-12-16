package g61689.atl.view;

import g61689.atl.model.ModelFacade;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import util.Observable;
import util.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserCode extends VBox implements Observable, Observer {
    private final List<Observer> observers;
    private int[] userCode = new int[3];
    private final Text codeDisplay = new Text();
    private final ModelFacade modelFacade;
    private final boolean isFinalCode;

    public UserCode(ModelFacade modelFacade, boolean isFinalCode) {
        observers = new ArrayList<>();
        this.modelFacade = modelFacade;
        this.isFinalCode = isFinalCode;
        modelFacade.register(this);
        setup(modelFacade, isFinalCode);
    }

    private void setup(ModelFacade modelFacade, boolean isFinalCode) {
        this.setPadding(new Insets(10));
        this.setSpacing(5);
        this.setAlignment(Pos.TOP_CENTER);

        String description = "Enter your code:";
        String buttonText;
        if (isFinalCode) {
            description = "Guess the final code:";
            buttonText = "Guess code";
        } else {
            buttonText = "Enter code";
        }

        Text descriptionText = new Text(description);
        this.getChildren().add(descriptionText);

        this.getChildren().add(codeDisplay);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        grid.add(createTriangle(), 0, 0);
        grid.add(createSquare(), 1, 0);
        grid.add(createCircle(), 2, 0);

        for (int i = 1; i <= 5; i++) {
            for (int j = 0; j < 3; j++) {
                Button btn = new Button(String.valueOf(i));
                final int col = j;
                btn.setOnAction(event -> buttonPressed(col, ((Button) event.getSource()).getText(), modelFacade, buttonText));
                grid.add(btn, j, i);
            }
        }

        this.getChildren().add(grid);

        Button chooseButton = getButton(modelFacade, buttonText);
        this.getChildren().add(chooseButton);
    }

    private Button getButton(ModelFacade modelFacade, String buttonText) {
        Button chooseButton = new Button(buttonText);
        chooseButton.setOnAction(e -> {
            StringBuilder code = new StringBuilder();
            boolean isCodeSet = true;
            for (int cc : userCode) {
                if (cc == 0) {
                    isCodeSet = false;
                }
                code.append(cc);
            }
            if (!isCodeSet) {
                UIView.popupMessage("Error", "You have to enter the whole code.");
            } else {
                if (Objects.equals(buttonText, "Guess code")) {
                    modelFacade.enterCode(Integer.parseInt(code.toString()));
                    notifyObservers();
                } else {
                    if (modelFacade.getNumberValidatorsTested() == 0) {
                        modelFacade.setUserCode(Integer.parseInt(code.toString()));
                    } else {
                        UIView.popupMessage("Error", "You cannot change the code after testing a validator.");
                    }
                }
            }
        });
        return chooseButton;
    }

    private void buttonPressed(int column, String number, ModelFacade modelFacade, String buttonText) {
        if (Objects.equals(buttonText, "Guess code")) {
            userCode[column] = Integer.parseInt(number);
            updateCodeDisplay();
        } else {
            if (userCode.length == 3 && modelFacade.getNumberValidatorsTested() == 0) {
                userCode[column] = Integer.parseInt(number);
                updateCodeDisplay();
            }
        }
    }

    private void updateCodeDisplay() {
        StringBuilder codeBuilder = new StringBuilder();
        for (int num : userCode) {
            codeBuilder.append(num);
        }
        codeDisplay.setText("Code: " + codeBuilder);
    }

    private Polygon createTriangle() {
        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(10.0, 0.0, 20.0, 20.0, 0.0, 20.0);
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

    private void updateResultsBasedOnState() {
        if (isFinalCode) {
            if (!modelFacade.isGuessCodeSet()) {
                this.userCode = new int[3];
            } else {
                int guessCode = modelFacade.getGuessCode();
                updateUserCodeArray(guessCode);
            }
        } else {
            if (!modelFacade.isUserCodeSet()) {
                this.userCode = new int[3];
            } else {
                int userCode = modelFacade.getUserCode();
                updateUserCodeArray(userCode);
            }
        }
        updateCodeDisplay();
    }

    private void updateUserCodeArray(int code) {
        this.userCode[0] = code / 100;
        this.userCode[1] = (code / 10) % 10;
        this.userCode[2] = code % 10;
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

    @Override
    public void update(Observable observable) {
        if (observable instanceof ModelFacade) {
            updateResultsBasedOnState();
        }
    }
}