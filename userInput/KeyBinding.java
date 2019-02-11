package gameEngine.userInput;

import javafx.scene.input.KeyCode;

public class KeyBinding {
    KeyCode key;
    boolean isPressed;

    KeyBinding(KeyCode key)
    {
        this.key = key;
        isPressed = false;
    }

    public boolean isPressed(){ return isPressed; }
    public KeyCode getKey(){ return key; }

    void setPressed(boolean isPressed)
    {
        this.isPressed = isPressed;
    }
}
