package GUI;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    public static Button[] tiles;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("2048");

        tiles = new Button[4];

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setStyle("-fx-background-color: #C0C0C0;");

        Pane field = new Pane();
        field.setMinWidth(300);
        field.setMinHeight(300);
        field.setStyle("-fx-background-color: #FFFFFF;");
        grid.add(field, 0, 0);

        VBox menu = new VBox();
        menu.setMinWidth(150);
        menu.setMinHeight(300);
        grid.add(menu, 1, 0);

        final Button tile = new Button("Go");
        tile.setMinHeight(50);
        tile.setMinWidth(50);
        tile.setLayoutX(0);
        tile.setLayoutY(0);
        field.getChildren().add(tile);
        
        Button first = new Button("1");
        first.setMinHeight(30);
        first.setMinWidth(30);
        first.setLayoutX(0);
        first.setLayoutY(100);
        field.getChildren().add(first);
        tiles[0] = first;

        Button second = new Button("2");
        second.setMinHeight(30);
        second.setMinWidth(30);
        second.setLayoutX(0);
        second.setLayoutY(150);
        field.getChildren().add(second);
        tiles[1] = second;

        Scene scene = new Scene(grid, 500, 350);
        primaryStage.setScene(scene);
        primaryStage.show();

        final Timeline timeline = new Timeline();
        //timeline.setAutoReverse(true);
        int move = 0;

        field.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.RIGHT) Movement.moveRight();
                if (keyEvent.getCode() == KeyCode.LEFT) Movement.moveLeft();
            }
        });
        field.setFocusTraversable(true);

        tile.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Movement.moveRight();
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
