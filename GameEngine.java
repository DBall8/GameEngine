package gameEngine;

import gameEngine.userInput.KeyBinding;
import gameEngine.userInput.UserInputHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public abstract class GameEngine extends Application {

    private final static int DEFAULT_WIDTH = 800;
    private final static int DEFAULT_HEIGHT = 800;

    private Pane pane;
    private List<Entity> entities = new ArrayList<>();
    private Settings settings = new Settings();
    private GameTime time;
    protected UserInputHandler userInputHandler = null;

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
        onInitialize();

        pane = new Pane();

        Scene scene = new Scene(pane, settings.getWindowWidth(), settings.getWindowHeight());
        userInputHandler = new UserInputHandler(scene);

        onStart();

        primaryStage.setScene(scene);
        primaryStage.show();

        time = new GameTime(this);
        time.play();
    }

    protected void onInitialize(){}
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

    public void play()
    {
        time.play();
    }

    public void pause()
    {
        time.pause();
    }

    public void setWindowWidth(int windowWidth)
    {
        settings.setWindowWidth(windowWidth);
    }

    public void setWindowHeight(int windowHeight)
    {
        settings.setWindowHeight(windowHeight);
    }

    public void setFramesPerSecond(float framesPerSecond)
    {
        settings.setFramesPerSecond(framesPerSecond);
    }

    public float getFramesPerSecond()
    {
        return settings.getFramesPerSecond();
    }
}
