package gameEngine.userInput;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserInputHandler {
    Scene scene;
    HashMap<KeyCode, List<KeyBinding>> keyBindings = new HashMap<>();
    MouseBinding mouseBinding;

    public UserInputHandler(Scene scene)
    {
        this.scene = scene;

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                keyDown(event);
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                keyUp(event);
            }
        });
    }

    public KeyBinding createKeyBinding(KeyCode key)
    {
        KeyBinding binding = new KeyBinding(key);

        if(keyBindings.containsKey(key))
        {
            keyBindings.get(key).add(binding);
        }
        else
        {
            List<KeyBinding> bindingList = new ArrayList<KeyBinding>();
            bindingList.add(binding);
            keyBindings.put(key, bindingList);
        }

        return binding;
    }

    public MouseBinding createMouseClickBinding()
    {
        mouseBinding = new MouseBinding();

        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(!mouseBinding.isPressed()){
                    mouseBinding.setMousePosition((float)event.getSceneX(), (float)event.getSceneY());
                    mouseBinding.setPressed(true);
                }
            }
        });

        scene.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseBinding.setPressed(false);
            }
        });

        return mouseBinding;
    }

    public MouseBinding createMouseListener()
    {
        mouseBinding = new MouseBinding();
        scene.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseBinding.setMousePosition((float)event.getSceneX(), (float)event.getSceneY());
            }
        });

        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseBinding.setMousePosition((float)event.getSceneX(), (float)event.getSceneY());
            }
        });

        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(!mouseBinding.isPressed()){
                    mouseBinding.setMousePosition((float)event.getSceneX(), (float)event.getSceneY());
                    mouseBinding.setPressed(true);
                }
            }
        });

        scene.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseBinding.setPressed(false);
            }
        });

        return mouseBinding;
    }

    private void keyDown(KeyEvent keyEvent)
    {
        if(keyBindings.containsKey(keyEvent.getCode()))
        {
            for(KeyBinding binding: keyBindings.get(keyEvent.getCode()))
            {
                if(!binding.isPressed()) binding.setPressed(true);
            }
        }
    }

    private void keyUp(KeyEvent keyEvent)
    {
        if(keyBindings.containsKey(keyEvent.getCode()))
        {
            for(KeyBinding binding: keyBindings.get(keyEvent.getCode()))
            {
                binding.setPressed(false);
            }
        }
    }
}
