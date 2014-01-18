
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.templates.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    private static OI instance = null;
    
    public static final int driverJoystick_Port = 1;        
    private static Joystick driverStick; //keep private and then make a function to find it. keeps editing impossible    
        
    public static final int shooterJoystickPort = 2;
    public static final int leftShooterTrigger_Port = 5;
    public static final int rightShooterTrigger_Port = 6;  
    private static Joystick shooterStick; //keep private and then make a function to find it. keeps editing impossible    
    private static JoystickButton rightShooterTrigger;
    private static JoystickButton leftShooterTrigger;
    
    
    public OI() {
        driverStick = new Joystick(driverJoystick_Port);         
        
        shooterStick = new Joystick(shooterJoystickPort); 
        rightShooterTrigger = new JoystickButton(shooterStick, rightShooterTrigger_Port);
        rightShooterTrigger.whenPressed(new LoaderLoadFrisbee());  
        leftShooterTrigger = new JoystickButton(shooterStick, leftShooterTrigger_Port);
        leftShooterTrigger.whenPressed(new ShooterControlFrisbeeMotor());         
    }
    public Joystick getDriverJoystick() {        
        return driverStick;
    }   
    public Joystick getShooterJoystick() {
        return shooterStick;
    }    
}


// does ShooterFinalShots need exact positioning?

// setReferenceSpeed/POsition and configCodesperRev required for encoders
// position control necessary for reset, not for reading encoder. 
// Reset necessary, encoder registers change even when robot is disabled. Easily start with an unexpected number
// changing CANControlMode stops motors until set again

// speed and position modes needs PID to move, use SetX for all modes setPID(20.0, 0.1, 0.01) is ok..
// CommandGroups are run immediately before enable, add() won't run until the function is called
//. but loops, prints, and ifs will.
// don't use anything other than add or print in commandGroup, it acts strange and won't do what is wished.
// loops in a regular command are fair game

// Right Motor is weak, it can't run alone without locking up after a couple seconds. 
// If a lock-up occurs, release the right thumbstick and wait a couple seconds. Left Motor is fine
// left circle movement is not possible because the right motor would bear the burden of pushing
// left turn in place using both motors is quite feasible
// Do not jam the thumbsticks in sudden directions, it may cause the robot to take command. 
// If this occurs, disable and re-download the code to fix. 
// Disable and enable will put the robot back in command doing what it was before.


