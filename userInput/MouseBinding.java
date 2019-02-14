package gameEngine.userInput;

import javafx.scene.input.MouseButton;

public class MouseBinding extends InputBinding {
    private float mouseX = 0;
    private float mouseY = 0;
    private boolean isClicked = false;
    private MouseButton button;

    MouseBinding(MouseButton button)
    {
        this.button = button;
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
    public MouseButton getButton(){ return button; }

    @Override
    void setPressed(boolean isPressed) {
        super.setPressed(isPressed);
        this.isClicked = isPressed;
    }
    void setMousePosition(float x, float y)
    {
        mouseX = x;
        mouseY = y;
    }
}
