package GUI.Panes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class GameOverPane extends Pane {
    private BasePane basePane;
    private Pane lastPane;

    public GameOverPane(final BasePane basePane) {
        this.basePane = basePane;

        setPrefSize(300, 300);
        setLayoutX(25);
        setLayoutY(25);

        Label gameOver = new Label("Game Over!");
        gameOver.setLayoutX(100);
        gameOver.setLayoutY(50);
        gameOver.setPrefWidth(100);
        gameOver.setAlignment(Pos.CENTER);
        getChildren().add(gameOver);

        Button okButton = new Button("OK");
        okButton.setPrefSize(75, 30);
        okButton.setLayoutX(115);
        okButton.setLayoutY(200);
        getChildren().add(okButton);

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                basePane.restorePane(lastPane);
                if (basePane.getScore() > basePane.getBestScore()) {
                    basePane.setBestScore(basePane.getScore());
                    new HighScorePane(basePane, lastPane).activate();
                }
            }
        });

    }

    public void activate() {
        lastPane = basePane.clearPane();
        basePane.getChildren().add(this);
    }
}
