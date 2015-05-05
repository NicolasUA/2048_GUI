package GUI.Panes;

import GUI.Additional.GameData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.Iterator;

public class BasePane extends Pane {
    private Label bestScore;
    private Label score;
    private int fieldSize;
    private Button newGameButton;
    private Button saveLoadButton;
    private Button statisticButton;
    private GameData gameData;

    public BasePane() {
        setPrefSize(550, 350);
        setStyle("-fx-background-color: #C0C0C0;");

        gameData = new GameData();
        gameData.load();
        fieldSize = 4;

        Label scoreTitle = new Label("Current score:");
        scoreTitle.setLayoutX(375);
        scoreTitle.setLayoutY(25);
        scoreTitle.setPrefWidth(100);
        scoreTitle.setAlignment(Pos.CENTER_RIGHT);
        getChildren().add(scoreTitle);

        score = new Label("0");
        score.setLayoutX(480);
        score.setLayoutY(25);
        getChildren().add(score);

        Label bestScoreTitle = new Label("Best score:");
        bestScoreTitle.setLayoutX(375);
        bestScoreTitle.setLayoutY(45);
        bestScoreTitle.setPrefWidth(100);
        bestScoreTitle.setAlignment(Pos.CENTER_RIGHT);
        getChildren().add(bestScoreTitle);

        bestScore = new Label("" + gameData.getBest(fieldSize));
        bestScore.setLayoutX(480);
        bestScore.setLayoutY(45);
        getChildren().add(bestScore);

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
                new SaveLoadPane(BasePane.this).activate();
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
                new StatisticPane(BasePane.this).activate();
            }
        });
        getChildren().add(statisticButton);

        getChildren().add(new GamePane(fieldSize, this));
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
        if (pane instanceof GamePane) {
            fieldSize = ((GamePane) pane).getSize();
            setBestScore(gameData.getBest(fieldSize));
        }
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public void setFieldSize(int fieldSize) {
        this.fieldSize = fieldSize;
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

    public GameData getGameData() {
        return gameData;
    }
}