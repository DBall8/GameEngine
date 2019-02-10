package GameEngine;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserInputHandler {
    HashMap<KeyCode, List<KeyBinding>> keyBindings = new HashMap<>();

    UserInputHandler(Scene scene)
    {
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

//        scene.setOnMouseMoved(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                mouseMoved(event);
//            }
//        });
//
//        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) { mouseMoved(event); }
//        });
//
//        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                if(!mouseDown){
//                    mouseDown = true;
//                    mousePressed = true;
//                }
//            }
//        });
//
//        scene.setOnMouseReleased(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                mouseDown = false;
//                mouseReleased = true;
//            }
//        });
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

    private void mouseMoved(MouseEvent mouseEvent)
    {

    }


}
