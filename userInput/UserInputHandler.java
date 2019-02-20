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

public class UserInputHandler {
    Scene scene;
    HashMap<KeyCode, KeyBinding> keyBindings = new HashMap<>();
    HashMap<MouseButton, MouseBinding> mouseBindings = new HashMap<>();

    private boolean keyListenerInitialized = false;
    private boolean mouseClickListenerInitialized = false;
    private boolean mouseMoveListenerInitialized = false;

    public UserInputHandler(Scene scene)
    {
        this.scene = scene;
    }

    public KeyBinding createKeyBinding(KeyCode key)
    {
        if(keyBindings.containsKey(key)) return keyBindings.get(key);

        // Set up key listener if not set up yet
        if(!keyListenerInitialized) initializeKeyListener();

        KeyBinding binding = new KeyBinding(key);
        keyBindings.put(key, binding);

        return binding;
    }

    public MouseBinding createMouseClickBinding(MouseButton button)
    {
        // If mouse binding already exists, return it
        if(mouseBindings.containsKey(button)) return mouseBindings.get(button);

        // Set up mouse click listeners if not set up yet
        if(!mouseClickListenerInitialized) initializeMouseClickListener();

        // Otherwise, make a new binding and set up listeners
        MouseBinding mouseBinding = new MouseBinding(button);
        mouseBindings.put(button, mouseBinding);

        return mouseBinding;
    }

    public MouseBinding createMouseListener(MouseButton button)
    {
        // If mouse binding already exists, return it
        if(mouseBindings.containsKey(button)) return mouseBindings.get(button);

        // Set up mouse click listeners if not set up yet
        if(!mouseClickListenerInitialized) initializeMouseClickListener();

        // Set up mouse move listeners if not set up yet
        if(!mouseMoveListenerInitialized) initializeMouseMoveListener();

        // Otherwise, make a new binding and set up listeners
        MouseBinding mouseBinding = new MouseBinding(button);
        mouseBindings.put(button, mouseBinding);

        return mouseBinding;
    }

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

    private void initializeMouseMoveListener()
    {
        scene.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                for(Map.Entry<MouseButton, MouseBinding> entry: mouseBindings.entrySet())
                {
                    entry.getValue().setMousePosition((float) mouseEvent.getSceneX(), (float) mouseEvent.getSceneY());
                }
            }
        });

        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                for(Map.Entry<MouseButton, MouseBinding> entry: mouseBindings.entrySet())
                {
                    entry.getValue().setMousePosition((float) mouseEvent.getSceneX(), (float) mouseEvent.getSceneY());
                }
            }
        });

        mouseMoveListenerInitialized = true;
    }

}
