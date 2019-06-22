package gameEngine.userInput;

import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;

public class XBoxController {

    private static ControllerManager ControllerCenter = null;
    private int index;
    private ControllerState state;

    public enum ControllerButton
    {
        A,
        B,
        X,
        Y,
        DPadUp,
        DPadRight,
        DPadDown,
        DPadLeft,
        LeftStick,
        RightStick,
        Start,
        Select,
        RightBumper,
        LeftBumper
    }

    public enum ControllerStick
    {
        LeftStick,
        RightStick,
    }

    public enum ControllerTrigger
    {
        LeftTrigger,
        RightTrigger,
    }

    public XBoxController(int index)
    {
        this.index = index;

        if(ControllerCenter == null)
        {
            ControllerCenter = new ControllerManager();
            ControllerCenter.initSDLGamepad();
        }

        updateState();
    }

    public int getIndex(){return index;}

    public void updateState(){ state = ControllerCenter.getState(index); }

    public boolean isConnected()
    {
        return state.isConnected;
    }

    public boolean isButtonPressed(ControllerButton button)
    {
        switch (button)
        {
            case A:
                return state.a;
            case B:
                return state.b;
            case X:
                return state.x;
            case Y:
                return state.y;
            case DPadUp:
                return state.dpadUp;
            case DPadLeft:
                return state.dpadLeft;
            case DPadDown:
                return state.dpadDown;
            case DPadRight:
                return state.dpadRight;
            case Start:
                return state.start;
            case Select:
                return state.back;
            case LeftStick:
                return state.leftStickClick;
            case RightStick:
                return state.rightStickClick;
            case LeftBumper:
                return state.lb;
            case RightBumper:
                return state.rb;
            default:
                return false;
        }
    }

    public float getStickX(ControllerStick stick)
    {
        switch(stick)
        {
            case LeftStick:
                return state.leftStickX;
            case RightStick:
                return state.rightStickX;
            default:
                return 0;
        }
    }

    public float getStickY(ControllerStick stick)
    {
        switch(stick)
        {
            case LeftStick:
                return state.leftStickY;
            case RightStick:
                return state.rightStickY;
            default:
                return 0;
        }
    }

    public float getStickAngle(ControllerStick stick)
    {
        switch(stick)
        {
            case LeftStick:
                return state.leftStickAngle;
            case RightStick:
                return state.rightStickAngle;
            default:
                return 0;
        }
    }

    public float getStickMagnitude(ControllerStick stick)
    {
        switch(stick)
        {
            case LeftStick:
                return state.leftStickMagnitude;
            case RightStick:
                return state.rightStickMagnitude;
            default:
                return 0;
        }
    }

    public float getTriggerMagnitude(ControllerTrigger trigger)
    {
        switch(trigger)
        {
            case LeftTrigger:
                return state.leftTrigger;
            case RightTrigger:
                return state.rightTrigger;
            default:
                return 0;
        }
    }
}
