package gameEngine.userInput;

public class MouseBinding {
    private float mouseX = 0;
    private float mouseY = 0;
    private boolean isPressed = false;
    private boolean isClicked = false;

    MouseBinding(){}

    public boolean isPressed() {
        return isPressed;
    }
    public boolean isClicked() {
        return isClicked;
    }
    public void consumeClick()
    {
        isClicked = false;
    }
    public float getMouseX(){ return mouseX; }
    public float getMouseY(){ return mouseY; }

    void setPressed(boolean isPressed) {
        this.isPressed = isPressed;
        this.isClicked = isPressed;
    }
    void setMousePosition(float x, float y)
    {
        mouseX = x;
        mouseY = y;
    }
}
