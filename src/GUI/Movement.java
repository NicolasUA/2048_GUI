package GUI;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.util.Collections;
import java.util.Comparator;

public class Movement {
    private static Timeline timeline = new Timeline();

    public static void moveRight() {
        timeline.getKeyFrames().clear();
        int size = Main.size;
        Collections.sort(Main.tiles, new Comparator<Tile>() {
            @Override
            public int compare(Tile o1, Tile o2) {
                if (o1.getY() == o2.getY()) {
                    return o2.getX() - o1.getY();
                }
                return o1.getY() - o2.getY();
            }
        });
        int place = size;
        for (int i = 0; i < Main.tiles.size(); i++) {
            Tile tile = Main.tiles.get(i);
            if (place == size) {
                if (tile.getX() != size) {
                    tile.setDx(size - tile.getX());
                }
                place--;
            } else {
                Tile previuosTile = Main.tiles.get(i - 1);
                if (tile.getY() != previuosTile.getY()) {
                    if (tile.getX() != size) {
                        tile.setDx(size - tile.getX());

                    }
                    place = size - 1;
                } else {
                    if ((tile.getNumber() == previuosTile.getNumber()) && (previuosTile.getCovered() == null)) {
                        previuosTile.toBack();
                        tile.setCovered(previuosTile);
                        tile.setDx(place + 1 - tile.getX());
                    } else {
                        tile.setDx(place - tile.getX());
                        place--;
                    }
                }
            }
        }
        for (final Tile tile : Main.tiles) {
            if (tile.getDx() != 0) {
                KeyValue kv = new KeyValue(tile.translateXProperty(), 50 * tile.getDx());
                KeyFrame kf;
                if (tile.getCovered() != null) {
                    kf = new KeyFrame(Duration.millis(500 * tile.getDx()), new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            tile.setNumber(tile.getNumber() * 2);
                            Main.tiles.remove(tile.getCovered());
                            Main.field.getChildren().remove(tile.getCovered());
                            tile.setCovered(null);
                            tile.normalizeCoordinates();
                        }
                    }, kv);
                } else {
                    kf = new KeyFrame(Duration.millis(500 * tile.getDx()), kv);
                }
                timeline.getKeyFrames().add(kf);
            }
        }

        timeline.setCycleCount(1);
        timeline.playFromStart();
    }

    public static void moveLeft() {
        timeline.getKeyFrames().clear();
        for (Tile tile : Main.tiles) {
            if (tile != null) {
                tile.setNumber(tile.getNumber() * 2);
                tile.setLayoutX(tile.getLayoutX() + tile.translateXProperty().getValue());
                tile.translateXProperty().setValue(0);
                KeyValue kv1 = new KeyValue(tile.translateXProperty(), -50);
                KeyFrame kf1 = new KeyFrame(Duration.millis(100), kv1);
                timeline.getKeyFrames().add(kf1);
            }
        }
        timeline.setCycleCount(1);
        timeline.playFromStart();
    }

    public static boolean isFinished() {
        return timeline.getCurrentTime().toMillis() == timeline.getTotalDuration().toMillis();
    }
}
