package gameEngine;

public class Settings {

    private float framesPerSecond = 120;
    private int windowWidth = 800;
    private int windowHeight = 800;

    Settings(){}

    public float getFramesPerSecond(){ return framesPerSecond; }
    public void setFramesPerSecond(float framesPerSecond){ this.framesPerSecond = framesPerSecond; }

    void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    }

    int getWindowHeight() {
        return windowHeight;
    }

    void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }

    int getWindowWidth(){ return windowWidth; }
}
