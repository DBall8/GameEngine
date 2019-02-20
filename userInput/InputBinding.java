package gameEngine.userInput;

/**
 * Super class for common handling of key and mouse bindings
 */
abstract class InputBinding {
    protected boolean isPressed; // True when the binding is currently pressed down

    InputBinding()
    {
        isPressed = false;
    }

    // GETTERS AND SETTERS
    public boolean isPressed(){ return isPressed; }
    void setPressed(boolean isPressed)
    {
        this.isPressed = isPressed;
    }
}
