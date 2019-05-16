package gameEngine;

import javafx.scene.Group;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;

/**
 * Simple class for drawing some shapes at a given position and angle each frame update
 */
public abstract class Entity {

    private Group visuals = new Group(); // Contains all shapes to draw
    private Rotate rotate = new Rotate(0, 0, 0); // Used to rotate the visuals
    private Camera camera;
    protected float x = 0; // x coordinate
    protected float y = 0; // y coordinate
    protected float orientation = 0; // angle (in degrees)

    /**
     * Constructor
     */
    public Entity(){
        // Sets the center of rotation to the center of the visuals
        visuals.getTransforms().add(rotate);
    }

    /**
     * Gets the visual group to add visuals to
     * @return
     */
    Group getVisuals(){ return visuals; }

    /**
     * Adds a visual to the group of visuals to draw for this entity
     * @param visual
     */
    protected void addVisual(Shape visual)
    {
        visuals.getChildren().add(visual);
    }

    /**
     * Methods for removing visuals
     */
    protected void removeVisual(Shape visual){ visuals.getChildren().remove(visual);}
    protected void removeVisual(int i){ visuals.getChildren().remove(i);}
    protected void removeAllVisuals(){ visuals.getChildren().clear();}

    /**
     * Sets the x and y locations of the entity
     * @param x
     * @param y
     */
    protected void setLocation(float x, float y) { this.x = x; this.y = y; }

    /**
     * Method to override for updating the entity's state each frame before drawing
     */
    public abstract void update();

    /**
     * Default draw method for drawing the entity, can be overridden if needed
     */
    public void draw(Camera camera)
    {
//        boolean visibleX = Math.abs(camera.getCameraX() - x) < camera.getRenderDistance();
//        boolean visibleY = Math.abs(camera.getCameraY() - y) < camera.getRenderDistance();
//
//        if(visibleX && visibleY) {
            visuals.setTranslateX(x + camera.getAdjustmentX());
            visuals.setTranslateY(y + camera.getAdjustmentY());
            rotate.setAngle(orientation);
//        }
    }

    public float getWorldX()
    {
        return x;
    }

    public float getWorldY()
    {
        return y;
    }

    public float getScreenX()
    {
        return x + camera.getAdjustmentX();
    }

    public float getScreenY()
    {
        return y + camera.getAdjustmentY();
    }

    void setCamera(Camera camera)
    {
        this.camera = camera;
    }
}
