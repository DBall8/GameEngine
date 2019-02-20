package gameEngine;

/**
 * Class containing basic settings for the game
 */
public class Settings {

    private float framesPerSecond = 120; // number of frames drawn per second
    private int windowWidth = 800; // screen width
    private int windowHeight = 800; // screen height

    Settings(){}

    // GETTERS AND SETTERS
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
