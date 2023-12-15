package g61689.atl.view;

import g61689.atl.model.ModelFacade;
import g61689.atl.model.Validator;
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
    private UserCode userCode;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Turing Machine");
        primaryStage.setMinWidth(1400);
        primaryStage.setMinHeight(800);
        modelFacade = new ModelFacade();
        modelFacade.register(this);
        MenuBar menuBar = createMenuBar(primaryStage);
        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(createUI());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
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

    private GridPane createUI() {
        uiContainer = new GridPane();

        HBox hBox = new HBox();
        showProblems();
        hBox.getChildren().addAll(problems);
        uiContainer.getChildren().addAll(hBox);
        uiContainer.setAlignment(Pos.CENTER);
        return uiContainer;
    }

    private void showProblems() {
        problems = new Problems(modelFacade);
        problems.register(this);
        problems.setAlignment(Pos.CENTER);
        problems.setPadding(new Insets(10));
    }

    @Override
    public void update(Observable observable) {
        if (observable instanceof Problems) {
            showGame();
        } else if (observable instanceof Validators) {
            testValidator();
        }
    }

    private void showGame() {
        if (problems.getChosenProblem() == null || problems.getChosenProblem().isEmpty()) {
            return;
        }
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
    }

    private void showValidators() {
        this.validators = new Validators(modelFacade);
        validators.register(this);

        uiContainer.add(validators, 0, 0, 3, 1);
    }

    private void showEnterCode() {
        this.userCode = new UserCode(modelFacade);
        userCode.register(this);

        uiContainer.add(userCode, 0, 1, 1, 1);
    }

    private void testValidator() {
        int chosenValidator = validators.getChosenValidator();
        System.out.println(chosenValidator);
        if (modelFacade.canApplyValidator()) {
            System.out.println("can apply");
            if (modelFacade.isUserCodeSet()) {
                modelFacade.chooseValidator(chosenValidator);
                if (modelFacade.getValidatorState(chosenValidator)) {
                    validators.setResult(chosenValidator, "You passed!");
                } else {
                    validators.setResult(chosenValidator, "You didn't pass!");
                }
            }
        } else {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Only 3 validators can be tested per round.");
            Font font = new Font("Arial", 12);
            String style = "-fx-font-family: '" + font.getFamily() + "'; -fx-font-size: " + font.getSize() + "pt;";
            alert.getDialogPane().setStyle(style);
            alert.showAndWait();
        }
    }

    private void showScore() {
        if (validators.getValidators() == null) {
            return;
        }
        uiContainer.getChildren().clear();

        State state = new State(modelFacade);
        uiContainer.getChildren().add(state);
    }
}
