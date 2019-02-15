package gameEngine;

import gameEngine.callback.Callback;
import gameEngine.userInput.KeyBinding;
import gameEngine.userInput.UserInputHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class GameEngine extends Application {

    private final static int DEFAULT_WIDTH = 800;
    private final static int DEFAULT_HEIGHT = 800;

    private Pane pane;
    private Settings settings = new Settings();
    private GameTime time;
    protected UserInputHandler userInputHandler = null;

    private List<Entity> entities = new ArrayList<>();
    private List<Entity> additionList = new LinkedList<>();
    private List<Entity> removalList = new LinkedList<>();

    void calculateFrame()
    {
        // Do any removals or additions to the entity list
        updateEntityList();

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

    private void add(Entity entity)
    {
        pane.getChildren().add(entity.getVisuals());
        entities.add(entity);
    }

    private void remove(Entity entity)
    {
        pane.getChildren().remove(entity.getVisuals());
        entities.remove(entity);
    }

    synchronized private void updateEntityList()
    {
        for(Entity entity: additionList)
        {
            add(entity);
        }
        additionList.clear();

        for(Entity entity: removalList)
        {
            remove(entity);
        }
        removalList.clear();
    }

    protected void onInitialize(){}
    protected void onStart(){}
    protected void onUpdateStart(){}
    protected void onUpdateFinish(){}

    // Could use if desired
//    public void forEachEntity(Callback<Entity> callback)
//    {
//        for(Entity entity: entities)
//        {
//            callback.run(entity);
//        }
//    }

    public synchronized void addEntity(Entity entity)
    {
        additionList.add(entity);
    }

    public synchronized void removeEntity(Entity entity)
    {
        removalList.add(entity);
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
