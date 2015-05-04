package GUI;

import GUI.Panes.BasePane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static final int MOVETIME = 100;
    public static final int TILESIZE = 50;
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("2048");

        BasePane basePane = new BasePane();

        Scene scene = new Scene(basePane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
