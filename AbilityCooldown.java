package gameEngine;

import java.util.Timer;
import java.util.TimerTask;

public class AbilityCooldown {
    private TimerTask activeTask = null;
    private float cooldown;
    private boolean isReady;

    public AbilityCooldown(float cooldownInSeconds)
    {
        isReady = true;
        this.cooldown = cooldownInSeconds;
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

        isReady = false;
        timer.schedule(activeTask, (int)(cooldown * 1000));
    }
}
