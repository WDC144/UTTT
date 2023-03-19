import javafx.scene.layout.GridPane;

public class UTTB extends GridPane {
    private int NUMBER_OF_BOARDS = 9;
    public int boardCounter = 1;
    private TTTBoard[] board = new TTTBoard[NUMBER_OF_BOARDS];
    private UTTR game;

    UTTB (UTTR game) {
        this.game = game;

        for (int i = 0; i < board.length; i++) {
            board[i] = new TTTBoard(this.game);
            add(board[i], i / 3, i % 3);
        }
        setHgap(5);
        setVgap(5);
    }

    public void evaluateState() {
        for (int horizontal = 0, vertical = 0; horizontal < NUMBER_OF_BOARDS; horizontal += 3) {
            if (checkSet(vertical, vertical + 3, vertical++ + 6) 
            ||  checkSet(horizontal, horizontal + 1, horizontal + 2)) {
                return;
            }
        }
        // Diagonal
        if(checkSet(0, 4, 8) || checkSet(2, 4, 6)) {
            return;
        }

        if (++boardCounter == NUMBER_OF_BOARDS) {
            game.endPrompt("It's a tie!");
            return;
        }
    }

    private boolean checkSet(int innerBoard1, int innerBoard2, int innerBoard3) {
        if (boardCounter >= 3) {
            if (board[innerBoard1].equivalentTo(board[innerBoard2]) 
            && board[innerBoard2].equivalentTo(board[innerBoard3])) {
                game.endPrompt(game.checkWinner(board[innerBoard1].winner().toString()) + " wins!");
                return true;
            }
        }
        return false;
    }

    public void disable() {
        for (TTTBoard b : board) {
            b.disable();
        }
    }

    public void enable(Position position) {
        for (int i = 0; i < Position.values().length; i++) {
            if (position == Position.values()[i]) {
                if (board[i].isCaptured()) {
                    enableAll();
                } else {
                    board[i].enable();
                }
                break;
            }
        }
    }

    public void enableAll() {
        for (TTTBoard b : board) {
            b.enable();
        }
    }

    public void reset() {
        for (TTTBoard b : board) {
            b.reset();
        }
    }
}