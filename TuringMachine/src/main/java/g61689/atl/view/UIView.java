package g61689.atl.view;

import g61689.atl.model.ModelFacade;
import g61689.atl.model.Validator;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.Observable;
import util.Observer;

public class UIView extends Application implements Observer {
    private VBox uiContainer;
    private Problems problems;
    private ModelFacade modelFacade;
    private Validators validators;
    private State state;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Turing Machine");
        primaryStage.setMinWidth(900);
        primaryStage.setMinHeight(600);
        modelFacade = new ModelFacade();
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

    private VBox createUI() {
        uiContainer = new VBox();
        HBox hBox = new HBox();
        showProblems();
        hBox.getChildren().addAll(problems);
        uiContainer.getChildren().addAll(hBox);
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
        if (observable instanceof Problems problems) {
            showValidators();
        } else if (observable instanceof Validators validators) {
            showScore();
        }
    }

    private void showValidators() {
        if (problems.getChosenProblem() == null || problems.getChosenProblem().isEmpty()) {
            return;
        }
        modelFacade.startGame(problems.getChosenProblemIndex());

        uiContainer.getChildren().clear(); // new page

        this.validators = new Validators(modelFacade);
        validators.register(this);

        uiContainer.getChildren().add(validators);
    }

    private void showScore() {
        if (validators.getValidators() == null) {
            return;
        }
        uiContainer.getChildren().clear();

        this.state = new State(modelFacade);
        uiContainer.getChildren().add(state);
    }
}
