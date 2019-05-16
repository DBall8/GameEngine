package gameEngine;

public class Camera {
    private final static int DEFAUL_RENDER_DISTANCE = 2000;

    private int xpos;
    private int ypos;

    private int windowWidth;
    private int windowHeight;

    private int renderDistance = DEFAUL_RENDER_DISTANCE;

    public Camera(int windowWidth, int windowHeight)
    {
        xpos = windowWidth / 2;
        ypos = windowHeight / 2;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    public int getAdjustmentX()
    {
        return (windowWidth/2) - xpos;
    }

    public int getAdjustmentY()
    {
        return (windowHeight/2) - ypos;
    }

    public void setCameraPosition(int x, int y)
    {
        xpos = x;
        ypos = y;
    }

    public void moveCameraPosition(int changeX, int changeY)
    {
        xpos += changeX;
        ypos += changeY;
    }

    public int getCameraX()
    {
        return xpos;
    }

    public int getCameraY()
    {
        return ypos;
    }

    void updateWindowSize(int windowWidth, int windowHeight)
    {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    public void setRenderDistance(int renderDistance){ this.renderDistance = renderDistance; }
    int getRenderDistance(){ return renderDistance; }
}
