package Launcher;

import GameEngine.GameEngine;

public class Game extends GameEngine {

    @Override
    protected int getWindowWidth() {
        return 1000;
    }

    @Override
    protected int getWindowHeight() {
        return 400;
    }
}
