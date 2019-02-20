package gameEngine.userInput;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that is exposed to the user for use in creating bindings from keys
 * NOTE: User can create bindings on their own if desired but the purpose of this class is to make a nicely organized
 * way of viewing key bindings
 */
public class UserInputHandler {
    Scene scene; // JavaFX scene the game is running in
    HashMap<KeyCode, KeyBinding> keyBindings = new HashMap<>(); // Map of keyboard keys to KeyBindings
    HashMap<MouseButton, MouseBinding> mouseBindings = new HashMap<>(); // Map of mouse buttons to MouseBindings

    // Three booleans for tracking whether the scene's listeners have been initialized or not, allows for only
    // initializing the listeners on a need basis.
    private boolean keyListenerInitialized = false;
    private boolean mouseClickListenerInitialized = false;
    private boolean mouseMoveListenerInitialized = false;

    /**
     * Constructor
     * @param scene JavaFX scene the game is run in
     */
    public UserInputHandler(Scene scene)
    {
        this.scene = scene;
    }

    /**
     * Creates a KeyBinding that tracks the status of the given key
     * @param key KeyCode marking the key to track
     * @return KeyBinding instance
     */
    public KeyBinding createKeyBinding(KeyCode key)
    {
        // If KeyBinding for this key already created, return it
        if(keyBindings.containsKey(key)) return keyBindings.get(key);

        // Set up key listener if not set up yet
        if(!keyListenerInitialized) initializeKeyListener();

        // Create new keybinding and store it in the map
        KeyBinding binding = new KeyBinding(key);
        keyBindings.put(key, binding);

        return binding;
    }

    /**
     * Creates a MouseBinding for tracking only mouse clicks, and the mouse location at the last mouse click
     * @param button a MouseButton indicating which mouse button to track
     * @return a MouseBinding instance
     */
    public MouseBinding createMouseClickBinding(MouseButton button)
    {
        // If mouse binding already exists, return it
        if(mouseBindings.containsKey(button)) return mouseBindings.get(button);

        // Set up mouse click listeners if not set up yet
        if(!mouseClickListenerInitialized) initializeMouseClickListener();

        // Otherwise, make a new binding and set up listeners
        MouseBinding mouseBinding = new MouseBinding(button, false);
        mouseBindings.put(button, mouseBinding);

        return mouseBinding;
    }

    /**
     * Creates an instance of MouseBinding that tracks the status of a mouse button as well as always watches the mouse's
     * position whenever the mouse is moved
     * @param button a MouseButton indicating which mouse button to track
     * @return a MouseBinding instance
     */
    public MouseBinding createMouseListener(MouseButton button)
    {
        // If mouse binding already exists, return it
        if(mouseBindings.containsKey(button) && mouseBindings.get(button).trackMovement)
        {
            return mouseBindings.get(button);
        }

        // Set up mouse click listeners if not set up yet
        if(!mouseClickListenerInitialized) initializeMouseClickListener();

        // Set up mouse move listeners if not set up yet
        if(!mouseMoveListenerInitialized) initializeMouseMoveListener();

        // Otherwise, make a new binding and set up listeners
        MouseBinding mouseBinding = new MouseBinding(button, true);
        mouseBindings.put(button, mouseBinding);

        return mouseBinding;
    }

    /**
     * Sets up listeners for the scene for tracking keyboard presses
     */
    private void initializeKeyListener()
    {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyBindings.containsKey(keyEvent.getCode()))
                {
                    KeyBinding keyBinding = keyBindings.get(keyEvent.getCode());
                    if(!keyBinding.isPressed()) keyBinding.setPressed(true);
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyBindings.containsKey(keyEvent.getCode()))
                {
                    keyBindings.get(keyEvent.getCode()).setPressed(false);
                }
            }
        });

        keyListenerInitialized = true;
    }

    /**
     * Sets up mouse press and release listeners for tracking mouse presses.
     */
    private void initializeMouseClickListener()
    {
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseBindings.containsKey(mouseEvent.getButton()))
                {
                    MouseBinding mouseBinding = mouseBindings.get(mouseEvent.getButton());
                    if(!mouseBinding.isPressed()){
                        mouseBinding.setMousePosition((float)mouseEvent.getSceneX(), (float)mouseEvent.getSceneY());
                        mouseBinding.setPressed(true);
                    }
                }
            }
        });

        scene.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseBindings.containsKey(mouseEvent.getButton())) {
                    mouseBindings.get(mouseEvent.getButton()).setPressed(false);
                }
            }
        });

        mouseClickListenerInitialized = true;
    }

    /**
     * Sets up mouse move and drag listeners for continuous mouse position tracking
     */
    private void initializeMouseMoveListener()
    {
        scene.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                for(Map.Entry<MouseButton, MouseBinding> entry: mouseBindings.entrySet())
                {
                    MouseBinding binding = entry.getValue();
                    if(binding.trackMovement) {
                        binding.setMousePosition((float) mouseEvent.getSceneX(), (float) mouseEvent.getSceneY());
                    }
                }
            }
        });

        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                for(Map.Entry<MouseButton, MouseBinding> entry: mouseBindings.entrySet())
                {
                    MouseBinding binding = entry.getValue();
                    if(binding.trackMovement) {
                        binding.setMousePosition((float) mouseEvent.getSceneX(), (float) mouseEvent.getSceneY());
                    }
                }
            }
        });

        mouseMoveListenerInitialized = true;
    }

}
