# Game Engine (Name Needed)

This module allows for the quick easy creation of a simple game using Java FX.

## To Launch

There are two basic ways to set up an instance of a GameEngine

### Method #1
 1. Create a class that extends from GameEngine
 2. In your main method, call `Application.launch(YourClass.class)`
 
 Example:\
 ```
 public static void main(String[] args) {
    Application.launch(YourClass.class);
 }
 ```

 ## Method #2
 1. Create a class that extends from GameEngine
 2. Set your project to use your new class as the Main class (GameEngine inherits from
  Application which can be launched)
  
  Example:\
  `public class YourClass extends GameEngine{}`

## Included Classes
The GameEngine provides several useful classes for creating and running your game

### Entity
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

### GameEngine
This class is the core class for creating your game. It is an abstract class with all the code
for running the game. By default, the GameEngine simply goes through every Entity that has been added
and calls it's update() method followed by its draw() method, which creates a "frame" and then draws
the frame on screen.\
\
To control and customize the GameEngine, several methods have been provided.

#### Override Methods 
The following methods are empty methods that can be overridden to run code at specific times. Note: none of them
have to be overridden, you may select which you would like to use.
```
protected void onInitialize();
protected void onStart();
protected void onUpdateStart();
protected void onUpdateFinish();
```
1. onInitialize() -- This method is called before anything else happens, and before the game window is created.
    If you want to change the window size or the game framerate, do it here.
2. onStart() -- This method is called after the window has been created and all objects are initialized.
3. onUpdateStart() -- This method is called at the start of the generation of each
    frame, before every Entity is updated.
4. onUpdateFinish() -- This method is called at the end of the generation of each frame.

### Time

### Settings
The GameEngine class has one singular Settings object that contains all settings for the game. They are as follows:

```
private float framesPerSecond = 120;
private int windowWidth = 800;
private int windowHeight = 800;
```
These are the default values for the class. To change each setting simply call the following methods:
```
settings.setWindowWidth(int windowWidth);
settings.setWindowHeight(int windowWidth);
settings.setWindowWidth(int windowWidth);
```
