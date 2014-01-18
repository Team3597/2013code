
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.templates.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
    public static final int driverJoystick_Port = 1;
    public static final int leftDriverTrigger_Port = 5;
    public static final int rightDriverTrigger_Port = 6;      
    public static final int driverButtonA_Port = 1;      
    public static final int driverButtonY_Port = 4;      
    private static OI instance = null;
    private static Joystick driverStick; //keep private and then make a function to find it. keeps editing impossible    
    private static JoystickButton rightDriverTrigger;
    private static JoystickButton leftDriverTrigger;
    private static JoystickButton driverButtonA;
    private static JoystickButton driverButtonY;
        
    public static final int shooterJoystickPort = 2;
    public static final int leftShooterTrigger_Port = 5;
    public static final int rightShooterTrigger_Port = 6;  
    //private static Joystick shooterStick; //keep private and then make a function to find it. keeps editing impossible    
    //private static JoystickButton rightShooterTrigger;
    //private static JoystickButton leftShooterTrigger;
    
    
    public OI() {
        driverStick = new Joystick(driverJoystick_Port); 
        rightDriverTrigger = new JoystickButton(driverStick, rightDriverTrigger_Port);
        rightDriverTrigger.whenPressed(new ClimberGroupPassBar()); 
        //leftDriverTrigger = new JoystickButton(driverStick, leftDriverTrigger_Port);
        //leftDriverTrigger.whenPressed(new ClimberEmergencyStop());
        //driverButtonA = new JoystickButton(driverStick, driverButtonA_Port);
        //riverButtonA.whenPressed(new ClimberGroup1()); 
        //driverButtonY = new JoystickButton(driverStick, driverButtonY_Port);
        //driverButtonY.whenPressed(new ClimberGroup2());
        
        
        //shooterStick = new Joystick(shooterJoystickPort); 
        //rightShooterTrigger = new JoystickButton(shooterStick, rightShooterTrigger_Port);
        //rightShooterTrigger.whenPressed(new ShooterGroupFire()); // add preset angles for the four buttons?
    }
    //public Joystick getDriverJoystick() {        
      //  return driverStick;
    //}   
    //public Joystick getShooterJoystick() {
      //  return shooterStick;
    //}    
}


// does ShooterFinalShots need exact positioning?
// setReferenceSpeed/POsition
// position control necessary for reset, not for reading encoder. 
// Reset necessary, encoder registers change even when robot is disabled. Easily start with an unexpected number
// changing CANControlMode stops motors until set again
// speed and position modes needs PID to move, use SetX for all modes setPID(20.0, 0.1, 0.01) is ok..


