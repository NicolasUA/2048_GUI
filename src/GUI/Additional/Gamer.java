package GUI.Additional;

import java.io.Serializable;

public class Gamer implements Serializable {
    String name;
    int size;
    int score;

    public Gamer() {}

    public Gamer(String name, int size, int score) {
        this.name = name;
        this.size = size;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
