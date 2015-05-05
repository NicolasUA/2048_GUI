package GUI.Additional;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SaveGame implements Serializable {
    private List<SimpleTile> tiles;
    private Date date;
    private int size;
    private int score;
    private String name;

    public List<SimpleTile> getTiles() {
        return tiles;
    }

    public void setTiles(List<SimpleTile> tiles) {
        this.tiles = tiles;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
