package g61689.atl.view;

import g61689.atl.model.ModelFacade;
import g61689.atl.model.Problem;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UIView extends Application {
    private Problems problems;
    private ModelFacade modelFacade;

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
        exitMenuItem.setOnAction(e -> primaryStage.close()); // Action to close the application
        fileMenu.getItems().add(exitMenuItem);
        menuBar.getMenus().add(fileMenu);
        return menuBar;
    }

    private VBox createUI() {
        VBox vBox = new VBox();
        HBox hBox = new HBox();
        showProblems();
        hBox.getChildren().addAll(problems);
        vBox.getChildren().addAll(hBox);
        return vBox;
    }

    private void showProblems() {
        problems = new Problems(modelFacade);
        problems.setAlignment(Pos.CENTER);
        problems.setPadding(new Insets(10));
        problems.setHgap(5);
        problems.setVgap(7);
    }
}
