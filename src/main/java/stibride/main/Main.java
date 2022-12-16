package stibride.main;

import javafx.application.Application;
import javafx.stage.Stage;
import stibride.model.PathFinder;
import stibride.presenter.Presenter;
import stibride.view.View;

/**
 *
 * @author
 */
public class Main extends Application {

    /**
     * Entry points to the <code> StibRide </code> application..
     *
     * @param args no arguments needed.
     */
    public static void main(String[] args) {
        launch(args);
    }

    public Main() {

    }

    @Override
    public void start(Stage stage) throws Exception {
        PathFinder model = new PathFinder();

        View view = new View(stage);

        Presenter presenter = new Presenter(model, view);

        view.showStage();
    }
}
