package GUI;

import javafx.scene.control.Button;
import javafx.scene.paint.Paint;

public class Tile extends Button{
    private int number;
    private int dx;
    private int dy;
    private int x;
    private int y;
    private Tile covered;

    public Tile(int x, int y, int number) {
        setX(x);
        setY(y);
        setMinHeight(50);
        setMinWidth(50);
        setNumber(number);
    }

    public void setNumber(int number) {
        this.number = number;
        switch (number) {
            case 2:
                setTextFill(Paint.valueOf("RED"));
                break;
            case 4:
                setTextFill(Paint.valueOf("ORANGE"));
                break;
            case 8:
                setTextFill(Paint.valueOf("YELLOW"));
                break;
            case 16:
                setTextFill(Paint.valueOf("LIGHTGREEN"));
                break;
            case 32:
                setTextFill(Paint.valueOf("CYAN"));
                break;
            case 64:
                setTextFill(Paint.valueOf("BLUE"));
                break;
            case 128:
                setTextFill(Paint.valueOf("VIOLET"));
                break;
            case 256:
                setTextFill(Paint.valueOf("MAGENTA"));
                break;
            case 512:
                setTextFill(Paint.valueOf("WHITE"));
                break;
            case 1024:
                setTextFill(Paint.valueOf("GREY"));
                break;
            case 2048:
                setTextFill(Paint.valueOf("BLACK"));
                break;
        }
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
        return x;
    }

    public void setX(int x) {
        setLayoutX((x - 1) * 50);
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        setLayoutY((y - 1) * 50);
        this.y = y;
    }

    public Tile getCovered() {
        return covered;
    }

    public void setCovered(Tile covered) {
        this.covered = covered;
    }

    public void normalizeCoordinates() {
        setLayoutX(getLayoutX() + translateXProperty().getValue());
        translateXProperty().setValue(0);
        setLayoutY(getLayoutY() + translateYProperty().getValue());
        translateYProperty().setValue(0);
    }
}

