package GUI.Panes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class HighScorePane extends Pane {
    private BasePane parentPane;
    private Pane lastPane;

    public HighScorePane(final BasePane parentPane, final Pane lastPane) {
        this.parentPane = parentPane;
        this.lastPane = lastPane;

        setPrefSize(300, 300);
        setLayoutX(25);
        setLayoutY(25);

        Label text = new Label("New High Score!");
        text.setLayoutX(110);
        text.setLayoutY(25);
        getChildren().add(text);

        Label text1 = new Label("Enter your name:");
        text1.setLayoutX(110);
        text1.setLayoutY(50);
        getChildren().add(text1);

        final TextField name = new TextField("<Anonymous>");
        name.setPrefSize(200, 30);
        name.setLayoutX(60);
        name.setLayoutY(100);
        name.setAlignment(Pos.CENTER);
        getChildren().add(name);

        Button cancel = new Button("OK");
        cancel.setPrefSize(75, 30);
        cancel.setLayoutX(125);
        cancel.setLayoutY(200);
        getChildren().add(cancel);

        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                parentPane.getGameData().setBest(parentPane.getFieldSize(), parentPane.getBestScore());
                parentPane.getGameData().addGamer(name.getText(), parentPane.getFieldSize(), parentPane.getBestScore());
                parentPane.restorePane(lastPane);
            }
        });
    }

    public void activate() {
        parentPane.clearPane();
        parentPane.getChildren().add(this);
    }

}
