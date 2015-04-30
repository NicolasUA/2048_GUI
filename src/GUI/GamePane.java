package GUI;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.LinkedList;
import java.util.List;

public class GamePane extends Pane{
    private List<Tile> tiles = new LinkedList<>();
    private int size;
    private BasePane parentPane;

    public GamePane(int size, BasePane parentPane) {
        this.size = size;
        this.parentPane = parentPane;

        setPrefSize(size * Main.TILESIZE, size * Main.TILESIZE);
        setLayoutX(175 - (size * Main.TILESIZE) / 2);
        setLayoutY(175 - (size * Main.TILESIZE) / 2);

        setStyle("-fx-background-color: #FFFFFF;");
        addTile();

        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (Movement.isFinished()) {
                    if (keyEvent.getCode() == KeyCode.RIGHT) Movement.moveRight(GamePane.this);
                    if (keyEvent.getCode() == KeyCode.LEFT) Movement.moveLeft(GamePane.this);
                    if (keyEvent.getCode() == KeyCode.DOWN) Movement.moveDown(GamePane.this);
                    if (keyEvent.getCode() == KeyCode.UP) Movement.moveUp(GamePane.this);
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
                Tile newTile = new Tile(x, y, number);
                this.getChildren().add(newTile);
                tiles.add(newTile);
                break;
            }
        }
        if (!canMove()) {
            new GameOverPane(parentPane).activate();
        }
    }

    public void removeTile(Tile tile) {
        this.getChildren().remove(tile);
        tiles.remove(tile);
    }

    private boolean canMove() {
        //if (tiles.size() > 5) return false;
        if (tiles.size() < size * size) return true;
        boolean check = false;
        for (Tile tile : tiles) {
            for (Tile nextTile : tiles) {
                if (Math.abs(tile.getX() - nextTile.getX() + tile.getY() - nextTile.getY()) == 1) {
                    check = true;
                }
            }
        }
        return check;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public int getSize() {
        return size;
    }

    public BasePane getParentPane() {
        return parentPane;
    }
}
