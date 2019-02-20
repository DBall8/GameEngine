package gameEngine.userInput;

import javafx.scene.input.MouseButton;

/**
 * Class for watching the status of a mouse
 */
public class MouseBinding extends InputBinding {
    // NOTE the following mouse positions are updated only on a mouse press, unless the instance of this class was
    // created as a mouse listener in which case the positions are updated on mouse moved or dragged
    private float mouseX = 0; // Mouse's x position
    private float mouseY = 0; // Mouse's y position
    private boolean isClicked = false; // true when the corresponding mouse button is first clicked until the click is
                                       // consumed using consumeClick()
    private MouseButton button; // The mouse button to watch

    boolean trackMovement;

    MouseBinding(MouseButton button, boolean trackMovement)
    {
        this.button = button;
        this.trackMovement = trackMovement;
    }

    /**
     * Returns true from when the mouse button is pressed until consumeClick() is called
     * @return
     */
    public boolean isClicked() {
        return isClicked;
    }

    /**
     * Consumes a mouse click to avoid constantly returning a mouse click while the mouse button is held
     */
    public void consumeClick()
    {
        isClicked = false;
    }

    // GETTERS AND SETTERS
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
