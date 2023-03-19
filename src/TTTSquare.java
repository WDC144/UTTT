import javafx.scene.control.Button;

public class TTTSquare {
    private Button button = new Button();
    private int SQUARE_LENGTH = 70;

    TTTSquare(UTTR game, TTTBoard board, Position position) {
        button.setMinSize(SQUARE_LENGTH, SQUARE_LENGTH);
        button.setOnAction(e -> {
            if (button.getText().isEmpty()) {
                button.setText(game.getCurrentPlayer().toString());
                button.setStyle(game.getCurrentPlayer().getStyle());
                board.evaluateState();
                game.endTurn();
                game.board().disable();
                game.board().enable(position);
            }
        });
    }

    public Button button() {
        return button;
    }

    public boolean equivalentTo(TTTSquare target) {
        return !button.getText().isEmpty() && button.getText().equals(target.button().getText());
    }

    public void reset() {
        button.setText("");
        button.setStyle("");
        button.setDisable(false);
    }
}
