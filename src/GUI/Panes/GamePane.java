package GUI.Panes;

import GUI.Actions.Animation;
import GUI.Actions.Movement;
import GUI.Additional.Tile;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.List;

public class GamePane extends Pane{
    private BasePane basePane;
    private int size;
    private int score;
    private List<Tile> tiles = new LinkedList<>();
    private int tileSize;
    private int[] tileSizes = new int[] {0, 0, 0, 0, 50, 50, 50, 42, 36};
    private int moveTime;
    private static Timeline focus;

    public GamePane(int size, BasePane basePane) {
        this.size = size;
        this.basePane = basePane;
        setScore(0);
        this.tileSize = tileSizes[size];
        this.moveTime = 100 * 4 / size;

        setPrefSize(size * tileSize, size * tileSize);
        setLayoutX(175 - (size * tileSize) / 2);
        setLayoutY(175 - (size * tileSize) / 2);

        setStyle("-fx-background-color: #FFFFFF;");
        addTile();

        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (Animation.isFinished()) {
                    if (keyEvent.getCode() == KeyCode.RIGHT) Movement.moveRight(GamePane.this);
                    if (keyEvent.getCode() == KeyCode.LEFT) Movement.moveLeft(GamePane.this);
                    if (keyEvent.getCode() == KeyCode.DOWN) Movement.moveDown(GamePane.this);
                    if (keyEvent.getCode() == KeyCode.UP) Movement.moveUp(GamePane.this);
                    keyEvent.consume();
                }
            }
        });

        focus = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!GamePane.this.isFocused()) GamePane.this.requestFocus();
            }
        }));
        focus.setCycleCount(Timeline.INDEFINITE);
        focus.play();

    }

    public void addTile() {
        int number = Math.random() < 0.9 ? 2 : 4;
        while (true) {
            int x = (int) (Math.random() * size + 1);
            int y = (int) (Math.random() * size + 1);
            boolean check = true;
            for (Tile tile : tiles) {
                if ((tile.getX() == x) && (tile.getY() == y)) {
                    check = false;
                }
            }
            if (check) {
                Tile newTile = new Tile(x, y, number, this);
                this.getChildren().add(newTile);
                tiles.add(newTile);
                break;
            }
        }
    }

    public void removeTile(Tile tile) {
        this.getChildren().remove(tile);
        tiles.remove(tile);
    }

    public boolean canMove() {
        //if (tiles.size() > 5) return false;
        if (tiles.size() < size * size) return true;
        boolean check = false;
        for (Tile tile : tiles) {
            for (Tile nextTile : tiles) {
                if ((Math.abs(tile.getX() - nextTile.getX() + tile.getY() - nextTile.getY()) == 1) &&
                        (tile.getNumber() == nextTile.getNumber())) {
                    check = true;
                }
            }
        }
        return check;
    }

    public BasePane getBasePane() {
        return basePane;
    }

    public int getSize() {
        return size;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        basePane.setScore(score);
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getMoveTime() {
        return moveTime;
    }
}
