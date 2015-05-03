package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class StatisticPane extends Pane {
    private BasePane parentPane;
    private Pane lastPane;

    public StatisticPane(final BasePane parentPane) {
        this.parentPane = parentPane;

        setPrefSize(300, 300);
        setLayoutX(25);
        setLayoutY(25);

        Label text = new Label("Statistic");
        text.setLayoutX(120);
        text.setLayoutY(25);
        getChildren().add(text);

        TableView<Gamer> statistic = new TableView<>();
        statistic.setPrefSize(252, 180);
        statistic.setLayoutX(25);
        statistic.setLayoutY(50);

        TableColumn firstColumn = new TableColumn("Name");
        firstColumn.setPrefWidth(150);
        firstColumn.setCellValueFactory(new PropertyValueFactory<Gamer, String>("name"));

        TableColumn secondColumn = new TableColumn("Size");
        secondColumn.setPrefWidth(50);
        secondColumn.setCellFactory(new Callback<TableColumn<Gamer, Integer>, TableCell<Gamer, Integer>>() {
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
        secondColumn.setCellValueFactory(new PropertyValueFactory<Gamer, Integer>("size"));

        TableColumn thirdColumn = new TableColumn("Score");
        thirdColumn.setPrefWidth(50);
        thirdColumn.setCellFactory(new Callback<TableColumn<Gamer, Integer>, TableCell<Gamer, Integer>>() {
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
        thirdColumn.setCellValueFactory(new PropertyValueFactory<Gamer, Integer>("score"));
        thirdColumn.setSortType(TableColumn.SortType.DESCENDING);

        statistic.setItems(parentPane.getGameData().getStatistic());
        statistic.getColumns().addAll(firstColumn, secondColumn, thirdColumn);
        statistic.getSortOrder().add(thirdColumn);

        getChildren().add(statistic);

        Button cancel = new Button("OK");
        cancel.setPrefSize(75, 30);
        cancel.setLayoutX(125);
        cancel.setLayoutY(250);
        getChildren().add(cancel);

        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                parentPane.restorePane(lastPane);
            }
        });
    }

    public void activate() {
        lastPane = parentPane.clearPane();
        parentPane.getChildren().add(this);
    }
}
