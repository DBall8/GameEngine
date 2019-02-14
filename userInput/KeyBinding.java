package gameEngine.userInput;

import javafx.scene.input.KeyCode;

public class KeyBinding extends InputBinding {
    KeyCode key;

    KeyBinding(KeyCode key)
    {
        this.key = key;
    }

    public KeyCode getKey(){ return key; }
}
