package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GameData {
    private int[] best = new int[10];
    private ObservableList<Gamer> statistic = FXCollections.observableArrayList();

    public int getBest(int size) {
        return best[size];
    }

    public void setBest(int size, int best) {
        this.best[size] = best;
    }

    public ObservableList<Gamer> getStatistic() {
        return statistic;
    }

    public void addGamer(String name, int size, int score) {
        statistic.add(new Gamer(name, size, score));
    }
}

