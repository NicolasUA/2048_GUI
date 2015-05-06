package GUI.Additional;

import GUI.Actions.Animation;
import GUI.Main;
import GUI.Panes.GamePane;
import javafx.scene.control.Button;
import javafx.scene.paint.Paint;

public class Tile extends Button{
    private int number;
    private int dx;
    private int dy;
    private Tile cover;
    private int tileSize;
    private int textSize;
    private GamePane gamePane;

    public Tile(int x, int y, int number, GamePane gamePane) {
        this.tileSize = gamePane.getTileSize();
        this.textSize = 8 + (int) Math.ceil(this.tileSize / 6.0);
        this.gamePane = gamePane;
        setLayoutX((x - 1) * tileSize + tileSize * 0.5 - 10);
        setLayoutY((y - 1) * tileSize + tileSize * 0.5 - 10);
        setPrefSize(20, 20);
        Animation.animateAddTile(this, number);
    }

    public void setNumber(int number) {
        this.number = number;
        String style = "-fx-padding: 1,1,1,1;";
        String[] colors = new String[] {"",
                "RED",
                "ORANGE",
                "YELLOW",
                "LIGHTGREEN",
                "CYAN",
                "BLUE",
                "VIOLET",
                "MAGENTA",
                "WHITE",
                "LIGHTGRAY",
                "GRAY"
        };
        int i = 31 - Integer.numberOfLeadingZeros(number);
        if (i < 12) {
            style += "-fx-color: " + colors[i] + ";";
        } else {
            style += "-fx-color: DARKGRAY;";
        }
        int j = this.textSize - (int) Math.log10(number);
        style += "-fx-font-size: " + j + "pt;";
        setStyle(style);
        setText("" + number);
    }

    public int getNumber() {
        return number;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getX() {
        if (translateXProperty().getValue() != 0) {
            setLayoutX(getLayoutX() + translateXProperty().getValue());
            translateXProperty().setValue(0);
            this.dx = 0;
        }
        return (int) (getLayoutX() / tileSize + 1);
    }

    public void setX(int x) {
        setLayoutX((x - 1) * tileSize);
    }

    public int getY() {
        if (translateYProperty().getValue() != 0) {
            setLayoutY(getLayoutY() + translateYProperty().getValue());
            translateYProperty().setValue(0);
            this.dy = 0;
        }
        return (int) (getLayoutY() / tileSize + 1);
    }

    public void setY(int y) {
        setLayoutY((y - 1) * tileSize);
    }

    public Tile getCover() {
        return cover;
    }

    public void setCover(Tile cover) {
        this.cover = cover;
    }

    public GamePane getGamePane() {
        return gamePane;
    }

    public int getTileSize() {
        return tileSize;
    }
}

