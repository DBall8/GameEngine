package gameEngine;

import gameEngine.callback.Callback;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Class for creating a simple ability
 */
public class Ability {
    Timer timer;
    private TimerTask activeTask = null;
    private float cooldown;
    private Callback callback;
    private boolean isReady;

    /**
     * Constructor
     * @param cooldownInSeconds cooldown time in seconds
     * @param callback function to call when successfully used
     */
    public Ability(float cooldownInSeconds, Callback callback)
    {
        timer = new Timer(true);
        isReady = true;
        this.cooldown = cooldownInSeconds;
        this.callback = callback;
    }

    /**
     * Returns true when the cooldown period since the ability's last use has NOT passed
     * @return
     */
    public boolean isOnCooldown()
    {
        return !isReady;
    }

    /**
     * If if the cooldown period since last use has passed, uses the ability. Otherwise, does nothing
     */
    public void use()
    {
        // If still on cooldown, don't use
        if(!isReady) return;

        // If there is already a timer running, reset it
        if(activeTask != null)
        {
            activeTask.cancel();
        }

        // Set up cooldown timer
        activeTask = new TimerTask() {
            @Override
            public void run() {
                isReady = true;
            }
        };

        // Use ability callback
        callback.run(null);
        isReady = false;
        // Start cooldown timer
        timer.schedule(activeTask, (int)(cooldown * 1000));
    }
}
