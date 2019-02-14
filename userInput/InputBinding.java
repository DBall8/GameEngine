package gameEngine.userInput;

abstract class InputBinding {
    protected boolean isPressed;

    InputBinding()
    {
        isPressed = false;
    }

    public boolean isPressed(){ return isPressed; }
    void setPressed(boolean isPressed)
    {
        this.isPressed = isPressed;
    }
}
