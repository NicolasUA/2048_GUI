package GUI;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.LinkedList;
import java.util.List;

public class Field extends Pane{
    private List<Tile> tiles = new LinkedList<>();
    private int size;
    private int tileSize;
    private Label score;

    public Field(int size, int tileSize, Label score) {
        this.size = size;
        this.tileSize = tileSize;
        this.score = score;
        setPrefSize(size * tileSize, size * tileSize);
        setStyle("-fx-background-color: #FFFFFF;");

        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (Movement.isFinished()) {
                    if (keyEvent.getCode() == KeyCode.RIGHT) Movement.moveRight(Field.this);
                    if (keyEvent.getCode() == KeyCode.LEFT) Movement.moveLeft(Field.this);
                    if (keyEvent.getCode() == KeyCode.DOWN) Movement.moveDown(Field.this);
                    if (keyEvent.getCode() == KeyCode.UP) Movement.moveUp(Field.this);
                }
            }
        });

        Thread focusField = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    if (!isFocused()) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        requestFocus();
                    }
                }
            }
        });
        focusField.setDaemon(true);
        focusField.start();
    }

    public void addTile() {
        int number = Math.random() < 0.9 ? 2 : 4;
        for (Tile tile : tiles) {
            tile.normalizeCoordinates();
        }
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
                Tile newTile = new Tile(x, y, number, tileSize);
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

    private boolean checkField(Pane field) {
        boolean check = false;
        return check;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public int getSize() {
        return size;
    }

    public int getScore() {
        return Integer.parseInt(score.getText());
    }

    public void setScore(int score) {
        this.score.setText("" + score);
    }
}
