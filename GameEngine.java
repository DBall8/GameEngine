package gameEngine;

import gameEngine.userInput.UserInputHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Abstract class for creating the core Game Engine
 */
public abstract class GameEngine extends Application {

    private Pane pane; // Pane for adding all visuals
    private Settings settings = new Settings(); // contains all settings for the engine
    private GameTime time; // controls triggering of frames updates
    protected UserInputHandler userInputHandler = null; // creates bindings between inputs and actions

    private List<Entity> entities = new ArrayList<>(); // All entities to be drawn
    private List<Entity> additionList = new LinkedList<>(); // list for entities to add on next frame
    private List<Entity> removalList = new LinkedList<>(); // list for entities to remove on next frame

    /**
     * Calculates the state of every entity and then draws a new frame
     */
    void calculateFrame()
    {
        // Do any removals or additions to the entity list
        updateEntityList();

        // Run method that the user can override
        onUpdateStart();

        // Update and draw objects
        for(Entity e: entities)
        {
            e.update();
            e.draw();
        }

        // Run method that the user can override
        onUpdateFinish();
    }


    /**
     * Launches the Java FX window
     * @param primaryStage stage to launch the visuals in
     * @throws Exception unused
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Run method that the user can override
        onInitialize();

        // Set up pane and scene
        pane = new Pane();
        Scene scene = new Scene(pane, settings.getWindowWidth(), settings.getWindowHeight());

        // Create user input handler
        userInputHandler = new UserInputHandler(scene);

        // Run method that the user can override
        onStart();

        // Set up window and show it
        primaryStage.setScene(scene);
        primaryStage.show();

        // Start game time
        time = new GameTime(this);
        time.play();
    }

    /**
     * Takes an entity and adds it to the entity list, can only be run in the calculateFrame method
     * @param entity
     */
    private void add(Entity entity)
    {
        pane.getChildren().add(entity.getVisuals());
        entities.add(entity);
    }

    /**
     * Takes an entity and removes it from the entity list, can only be run in the calculateFrame method
     * @param entity
     */
    private void remove(Entity entity)
    {
        pane.getChildren().remove(entity.getVisuals());
        entities.remove(entity);
    }

    /**
     * Runs all stored additions and removals in a synchronized call to prevent simultaneous alterations
     */
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

    /**
     * Methods for the user to override
     */
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

    /**
     * Adds an Entity to the list of drawn Entities
     * @param entity
     */
    public synchronized void addEntity(Entity entity)
    {
        additionList.add(entity);
    }

    /**
     * Removes an Entity from the list of drawn Entities
     * @param entity
     */
    public synchronized void removeEntity(Entity entity)
    {
        removalList.add(entity);
    }

    /**
     * Starts the game
     */
    public void play()
    {
        time.play();
    }

    /**
     * Pauses the game
     */
    public void pause()
    {
        time.pause();
    }

    /**
     * Sets the window width MUST BE DONE IN onInitialize() TO WORK CURRENTLY
     * @param windowWidth
     */
    public void setWindowWidth(int windowWidth)
    {
        settings.setWindowWidth(windowWidth);
    }

    /**
     * Sets the window height MUST BE DONE IN onInitialize() TO WORK CURRENTLY
     * @param windowHeight
     */
    public void setWindowHeight(int windowHeight)
    {
        settings.setWindowHeight(windowHeight);
    }

    /**
     * Sets the number of frames drawn per second MUST BE DONE IN onInitialize() TO WORK CURRENTLY
     * @param framesPerSecond
     */
    public void setFramesPerSecond(float framesPerSecond)
    {
        settings.setFramesPerSecond(framesPerSecond);
    }

    /**
     * Gets the current number of frames drawn per second
     * @return
     */
    public float getFramesPerSecond()
    {
        return settings.getFramesPerSecond();
    }
}
