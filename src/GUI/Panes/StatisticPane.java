package GUI.Panes;

import GUI.Additional.Gamer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class StatisticPane extends Pane {
    private BasePane basePane;
    private Pane lastPane;

    public StatisticPane(final BasePane basePane) {
        this.basePane = basePane;

        setPrefSize(300, 300);
        setLayoutX(25);
        setLayoutY(25);

        Label statisticLabel = new Label("Statistic");
        statisticLabel.setLayoutX(100);
        statisticLabel.setLayoutY(25);
        statisticLabel.setPrefWidth(100);
        statisticLabel.setAlignment(Pos.CENTER);
        getChildren().add(statisticLabel);

        TableView<Gamer> statistic = new TableView<>();
        statistic.setPrefSize(265, 190);
        statistic.setLayoutX(20);
        statistic.setLayoutY(50);

        TableColumn nameColumn = new TableColumn("Name");
        nameColumn.setPrefWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<Gamer, String>("name"));

        TableColumn sizeColumn = new TableColumn("Size");
        sizeColumn.setPrefWidth(50);
        sizeColumn.setCellFactory(new Callback<TableColumn<Gamer, Integer>, TableCell<Gamer, Integer>>() {
            @Override
            public TableCell<Gamer, Integer> call(TableColumn<Gamer, Integer> gamerStringTableColumn) {
                TableCell<Gamer, Integer> cell = new TableCell<Gamer, Integer>() {
                    @Override
                    protected void updateItem(Integer integer, boolean b) {
                        if (integer != null) setText(integer.toString());
                    }
                };
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });
        sizeColumn.setCellValueFactory(new PropertyValueFactory<Gamer, Integer>("size"));

        TableColumn scoreColumn = new TableColumn("Score");
        scoreColumn.setPrefWidth(50);
        scoreColumn.setCellFactory(new Callback<TableColumn<Gamer, Integer>, TableCell<Gamer, Integer>>() {
            @Override
            public TableCell<Gamer, Integer> call(TableColumn<Gamer, Integer> gamerStringTableColumn) {
                TableCell<Gamer, Integer> cell = new TableCell<Gamer, Integer>() {
                    @Override
                    protected void updateItem(Integer integer, boolean b) {
                        if (integer != null) setText(integer.toString());
                    }
                };
                cell.setAlignment(Pos.CENTER_RIGHT);
                return cell;
            }
        });
        scoreColumn.setCellValueFactory(new PropertyValueFactory<Gamer, Integer>("score"));
        scoreColumn.setSortType(TableColumn.SortType.DESCENDING);

        statistic.setItems(basePane.getGameData().getStatistic());
        statistic.getColumns().addAll(nameColumn, sizeColumn, scoreColumn);
        statistic.getSortOrder().add(scoreColumn);

        getChildren().add(statistic);

        Button okButton = new Button("OK");
        okButton.setPrefSize(75, 30);
        okButton.setLayoutX(115);
        okButton.setLayoutY(250);
        getChildren().add(okButton);

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                basePane.restorePane(lastPane);
            }
        });
    }

    public void activate() {
        lastPane = basePane.clearPane();
        basePane.getChildren().add(this);
    }
}
