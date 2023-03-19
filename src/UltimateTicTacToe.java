import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UltimateTicTacToe extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(new UTTR(stage)));
        stage.show();
    }
}