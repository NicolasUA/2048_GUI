package GUI.Panes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class SelectSizePane extends Pane {
    private BasePane basePane;
    private Pane lastPane;

    public SelectSizePane(final BasePane basePane) {
        this.basePane = basePane;

        setPrefSize(300, 300);
        setLayoutX(25);
        setLayoutY(25);

        Label selectSize = new Label("Choose field size:");
        selectSize.setLayoutX(100);
        selectSize.setLayoutY(50);
        selectSize.setPrefWidth(100);
        selectSize.setAlignment(Pos.CENTER);
        getChildren().add(selectSize);

        Button size4 = new Button("4x4");
        size4.setPrefSize(40, 40);
        size4.setLayoutX(20);
        size4.setLayoutY(100);
        getChildren().add(size4);

        Button size5 = new Button("5x5");
        size5.setPrefSize(40, 40);
        size5.setLayoutX(75);
        size5.setLayoutY(100);
        getChildren().add(size5);

        Button size6 = new Button("6x6");
        size6.setPrefSize(40, 40);
        size6.setLayoutX(130);
        size6.setLayoutY(100);
        getChildren().add(size6);

        Button size7 = new Button("7x7");
        size7.setPrefSize(40, 40);
        size7.setLayoutX(185);
        size7.setLayoutY(100);
        getChildren().add(size7);

        Button size8 = new Button("8x8");
        size8.setPrefSize(40, 40);
        size8.setLayoutX(240);
        size8.setLayoutY(100);
        getChildren().add(size8);

        Button cancel = new Button("Cancel");
        cancel.setPrefSize(75, 30);
        cancel.setLayoutX(115);
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

        size7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                newGamePane(7);
            }
        });

        size8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                newGamePane(8);
            }
        });

        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                basePane.restorePane(lastPane);
            }
        });
    }

    private void newGamePane(int size) {
        if (basePane.getScore() > basePane.getBestScore()) {
            basePane.setBestScore(basePane.getScore());
            new HighScorePane(basePane, new GamePane(size, basePane)).activate();
        } else {
            basePane.restorePane(new GamePane(size, basePane));
        }
    }

    public void activate() {
        lastPane = basePane.clearPane();
        basePane.getChildren().add(this);
    }
}
