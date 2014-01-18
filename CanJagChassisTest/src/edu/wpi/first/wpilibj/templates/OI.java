
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.templates.commands.ChassisPyramidBoost;
import edu.wpi.first.wpilibj.templates.commands.ChassisStopPyramid;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
     Joystick stick;
     JoystickButton trigger;
     JoystickButton trigger2;
    
     public OI() {
         stick = new Joystick(1);  
         trigger = new JoystickButton(stick, 6);
         trigger.whenPressed(new ChassisStopPyramid());
         trigger2 = new JoystickButton(stick, 5);
         trigger2.whileHeld(new ChassisPyramidBoost());         
     }
     public Joystick getStick() {
         return stick;
     }
}

// Right Motor is weak, it can't run alone without locking up after a couple seconds. 
// If a lock-up occurs, release the right thumbstick and wait a couple seconds. Left Motor is fine
// left circle movement is not possible because the right motor would bear the burden of pushing
// left turn in place using both motors is quite feasible
// Do not jam the thumbsticks in sudden directions, it may cause the robot to take command. 
// If this occurs, disable and re-download the code to fix. 
// Disable and enable will put the robot back in command doing what it was before.
