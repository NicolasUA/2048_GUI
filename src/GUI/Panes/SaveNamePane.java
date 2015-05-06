package GUI.Panes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class SaveNamePane extends Pane {
    private BasePane basePane;
    private Pane lastPane;

    public SaveNamePane(final BasePane basePane, final Pane lastPane) {
        this.basePane = basePane;
        this.lastPane = lastPane;

        setPrefSize(300, 300);
        setLayoutX(25);
        setLayoutY(25);

        Label newHighScore = new Label("Saving game");
        newHighScore.setLayoutX(100);
        newHighScore.setLayoutY(25);
        newHighScore.setPrefWidth(100);
        newHighScore.setAlignment(Pos.CENTER);
        getChildren().add(newHighScore);

        Label enterName = new Label("Enter your name:");
        enterName.setLayoutX(100);
        enterName.setLayoutY(50);
        enterName.setPrefWidth(100);
        enterName.setAlignment(Pos.CENTER);
        getChildren().add(enterName);

        final TextField name = new TextField("<Anonymous>");
        name.setPrefSize(200, 30);
        name.setLayoutX(50);
        name.setLayoutY(100);
        name.setAlignment(Pos.CENTER);
        getChildren().add(name);

        Button okButton = new Button("OK");
        okButton.setPrefSize(75, 30);
        okButton.setLayoutX(115);
        okButton.setLayoutY(200);
        getChildren().add(okButton);

        EventHandler<ActionEvent> okAction = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                basePane.getGameData().addSaveGame((GamePane) lastPane, name.getText());
                basePane.restorePane(lastPane);
            }
        };
        name.setOnAction(okAction);
        okButton.setOnAction(okAction);
    }

    public void activate() {
        basePane.clearPane();
        basePane.getChildren().add(this);
    }
}
