package GUI.Actions;

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
                KeyValue kv = new KeyValue(x ? tile.translateXProperty() : tile.translateYProperty(),
                        tile.getTileSize() * move);
                KeyFrame kf;
                if (tile.getCover() != null) {
                    kf = new KeyFrame(Duration.millis(Math.abs(gamePane.getMoveTime() * move)), new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            tile.setNumber(tile.getNumber() * 2);
                            gamePane.setScore(gamePane.getScore() + tile.getNumber());
                            gamePane.removeTile(tile.getCover());
                            tile.setCover(null);
                        }
                    }, kv);
                } else {
                    kf = new KeyFrame(Duration.millis(Math.abs(gamePane.getMoveTime() * move)), kv);
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

    public static void animateAddTile(final Tile tile, final int number) {
        if (isFinished()) {
            clearFrames();
        } else {
            timeline.pause();
        }
        KeyValue kv1 = new KeyValue(tile.translateXProperty(), - tile.getTileSize() / 2 + 10);
        KeyValue kv2 = new KeyValue(tile.translateYProperty(), - tile.getTileSize() / 2 + 10);
        KeyValue kv3 = new KeyValue(tile.prefHeightProperty(), tile.getTileSize() - 1);
        KeyValue kv4 = new KeyValue(tile.prefWidthProperty(), tile.getTileSize() - 1);
        KeyFrame kf = new KeyFrame(Duration.millis(tile.getGamePane().getMoveTime()), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                tile.setNumber(number);
            }
        }, kv1, kv2, kv3, kv4);
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!tile.getGamePane().canMove()) {
                    new GameOverPane(tile.getGamePane().getBasePane()).activate();
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
