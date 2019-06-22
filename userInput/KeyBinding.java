package gameEngine.userInput;

import gameEngine.callback.Callback;
import javafx.scene.input.KeyCode;

/**
 * Class for tracking if a keyboard key is currently pressed or not
 */
public class KeyBinding extends InputBinding {
    KeyCode key; // KeyCode being watched
    private Callback<Void> onPressEvent = null;
    private Callback<Void> onReleaseEvent = null;

    KeyBinding(KeyCode key)
    {
        this.key = key;
    }

    @Override
    void setPressed(boolean isPressed)
    {
        super.setPressed(isPressed);
        if(isPressed && onPressEvent != null)
        {
            onPressEvent.run(null);
        }
        else if(!isPressed && onReleaseEvent != null)
        {
            onReleaseEvent.run(null);
        }
    }

    public KeyCode getKey(){ return key; }

    public void setOnPressEvent(Callback<Void> onPressEvent)
    {
        this.onPressEvent = onPressEvent;
    }

    public void setOnReleaseEvent(Callback<Void> onReleaseEvent)
    {
        this.onReleaseEvent = onReleaseEvent;
    }
}
