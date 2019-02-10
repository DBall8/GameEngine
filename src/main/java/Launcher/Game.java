package Launcher;

import GameEngine.GameEngine;

public class Game extends GameEngine {

    @Override
    public void onStart()
    {
        Player p = new Player(50, 50, userInputHandler);
        addEntity(p);
    }

    @Override
    public int getWindowWidth() {
        return 800;
    }

    @Override
    public int getWindowHeight() {
        return 800;
    }
}
