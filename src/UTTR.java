import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UTTR extends BorderPane {
    private StringProperty xPlayer = new SimpleStringProperty("X player");
    private StringProperty oPlayer = new SimpleStringProperty("O player");
    private IntegerProperty xScore = new SimpleIntegerProperty(0);
    private IntegerProperty oScore = new SimpleIntegerProperty(0);
    private IntegerProperty tieScore = new SimpleIntegerProperty(0);
    private boolean scoreDisplayed;
    private Player currentPlayer = Player.X;
    private UTTB board;

   UTTR(Stage stage) {
        board = new UTTB(this);
        setCenter(board);
    }

    public void endPrompt(String message) {
        board.disable();

        Stage stage = new Stage();
        Label label = new Label(message);
        label.setStyle("-fx-font-weight: bold;");

        int BUTTON_WIDTH = 80;

        Button reset = new Button("New Round");
        reset.setMinWidth(BUTTON_WIDTH);
        reset.setOnAction(e -> {
            stage.close();
            newRound();
        });
        reset.setDefaultButton(true);

        Button quit = new Button("Quit");
        quit.setMinWidth(BUTTON_WIDTH);
        quit.setOnAction(e -> Platform.exit());

        HBox gameLayout = new HBox(5);
        gameLayout.getChildren().addAll(reset, quit);
        gameLayout.setAlignment(Pos.CENTER);

        VBox layout = new VBox(5);
        layout.getChildren().addAll(label, gameLayout);
        layout.setAlignment(Pos.CENTER);

        stage.setScene(new Scene(layout, 175 + new Text(message).getLayoutBounds().getWidth(), 75));
        stage.sizeToScene();
        stage.setTitle("Game");
        stage.show();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    private void activateMnemonics(MenuItem... items) {
        for (MenuItem item : items) {
            item.setMnemonicParsing(true);
        }
    }

    private void newGame() {
        board.boardCounter = 0;
        currentPlayer = Player.X;
        board.reset();
    }

    private void newRound() {
        board.boardCounter = 0;
        board.reset();
    }

    public String checkWinner(String winner) {
        if (winner.equals("X")) {
            xScore.setValue(xScore.getValue() + 1);
            return xPlayer.getValue();
        } else {
            oScore.setValue(oScore.getValue() + 1);
            return oPlayer.getValue();
        }
    }
    public void endTurn() {
        currentPlayer = currentPlayer == Player.X ? Player.O : Player.X;
    }

    public void evaluateBoard() {
        board.evaluateState();
    }

    public UTTB board() {
        return board;
    }
}
