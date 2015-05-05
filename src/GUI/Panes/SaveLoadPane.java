package GUI.Panes;

import GUI.Additional.SaveGame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveLoadPane extends Pane {
    private BasePane basePane;
    private Pane lastPane;

    public SaveLoadPane(final BasePane basePane) {
        this.basePane = basePane;

        setPrefSize(300, 300);
        setLayoutX(25);
        setLayoutY(25);

        Label savedGames = new Label("Saved Games");
        savedGames.setLayoutX(100);
        savedGames.setLayoutY(25);
        savedGames.setPrefWidth(100);
        savedGames.setAlignment(Pos.CENTER);
        getChildren().add(savedGames);

        final TableView<SaveGame> games = new TableView<>();
        games.setPrefSize(265, 190);
        games.setLayoutX(20);
        games.setLayoutY(50);

        TableColumn nameColumn = new TableColumn("Name");
        nameColumn.setPrefWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<SaveGame, String>("name"));

        TableColumn sizeColumn = new TableColumn("Size");
        sizeColumn.setPrefWidth(50);
        sizeColumn.setCellFactory(new Callback<TableColumn<SaveGame, Integer>, TableCell<SaveGame, Integer>>() {
            @Override
            public TableCell<SaveGame, Integer> call(TableColumn<SaveGame, Integer> SaveGameStringTableColumn) {
                TableCell<SaveGame, Integer> cell = new TableCell<SaveGame, Integer>() {
                    @Override
                    protected void updateItem(Integer integer, boolean b) {
                        if (integer != null) setText(integer.toString());
                    }
                };
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });
        sizeColumn.setCellValueFactory(new PropertyValueFactory<SaveGame, Integer>("size"));

        TableColumn scoreColumn = new TableColumn("Score");
        scoreColumn.setPrefWidth(50);
        scoreColumn.setCellFactory(new Callback<TableColumn<SaveGame, Integer>, TableCell<SaveGame, Integer>>() {
            @Override
            public TableCell<SaveGame, Integer> call(TableColumn<SaveGame, Integer> SaveGameStringTableColumn) {
                TableCell<SaveGame, Integer> cell = new TableCell<SaveGame, Integer>() {
                    @Override
                    protected void updateItem(Integer integer, boolean b) {
                        if (integer != null) setText(integer.toString());
                    }
                };
                cell.setAlignment(Pos.CENTER_RIGHT);
                return cell;
            }
        });
        scoreColumn.setCellValueFactory(new PropertyValueFactory<SaveGame, Integer>("score"));

        TableColumn dateColumn = new TableColumn("Date");
        dateColumn.setPrefWidth(100);
        dateColumn.setCellFactory(new Callback<TableColumn<SaveGame, Date>, TableCell<SaveGame, Date>>() {
            @Override
            public TableCell<SaveGame, Date> call(TableColumn<SaveGame, Date> SaveGameStringTableColumn) {
                TableCell<SaveGame, Date> cell = new TableCell<SaveGame, Date>() {
                    @Override
                    protected void updateItem(Date date, boolean b) {
                        if (date != null) setText(new SimpleDateFormat("HH:mm:ss dd:MM:yy").format(date));
                    }
                };
                cell.setAlignment(Pos.CENTER_LEFT);
                return cell;
            }
        });
        dateColumn.setCellValueFactory(new PropertyValueFactory<SaveGame, Date>("date"));
        dateColumn.setSortType(TableColumn.SortType.DESCENDING);

        games.setItems(basePane.getGameData().getSaveGames());
        games.getColumns().addAll(nameColumn, sizeColumn, scoreColumn, dateColumn);
        games.getSortOrder().add(dateColumn);

        getChildren().add(games);

        Button saveButton = new Button("Save");
        saveButton.setPrefSize(75, 30);
        saveButton.setLayoutX(15);
        saveButton.setLayoutY(250);
        getChildren().add(saveButton);

        Button loadButton = new Button("Load");
        loadButton.setPrefSize(75, 30);
        loadButton.setLayoutX(115);
        loadButton.setLayoutY(250);
        getChildren().add(loadButton);

        Button cancelButton = new Button("Cancel");
        cancelButton.setPrefSize(75, 30);
        cancelButton.setLayoutX(215);
        cancelButton.setLayoutY(250);
        getChildren().add(cancelButton);

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (lastPane instanceof GamePane)
                    basePane.getGameData().addSaveGame((GamePane) lastPane, "Test");
            }
        });

        loadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (games.getSelectionModel().getSelectedItem() != null) {

                    // TODO Make name enter pane

                    basePane.restorePane(basePane.getGameData().loadSaveGame(games.getSelectionModel().getSelectedItem(), basePane));
                }
            }
        });

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                basePane.restorePane(lastPane);
            }
        });
    }

    public void activate() {
        this.lastPane = basePane.clearPane();
        basePane.getChildren().add(this);
    }
}
