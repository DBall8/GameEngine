# Game Engine (Name Needed)

This module allows for the quick easy creation of a simple game using Java FX.

## Table of Contents
1. [Overview and Launching](#1-Overview-and-Launching)\
  1a. [Launching Method #1](#1a-Launching-Method-1)\
  1b. [Launching Method #2](#1b-Launching-Method-2)
2. [Included Classes](#2-Included-Classes)\
  2a. [Entity](#2a-Entity)\
  2b. [GameEngine](#2b-GameEngine)\
  2c. [UserInputHandler](#2c-UserInputHandler)\
  2d. [KeyBinding](#2d-KeyBinding)\
  2e. [MouseBinding](#2e-MouseBinding)\
  2f. [Ability](#2f-Ability)

## 1. Overview and Launching ##
The basic concept of the GameEngine is that you will be creating a class that extends GameEngine, and then you will
override a couple methods to give yourself control over the game.

Example:
```
import gameEngine.GameEngine;
public class YourGame extends GameEngine {

    @Override
    protected void onInitialize()
    {
        // Code here is called before anything is initialized
    }
    
    @Override
    protected void onStart()
    {
        // Code here is called before the window is displayed.
    }
    
    @Override
    protected void onUpdateStart()
    {
        // Code here is called on every frame of the game, before entities are moved and drawn
    }
    
    @Override
    protected void onUpdateStart()
    {
        // Code here is called on every frame of the game, after entities are moved and drawn
    }
}
```
There are two basic ways to launch an instance of your class that extends GameEngine.

### 1a. Launching Method #1 ###
 1. Create a class that extends from GameEngine
 2. In your main method, call `Application.launch(YourClass.class)`
 
 Example:
 ```
 public static void main(String[] args) {
    Application.launch(YourClass.class);
 }
 ```

 ## 1b. Launching Method #2 ##
 1. Create a class that extends from GameEngine
 2. Set your project to use your new class as the Main class (GameEngine inherits from
  Application which can be launched)

## 2. Included Classes ##
The GameEngine provides several useful classes for creating and running your game

### 2a. Entity ###
This class is for creating basic objects that will be drawn on the screen (2 dimensions)\
The class has 3 fields:
```
protected float x;
protected float y;
protected float orientation;
```
Variables x and y are the coordinates of the center of the entity, and orientation is the angle in Degrees.\
\
The class also has the following methods:
```
public abstract void update();
protected void addVisual(Shape visual);
public void draw();
```
The update() method is to overridden, and is called every frame and is where you would put code to change the Entity each frame.\
The addVisual(Shape visual) method is used to add visuals to the entity. Each shape you add will be added to
a JavaFX Group that is drawn at the Entity's coordinates.
The draw() method contains code that simply draws the Entity's visuals. By default it draws the visuals at position
(x,y) and angle orientation, but it can be overridden for custom control.

### 2b. GameEngine ###
This class is the core class for creating your game. It is an abstract class with all the code
for running the game. By default, the GameEngine simply goes through every Entity that has been added
and calls it's update() method followed by its draw() method, which creates a "frame" and then draws
the frame on screen.\
\
To control and customize the GameEngine, several methods have been provided.

#### Useful Methods ####
```
public void addEntity(Entity entity)
public void removeEntity(Entity entity)
public void pause()
public void play()
public void setWindowWidth(int windowWidth)
public void setWindowHeight(int windowHeight)
public void setFramesPerSecond(float framesPerSecond)
public float getFramesPerSecond()
```

1. addEntity(Entity entity) -- This method adds an instance of Entity to the GameEngine so that it can start
    handling updating and drawing of the Entity.
2. removeEntity(Entity entity) -- Use this method to remove and Entity from the GameEngine (it will no longer be
    drawn or updated)
3. pause() -- This method pauses the GameEngine: all updating and drawing will stop.
4. play() -- This method resumes playing the GameEngine if it is paused: updating and drawing will commence.
5. setWindowWidth(int windowWidth) -- Use this to set the width of the window. (NOTE: must be called in onInitialize)
6. setWindowHeight(int windowHeight) -- Use this to set the height of the window. (NOTE: must be called in onInitialize)
7. setFramesPerSecond(float framesPerSecond) -- Sets the number of frames drawn per second. (NOTE: must be called in onInitialize)
8. getFramesPerSecond() -- Returns the current number of frames being drawn per second.

#### Override Methods ####
The following methods are empty methods that can be overridden to run code at specific times. Note: none of them
have to be overridden, you may select which you would like to use.
```
protected void onInitialize();
protected void onStart();
protected void onUpdateStart();
protected void onUpdateFinish();
```
1. onInitialize() -- This method is called before anything else happens, and before the game window is created.
    If you want to change the window size or the game frame rate, do it here.
2. onStart() -- This method is called after the window has been created and all objects are initialized.
3. onUpdateStart() -- This method is called at the start of the generation of each
    frame, before every Entity is updated.
4. onUpdateFinish() -- This method is called at the end of the generation of each frame.

#### Useful member variables ####
To allow for easy control of various aspects of the game, some instances of classes have been created for easy use.
These member variables are instantiated after initialization, so can be used anywhere except in onInitialize().
```
protected UserInputHandler userInputHandler;
```

## 2c. UserInputHandler ##
This is a class for creating bindings for user inputs. This class has 3 main methods to use:
```
public KeyBinding createKeyBinding(KeyCode key);
public MouseBinding createMouseClickBinding();
public MouseBinding createMouseListener();
```

1. createKeyBinding(KeyCode key) -- This method takes a key and returns and instance of the KeyBinding class that can be
    used to check if that key is current pressed. (See below for details on the KeyBinding class)
2. createMouseClickBinding() -- Similar to createKeyBinging, this method returns an instance of the MouseBinding class
    that can be used to see if the mouse is current being pressed. (See below for details on the MouseBinding class)
3. createMouseListener() -- This method returns an instance of the MouseBinding class that can be used to detect a mouse
    click, as well as detect mouse movement. (See below for details on the MouseBinding class)
    
## 2d. KeyBinding ##
The KeyBinding class can only be created through the use of `KeyBinding userInputHandler.createKeyBinding(KeyCode key)`.
### Useful Methods ###
```
public boolean isPressed();
public KeyCode getKey();
```

1. isPressed() -- Returns true if the key corresponding to this KeyBinding is currently being pressed.
2. getKey() -- Returns the key code for the key that the KeyBinding watches.

## 2e. MouseBinding ##
The KeyBinding class can only be created through the use of `MouseBinding userInputHandler.createMouseClickBinding()` or
`MouseBinding userInputHandler.createMouseListener()`.

### Useful Methods ###
```
public boolean isPressed();
public boolean isClicked();
public void consumeClick();
public float getMouseX();
public float getMouseY();
```

1. isPressed() -- Returns true if the left mouse button is currently pressed.
2. isClicked() -- Returns true if the mouse button was clicked. Will return true from the moment the mouse is pressed 
    up until consumeClick() is called.
3. consumeClick() -- Clears a mouse click so that the MouseBinding won't continuously think the mouse is being clicked.
4. getMouseX() -- Returns the mouse's x coordinate.
5. getMouseY() -- Returns the mouse's y coordinate.

NOTE -- MouseBindings made from createMouseClickBinding() will only update mouse x and mouse y when clicked, but MouseBindings
    made with createMouseListener() will update the x and y coordinate whenever the mouse moves.

## 2f. Ability ##
The ability class allows you to give it a callback function that will be called whenever the ability is used, as long as
the ability is off of cooldown.

### Useful Methods ###
```
public Ability(float cooldownInSeconds, Callback callback)
public void use();
public boolean isOnCooldown();
```

1. Constructor -- An ability is constructed from a cooldown time in seconds, and a Callback object. The callback object
    functions as simply a object with a single method "run" that must be overridden with the code that you would like to
    run when the ability is used. The cooldown time is the time (in seconds) that must be waited after a successful use 
    of the ability before the ability may be used again.
2. use() -- This method attempts to use the ability. The ability will be used only if the cooldown period since the last
    use has passed. If the ability is successfully used, it will call the callback function given in the constructor.
3. isOnCooldown() -- Returns true if the cooldown period since the last ability use has not passed.

### Example of construction ###
The following is an example of how to create an ability with a cooldown of 2 seconds that will simply print a message
when used.
```
Ability exampleAbility = new Ability(2, new Callback<Void>() {
    @Override
    public void run(Void parameter) {
        // Code run on ability usage here
        System.out.println("USING ABILITY");
    }
});
```