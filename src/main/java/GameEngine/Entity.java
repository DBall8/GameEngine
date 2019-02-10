package GameEngine;

import javafx.scene.Group;
import javafx.scene.shape.Shape;

public abstract class Entity {

    private Group visuals = new Group();
    protected float x = 0;
    protected float y = 0;

    public Entity(){}

    Group getVisuals(){ return visuals; }

    protected void addVisual(Shape visual)
    {
        visuals.getChildren().add(visual);
    }
    public abstract void update();
    public void draw()
    {
        visuals.setTranslateX(x);
        visuals.setTranslateY(y);
    }
}
