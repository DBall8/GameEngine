package gameEngine;

import gameEngine.callback.Callback;

import java.util.Timer;
import java.util.TimerTask;

public class Ability {
    private TimerTask activeTask = null;
    private float cooldown;
    private Callback callback;
    private boolean isReady;

    public Ability(float cooldownInSeconds, Callback callback)
    {
        isReady = true;
        this.cooldown = cooldownInSeconds;
        this.callback = callback;
    }

    public boolean isOnCooldown()
    {
        return !isReady;
    }

    public void use()
    {
        // If still on cooldown, don't use
        if(!isReady) return;

        // If there is already a timer running, reset it
        if(activeTask != null)
        {
            activeTask.cancel();
        }

        Timer timer = new Timer();
        activeTask = new TimerTask() {
            @Override
            public void run() {
                isReady = true;
                timer.cancel();
            }
        };

        callback.run(null);
        isReady = false;
        timer.schedule(activeTask, (int)(cooldown * 1000));
    }
}
