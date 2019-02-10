package Launcher;

import GameEngine.Entity;
import GameEngine.KeyBinding;
import GameEngine.UserInputHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player extends Entity
{
    private final static int WIDTH = 50;
    private final static int HEIGHT = 50;
    private final static float SPEED = 10;

    KeyBinding up, down, left, right;

    public Player(int x, int y, UserInputHandler inputHandler)
    {
        this.x = x;
        this.y = y;

        Rectangle rec = new Rectangle(-WIDTH/2, -HEIGHT/2, WIDTH, HEIGHT);
        rec.setFill(Color.BLUE);
        addVisual(rec);

        up = inputHandler.createKeyBinding(KeyCode.W);
        down = inputHandler.createKeyBinding(KeyCode.S);
        left = inputHandler.createKeyBinding(KeyCode.A);
        right = inputHandler.createKeyBinding(KeyCode.D);
    }

    @Override
    public void update()
    {
        float dx = 0;
        float dy = 0;
        if(up.isPressed() && !down.isPressed())
        {
            dy = -SPEED;
        }
        else if(down.isPressed() && !up.isPressed())
        {
            dy = SPEED;
        }

        if(left.isPressed() && !right.isPressed())
        {
            dx = -SPEED;
        }
        else if(right.isPressed() && !left.isPressed())
        {
            dx = SPEED;
        }

        x += dx;
        y += dy;
    }
}
