package GUI;

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

import java.util.LinkedList;
import java.util.List;

public class Main extends Application {

    public static List<Tile> tiles;
    public static int size = 6;
    public static Pane field;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("2048");

        tiles = new LinkedList<Tile>();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setStyle("-fx-background-color: #C0C0C0;");

        field = new Pane();
        field.setMinWidth(300);
        field.setMinHeight(300);
        field.setStyle("-fx-background-color: #FFFFFF;");
        grid.add(field, 0, 0);

        VBox menu = new VBox();
        menu.setMinWidth(150);
        menu.setMinHeight(300);
        grid.add(menu, 1, 0);

        Button tile = new Button("Go");
        tile.setMinHeight(50);
        tile.setMinWidth(50);
        tile.setLayoutX(0);
        tile.setLayoutY(0);
        field.getChildren().add(tile);

        tiles.add(new Tile(1, 2, 2));
        tiles.add(new Tile(1, 3, 2));
        tiles.add(new Tile(3, 3, 2));

        for (Tile t : tiles) {
            field.getChildren().add(t);
        }

        Scene scene = new Scene(grid, 500, 350);
        primaryStage.setScene(scene);
        primaryStage.show();

        field.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (Movement.isFinished()) {
                    if (keyEvent.getCode() == KeyCode.RIGHT) Movement.moveRight();
                    if (keyEvent.getCode() == KeyCode.LEFT) Movement.moveLeft();
                }
            }
        });
        field.requestFocus();

        Thread focusField = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    if (!field.isFocused()) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        field.requestFocus();
                    }
                }
            }
        });
        focusField.setDaemon(true);
        focusField.start();

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
