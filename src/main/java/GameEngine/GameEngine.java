package GameEngine;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public abstract class GameEngine extends Application {

    private Pane pane;
    private List<Entity> entities = new ArrayList<>();

    public void onStart(){}
    public void onUpdate(){}

    void calculateFrame()
    {
        // Update objects
        for(Entity e: entities)
        {
            e.update();
        }

        onUpdate();

        // Draw objects at resulting locations
        for(Entity e: entities){
            e.draw();
        }
    }

    /**
     * Launches the Java FX window
     * @param primaryStage stage to launch the visuals in
     * @throws Exception unused
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        pane = new Pane();

        Scene scene = new Scene(pane, getWindowWidth(), getWindowHeight());

        onStart();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    abstract protected int getWindowWidth();
    abstract protected int getWindowHeight();
}
