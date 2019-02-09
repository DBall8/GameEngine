package GameEngine;

import javafx.scene.Group;
import javafx.scene.shape.Shape;

public abstract class Entity {

    private Group visuals;

    public Entity(){}

    protected void addVisual(Shape visual)
    {
        visuals.getChildren().add(visual);
    }
    void update(){}
    abstract void draw();
}
