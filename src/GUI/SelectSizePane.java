package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class SelectSizePane extends Pane {
    private BasePane parentPane;
    private Pane lastPane;

    public SelectSizePane(final BasePane parentPane) {
        this.parentPane = parentPane;

        setPrefSize(300, 300);
        setLayoutX(25);
        setLayoutY(25);

        Label text = new Label("Choose field size:");
        text.setLayoutX(110);
        text.setLayoutY(50);
        getChildren().add(text);

        Button size4 = new Button("Size\n4x4");
        size4.setPrefSize(75, 50);
        size4.setLayoutX(25);
        size4.setLayoutY(100);
        getChildren().add(size4);

        Button size5 = new Button("Size\n5x5");
        size5.setPrefSize(75, 50);
        size5.setLayoutX(125);
        size5.setLayoutY(100);
        getChildren().add(size5);

        Button size6 = new Button("Size\n6x6");
        size6.setPrefSize(75, 50);
        size6.setLayoutX(225);
        size6.setLayoutY(100);
        getChildren().add(size6);

        Button cancel = new Button("Cancel");
        cancel.setPrefSize(75, 30);
        cancel.setLayoutX(125);
        cancel.setLayoutY(200);
        getChildren().add(cancel);

        size4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                newGamePane(4);
            }
        });

        size5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                newGamePane(5);
            }
        });

        size6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                newGamePane(6);
            }
        });

        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                parentPane.restorePane(lastPane);
            }
        });
    }

    private void newGamePane(int size) {
        GamePane gamePane = new GamePane(size, parentPane);

        // TODO - Check highScore

        parentPane.setScore(0);
        parentPane.restorePane(gamePane);
    }

    public void activate() {
        lastPane = parentPane.clearPane();
        parentPane.getChildren().add(this);
    }
}
