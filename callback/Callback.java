package gameEngine.callback;

/**
 * Simple class for creating a callback function to be run at a later time
 * @param <T> The type of the parameter to pass to the callback, (can be Void for no parameter)
 */
public abstract class Callback<T> {
    public abstract void run(T parameter);
}
