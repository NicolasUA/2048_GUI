package GUI;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class Movement {
    private static Timeline timeline = new Timeline();

    public static void moveRight() {
        timeline.getKeyFrames().clear();
        for (Button tile : Main.tiles) {
            if (tile != null) {
                tile.setLayoutX(tile.getLayoutX() + tile.translateXProperty().getValue());
                tile.translateXProperty().setValue(0);
                KeyValue kv1 = new KeyValue(tile.translateXProperty(), 50);
                KeyFrame kf1 = new KeyFrame(Duration.millis(500), kv1);
                timeline.getKeyFrames().add(kf1);
            }
        }
        timeline.setCycleCount(1);
        timeline.playFromStart();
    }

    public static void moveLeft() {
        timeline.getKeyFrames().clear();
        for (Button tile : Main.tiles) {
            if (tile != null) {
                tile.setLayoutX(tile.getLayoutX() + tile.translateXProperty().getValue());
                tile.translateXProperty().setValue(0);
                KeyValue kv1 = new KeyValue(tile.translateXProperty(), -50);
                KeyFrame kf1 = new KeyFrame(Duration.millis(500), kv1);
                timeline.getKeyFrames().add(kf1);
            }
        }
        timeline.setCycleCount(1);
        timeline.playFromStart();
    }
}
