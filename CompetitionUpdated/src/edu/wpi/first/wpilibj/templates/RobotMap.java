package edu.wpi.first.wpilibj.templates;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    
     public static final int loadMotor = 2;         // CAN ID for Jag
     public static final int launchMotor = 3;       // CAN ID for Jag 
     public static final int leftMotor = 5;         // CAN ID for Jag
     public static final int rightMotor = 6;        // CAN ID for Jag
     public static final int aimingMotor = 8;       // CAN ID for Jag
     
     public static final int limitSwitch = 1;
}
