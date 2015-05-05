package GUI.Additional;

import GUI.Panes.BasePane;
import GUI.Panes.GamePane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class GameData{
    private int[] best = new int[10];
    private ObservableList<Gamer> statistic = FXCollections.observableArrayList();
    private ObservableList<SaveGame> saveGames = FXCollections.observableArrayList();

    public GameData() {
    }

    public int getBest(int size) {
        return best[size];
    }

    public ObservableList<Gamer> getStatistic() {
        return statistic;
    }

    public ObservableList<SaveGame> getSaveGames() {
        return saveGames;
    }

    public void addGamer(String name, int size, int score) {
        statistic.add(new Gamer(name, size, score));
        this.best[size] = score;
        save();
    }

    public void addSaveGame(GamePane gamePane, String name) {
        SaveGame saveGame = new SaveGame();
        saveGame.setName(name);
        saveGame.setSize(gamePane.getSize());
        saveGame.setScore(gamePane.getScore());
        saveGame.setDate(new Date());
        List<SimpleTile> list = new LinkedList<>();
        for (Tile tile : gamePane.getTiles()) {
            SimpleTile stile = new SimpleTile();
            stile.setNumber(tile.getNumber());
            stile.setX(tile.getX());
            stile.setY(tile.getY());
            list.add(stile);
        }
        saveGame.setTiles(list);
        saveGames.add(saveGame);
        save();
    }

    public GamePane loadSaveGame(SaveGame saveGame, BasePane basePane) {
        GamePane gamePane = new GamePane(saveGame.getSize(), basePane);
        gamePane.removeTile(gamePane.getTiles().get(0));
        gamePane.setScore(saveGame.getScore());
        for (SimpleTile stile : saveGame.getTiles()) {
            Tile tile = new Tile(stile.getX(), stile.getY(), stile.getNumber(), gamePane);
            gamePane.getChildren().add(tile);
            gamePane.getTiles().add(tile);
        }
        return gamePane;
    }

    public void load() {
        try (FileInputStream fis = new FileInputStream("2048.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            best = (int[]) ois.readObject();
            statistic = FXCollections.observableArrayList();
            statistic.addAll((Gamer[]) ois.readObject());
            saveGames = FXCollections.observableArrayList();
            saveGames.addAll((SaveGame[]) ois.readObject());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try (FileOutputStream fos = new FileOutputStream("2048.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(best);
            oos.writeObject(statistic.toArray(new Gamer[0]));
            oos.writeObject(saveGames.toArray(new SaveGame[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

