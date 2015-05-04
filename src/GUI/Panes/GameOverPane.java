package GUI.Panes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class GameOverPane extends Pane {
    private BasePane parentPane;
    private Pane lastPane;

    public GameOverPane(final BasePane parentPane) {
        this.parentPane = parentPane;

        setPrefSize(300, 300);
        setLayoutX(25);
        setLayoutY(25);

        Label text = new Label("Game Over!");
        text.setLayoutX(120);
        text.setLayoutY(50);
        getChildren().add(text);

        Button cancel = new Button("OK");
        cancel.setPrefSize(75, 30);
        cancel.setLayoutX(125);
        cancel.setLayoutY(200);
        getChildren().add(cancel);

        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                parentPane.restorePane(lastPane);
                if (parentPane.getScore() > parentPane.getBestScore()) {
                    parentPane.setBestScore(parentPane.getScore());
                    new HighScorePane(parentPane, lastPane).activate();
                }
            }
        });

    }

    public void activate() {
        lastPane = parentPane.clearPane();
        parentPane.getChildren().add(this);
    }
}
