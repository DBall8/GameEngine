package gameEngine;

import javafx.scene.Group;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;

public abstract class Entity {

    private Group visuals = new Group();
    private Rotate rotate = new Rotate(0, 0, 0);
    protected float x = 0;
    protected float y = 0;
    protected float orientation = 0;

    public Entity(){
        visuals.getTransforms().add(rotate);
    }

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
        rotate.setAngle(orientation);
    }
}
