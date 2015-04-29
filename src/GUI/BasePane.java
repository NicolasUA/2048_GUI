package GUI;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class BasePane extends Pane{
    private Field gameField;

    public BasePane() {
        setPrefSize(550, 350);
        setStyle("-fx-background-color: #C0C0C0;");

        final Label scoreTitle = new Label("Current score:");
        scoreTitle.setLayoutX(375);
        scoreTitle.setLayoutY(25);
        getChildren().add(scoreTitle);

        final Label score = new Label("0");
        score.setLayoutX(455);
        score.setLayoutY(25);
        getChildren().add(score);

        Button tile = new Button("New Game");
        tile.setPrefSize(100, 30);
        tile.setLayoutX(400);
        tile.setLayoutY(50);
        tile.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                getFieldSize(score);
            }
        });
        getChildren().add(tile);

    }

    private void createGameField(int size, Label score) {
        this.getChildren().remove(gameField);
        this.gameField = new Field(size, 50, score);
        this.gameField.addTile();
        this.gameField.setLayoutX(175 - (size * 50) / 2);
        this.gameField.setLayoutY(175 - (size * 50) / 2);
        this.getChildren().add(gameField);
    }

    private void getFieldSize(final Label score) {
        this.getChildren().remove(gameField);

        final Pane choosePane = new Pane();
        choosePane.setPrefSize(300, 300);
        choosePane.setLayoutX(25);
        choosePane.setLayoutY(25);
        this.getChildren().add(choosePane);

        Label text = new Label("Choose field size:");
        text.setLayoutX(110);
        text.setLayoutY(50);
        choosePane.getChildren().add(text);

        Button size4 = new Button("Size\n4x4");
        size4.setPrefSize(75, 50);
        size4.setLayoutX(25);
        size4.setLayoutY(100);
        choosePane.getChildren().add(size4);

        Button size5 = new Button("Size\n5x5");
        size5.setPrefSize(75, 50);
        size5.setLayoutX(125);
        size5.setLayoutY(100);
        choosePane.getChildren().add(size5);

        Button size6 = new Button("Size\n6x6");
        size6.setPrefSize(75, 50);
        size6.setLayoutX(225);
        size6.setLayoutY(100);
        choosePane.getChildren().add(size6);

        Button cancel = new Button("Cancel");
        cancel.setPrefSize(75, 30);
        cancel.setLayoutX(125);
        cancel.setLayoutY(200);
        choosePane.getChildren().add(cancel);

        size4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                BasePane.this.getChildren().remove(choosePane);
                score.setText("0");
                createGameField(4, score);
            }
        });

        size5.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                BasePane.this.getChildren().remove(choosePane);
                score.setText("0");
                createGameField(5, score);
            }
        });

        size6.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                BasePane.this.getChildren().remove(choosePane);
                score.setText("0");
                createGameField(6, score);
            }
        });

        cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                BasePane.this.getChildren().remove(choosePane);
                BasePane.this.getChildren().add(gameField);
            }
        });

    }
}