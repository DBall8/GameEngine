package GameEngine;

public class Settings {

    private static float FRAMERATE = 120;

    public static float getFramerate(){ return getInstance().FRAMERATE; }
    public static void setFramerate(float framerate){getInstance().FRAMERATE = framerate; }

    private Settings(){}
    private final static class SingletonHelper{
        private static final Settings _instance = new Settings();
    }
    private static Settings getInstance(){ return SingletonHelper._instance; }
}
