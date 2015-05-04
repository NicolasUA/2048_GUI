package GUI.Actions;

import GUI.Main;
import GUI.Panes.GameOverPane;
import GUI.Panes.GamePane;
import GUI.Additional.Tile;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class Animation {
    private static Timeline timeline = new Timeline();

    public static void animateMovement(final GamePane gamePane, boolean x) {
        clearFrames();
        boolean hasMoves = false;
        for (final Tile tile : gamePane.getTiles()) {
            int move = x ? tile.getDx() : tile.getDy();
            if (move != 0) {
                hasMoves = true;
                KeyValue kv = new KeyValue(x ? tile.translateXProperty() : tile.translateYProperty(), Main.TILESIZE * move);
                KeyFrame kf;
                if (tile.getCover() != null) {
                    kf = new KeyFrame(Duration.millis(Math.abs(Main.MOVETIME * move)), new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            tile.setNumber(tile.getNumber() * 2);
                            gamePane.getParentPane().setScore(gamePane.getParentPane().getScore() + tile.getNumber());
                            gamePane.removeTile(tile.getCover());
                            tile.setCover(null);
                        }
                    }, kv);
                } else {
                    kf = new KeyFrame(Duration.millis(Math.abs(Main.MOVETIME * move)), kv);
                }
                timeline.getKeyFrames().add(kf);
            }
        }
        if (hasMoves) {
            timeline.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    gamePane.addTile();
                }
            });
        }
        playAnimation();
    }

    public static void animateAddTile(final Tile tile, final int number, final GamePane gamePane) {
        clearFrames();
        KeyValue kv1 = new KeyValue(tile.translateXProperty(), - Main.TILESIZE / 2 + 10);
        KeyValue kv2 = new KeyValue(tile.translateYProperty(), - Main.TILESIZE / 2 + 10);
        KeyValue kv3 = new KeyValue(tile.prefHeightProperty(), Main.TILESIZE - 1);
        KeyValue kv4 = new KeyValue(tile.prefWidthProperty(), Main.TILESIZE - 1);
        KeyFrame kf = new KeyFrame(Duration.millis(Main.MOVETIME), kv1, kv2, kv3, kv4);
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                tile.setNumber(number);
                tile.normalizeCoordinates();
                if (!gamePane.canMove()) {
                    new GameOverPane(gamePane.getParentPane()).activate();
                }
            }
        });
        timeline.getKeyFrames().add(kf);
        playAnimation();
    }

    private static void clearFrames() {
        timeline.getKeyFrames().clear();
    }

    private static void playAnimation() {
        timeline.setCycleCount(1);
        timeline.playFromStart();
    }

    public static boolean isFinished() {
        return timeline.getCurrentTime().toMillis() == timeline.getTotalDuration().toMillis();
    }
}
