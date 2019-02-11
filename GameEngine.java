package gameEngine;

import gameEngine.userInput.UserInputHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public abstract class GameEngine extends Application {

    private Pane pane;
    private List<Entity> entities = new ArrayList<>();
    protected Settings settings = new Settings();
    protected GameTime time;
    protected UserInputHandler userInputHandler;

    protected void onStart(){}
    protected void onUpdateStart(){}
    protected void onUpdateFinish(){}

    public void addEntity(Entity entity)
    {
        pane.getChildren().add(entity.getVisuals());
        entities.add(entity);
    }

    public void removeEntity(Entity entity)
    {
        pane.getChildren().remove(entity.getVisuals());
        entities.remove(entity);
    }

    void calculateFrame()
    {
        onUpdateStart();

        // Update and draw objects
        for(Entity e: entities)
        {
            e.update();
            e.draw();
        }

        onUpdateFinish();
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
        userInputHandler = new UserInputHandler(scene);

        onStart();

        primaryStage.setScene(scene);
        primaryStage.show();

        time = new GameTime(this);
        time.play();
    }

    abstract public int getWindowWidth();
    abstract public int getWindowHeight();
}
