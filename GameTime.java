package gameEngine;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Class for running the frame update timer
 */
public class GameTime {

    private Timeline time;
    private boolean playing = false;
    private FrameRateTracker frameRateTracker = null;


    GameTime(GameEngine game){
        boolean showFrameRate = game.isFramerateShown();
        if(showFrameRate) frameRateTracker = new FrameRateTracker();

        time = new Timeline();
        time.setCycleCount(Animation.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(1000L / game.getFramesPerSecond()), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // update positions
                game.calculateFrame();
                if(showFrameRate) frameRateTracker.incrementFrameCount();

            }
        });
        time.getKeyFrames().add(keyFrame);

        if(showFrameRate) frameRateTracker.start();
    }

    /**
     * Starts the frame update timer if not already running
     */
    public void play(){
        if(!playing){
            playing = true;
            time.play();
        }
    }

    /**
     * Stops the frame update timer if not already stopped
     */
    public void pause(){
        if(playing){
            playing = false;
            time.pause();
        }
    }

    float getFrameRate()
    {
        if(frameRateTracker == null) return 0;
        return frameRateTracker.getLatestFrameRate();
    }

    private class FrameRateTracker
    {
        private final static int UPDATE_TIME_MSEC = 500;
        private int numFrames;
        private Timer timer;
        private float latestRate;

        FrameRateTracker()
        {
            numFrames = 0;
            latestRate = 0;
            timer = new Timer(true);
        }

        public float getLatestFrameRate()
        {
            return latestRate;
        }

        void start()
        {
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    update();
                }
            };
            timer.scheduleAtFixedRate(task, 1000, UPDATE_TIME_MSEC);

        }

        synchronized void update()
        {
            float recentRate = numFrames * (1000 / UPDATE_TIME_MSEC);
            latestRate = (latestRate + recentRate) / 2.0f;
            numFrames = 0;
        }

        synchronized void incrementFrameCount()
        {
            numFrames++;
        }

    }
}
