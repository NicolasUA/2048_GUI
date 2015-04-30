package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.Iterator;

public class BasePane extends Pane {
    private Label bestScore;
    private Label score;
    private Button newGameButton;
    private Button saveLoadButton;
    private Button statisticButton;

    public BasePane() {
        setPrefSize(550, 350);
        setStyle("-fx-background-color: #C0C0C0;");

        Label bestScoreTitle = new Label("Best score:");
        bestScoreTitle.setLayoutX(400);
        bestScoreTitle.setLayoutY(25);
        getChildren().add(bestScoreTitle);

        bestScore = new Label("0");
        bestScore.setLayoutX(480);
        bestScore.setLayoutY(25);
        getChildren().add(bestScore);

        Label scoreTitle = new Label("Current score:");
        scoreTitle.setLayoutX(400);
        scoreTitle.setLayoutY(45);
        getChildren().add(scoreTitle);

        score = new Label("0");
        score.setLayoutX(480);
        score.setLayoutY(45);
        getChildren().add(score);

        newGameButton = new Button("New Game");
        newGameButton.setPrefSize(100, 30);
        newGameButton.setLayoutX(400);
        newGameButton.setLayoutY(75);
        newGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                new SelectSizePane(BasePane.this).activate();
            }
        });
        getChildren().add(newGameButton);
        
        saveLoadButton = new Button("Save / Load");
        saveLoadButton.setPrefSize(100, 30);
        saveLoadButton.setLayoutX(400);
        saveLoadButton.setLayoutY(125);
        saveLoadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
        getChildren().add(saveLoadButton);
        
        statisticButton = new Button("Statistic");
        statisticButton.setPrefSize(100, 30);
        statisticButton.setLayoutX(400);
        statisticButton.setLayoutY(175);
        statisticButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
        getChildren().add(statisticButton);

        getChildren().add(new GamePane(4, this));
    }

    public Pane clearPane() {
        Pane pane = null;
        for (Iterator<Node> iterator = this.getChildren().iterator(); iterator.hasNext(); ) {
            Node node = iterator.next();
            if (node instanceof Button) {
                node.setDisable(true);
            } else if (node instanceof Pane) {
                pane = (Pane) node;
                iterator.remove();
            }
        }
        return pane;
    }

    public void restorePane(Node pane) {
        for (Iterator<Node> iterator = this.getChildren().iterator(); iterator.hasNext(); ) {
            Node node = iterator.next();
            if (node instanceof Button) {
                node.setDisable(false);
            } else if (node instanceof Pane) {
                iterator.remove();
            }
        }
        if (pane != null) {
            this.getChildren().add(pane);
        }
    }

    public int getScore() {
        return Integer.parseInt(score.getText());
    }

    public void setScore(int score) {
        this.score.setText("" + score);
    }

    public int getBestScore() {
        return Integer.parseInt(bestScore.getText());
    }

    public void setBestScore(int bestScore) {
        this.bestScore.setText("" + bestScore);
    }

}