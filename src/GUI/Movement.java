package GUI;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.util.*;

public class Movement {
    private static Timeline timeline = new Timeline();

    public static void moveRight(GamePane gamePane) {
        List<Tile> tiles = gamePane.getTiles();
        int size = gamePane.getSize();
        Collections.sort(tiles, new Comparator<Tile>() {
            @Override
            public int compare(Tile o1, Tile o2) {
                if (o1.getY() == o2.getY()) {
                    return o2.getX() - o1.getX();
                }
                return o1.getY() - o2.getY();
            }
        });
        int place = size;
        for (int i = 0; i < tiles.size(); i++) {
            Tile tile = tiles.get(i);
            if (place == size) {
                if (tile.getX() != size) {
                    tile.setDx(size - tile.getX());
                }
                place--;
            } else {
                Tile previuosTile = tiles.get(i - 1);
                if (tile.getY() != previuosTile.getY()) {
                    if (tile.getX() != size) {
                        tile.setDx(size - tile.getX());

                    }
                    place = size - 1;
                } else {
                    if ((tile.getNumber() == previuosTile.getNumber()) && (previuosTile.getCover() == null)) {
                        previuosTile.toBack();
                        tile.setCover(previuosTile);
                        tile.setDx(place + 1 - tile.getX());
                    } else {
                        tile.setDx(place - tile.getX());
                        place--;
                    }
                }
            }
        }
        animate(gamePane, true);
    }

    public static void moveLeft(GamePane gamePane) {
        List<Tile> tiles = gamePane.getTiles();
        Collections.sort(tiles, new Comparator<Tile>() {
            @Override
            public int compare(Tile o1, Tile o2) {
                if (o1.getY() == o2.getY()) {
                    return o1.getX() - o2.getX();
                }
                return o1.getY() - o2.getY();
            }
        });
        int place = 1;
        for (int i = 0; i < tiles.size(); i++) {
            Tile tile = tiles.get(i);
            if (place == 1) {
                if (tile.getX() != 1) {
                    tile.setDx(1 - tile.getX());
                }
                place++;
            } else {
                Tile previuosTile = tiles.get(i - 1);
                if (tile.getY() != previuosTile.getY()) {
                    if (tile.getX() != 1) {
                        tile.setDx(1 - tile.getX());

                    }
                    place = 2;
                } else {
                    if ((tile.getNumber() == previuosTile.getNumber()) && (previuosTile.getCover() == null)) {
                        previuosTile.toBack();
                        tile.setCover(previuosTile);
                        tile.setDx(place - 1 - tile.getX());
                    } else {
                        tile.setDx(place - tile.getX());
                        place++;
                    }
                }
            }
        }
        animate(gamePane, true);
    }

    public static void moveDown(GamePane gamePane) {
        List<Tile> tiles = gamePane.getTiles();
        int size = gamePane.getSize();
        Collections.sort(tiles, new Comparator<Tile>() {
            @Override
            public int compare(Tile o1, Tile o2) {
                if (o1.getX() == o2.getX()) {
                    return o2.getY() - o1.getY();
                }
                return o1.getX() - o2.getX();
            }
        });
        int place = size;
        for (int i = 0; i < tiles.size(); i++) {
            Tile tile = tiles.get(i);
            if (place == size) {
                if (tile.getY() != size) {
                    tile.setDy(size - tile.getY());
                }
                place--;
            } else {
                Tile previuosTile = tiles.get(i - 1);
                if (tile.getX() != previuosTile.getX()) {
                    if (tile.getY() != size) {
                        tile.setDy(size - tile.getY());

                    }
                    place = size - 1;
                } else {
                    if ((tile.getNumber() == previuosTile.getNumber()) && (previuosTile.getCover() == null)) {
                        previuosTile.toBack();
                        tile.setCover(previuosTile);
                        tile.setDy(place + 1 - tile.getY());
                    } else {
                        tile.setDy(place - tile.getY());
                        place--;
                    }
                }
            }
        }
        animate(gamePane, false);
    }

    public static void moveUp(GamePane gamePane) {
        List<Tile> tiles = gamePane.getTiles();
        Collections.sort(tiles, new Comparator<Tile>() {
            @Override
            public int compare(Tile o1, Tile o2) {
                if (o1.getX() == o2.getX()) {
                    return o1.getY() - o2.getY();
                }
                return o1.getX() - o2.getX();
            }
        });
        int place = 1;
        for (int i = 0; i < tiles.size(); i++) {
            Tile tile = tiles.get(i);
            if (place == 1) {
                if (tile.getY() != 1) {
                    tile.setDy(1 - tile.getY());
                }
                place++;
            } else {
                Tile previuosTile = tiles.get(i - 1);
                if (tile.getX() != previuosTile.getX()) {
                    if (tile.getY() != 1) {
                        tile.setDy(1 - tile.getY());

                    }
                    place = 2;
                } else {
                    if ((tile.getNumber() == previuosTile.getNumber()) && (previuosTile.getCover() == null)) {
                        previuosTile.toBack();
                        tile.setCover(previuosTile);
                        tile.setDy(place - 1 - tile.getY());
                    } else {
                        tile.setDy(place - tile.getY());
                        place++;
                    }
                }
            }
        }
        animate(gamePane, false);
    }

    private static void animate(final GamePane gamePane, boolean x) {
        timeline.getKeyFrames().clear();
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
            timeline.setCycleCount(1);
            timeline.playFromStart();
        }
    }

    public static boolean isFinished() {
        return timeline.getCurrentTime().toMillis() == timeline.getTotalDuration().toMillis();
    }
}
