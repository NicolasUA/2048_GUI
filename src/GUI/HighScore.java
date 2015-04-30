package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class HighScore extends Pane {
    private BasePane parentPane;
    private Pane lastPane;

    public HighScore(final BasePane parentPane) {
        this.parentPane = parentPane;

        setPrefSize(300, 300);
        setLayoutX(25);
        setLayoutY(25);

        Label text = new Label("New High Score!");
        text.setLayoutX(110);
        text.setLayoutY(50);
        getChildren().add(text);

        // TODO - Enter highScore name

        Button cancel = new Button("OK");
        cancel.setPrefSize(75, 30);
        cancel.setLayoutX(125);
        cancel.setLayoutY(200);
        getChildren().add(cancel);

        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                // TODO - Save highScore + Name

                parentPane.restorePane(lastPane);
            }
        });

    }

    public void activate() {
        lastPane = parentPane.clearPane();
        parentPane.getChildren().add(this);
    }

}
