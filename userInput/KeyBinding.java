package gameEngine.userInput;

import javafx.scene.input.KeyCode;

/**
 * Class for tracking if a keyboard key is currently pressed or not
 */
public class KeyBinding extends InputBinding {
    KeyCode key; // KeyCode being watched

    KeyBinding(KeyCode key)
    {
        this.key = key;
    }

    public KeyCode getKey(){ return key; }
}
