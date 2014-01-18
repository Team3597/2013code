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
     public static final int module1 = 1;
     public static final int module2 = 2;
     
     public static final int leftMotor = 2;         // CAN ID for Jag
     public static final int rightMotor = 3;        // CAN ID for Jag// still need PWM numbers for vics and servos
     public static final int launchMotor = 4;       // CAN ID for Jag     
     public static final int aimingMotor = 5;        // CAN ID for Jag 
     public static final int extendingArmsMotor = 6;// CAN ID for Jag
     public static final int bodyHooksMotor = 7;    // CAN ID for Jag
     public static final int topHandMotor = 8;      // CAN ID for Jag
     public static final int bottomHandMotor = 9;   // CAN ID for Jag
     public static final int skidMotor = 10;        // CAN ID for Jag no encoder stopped by limit
     public static final int boomerangMotor = 11;   // CAN ID for Jag no encoder stopped by limit  
     
     public static final int loadMotor = 1;         // PWM probable VIC, possible CAN separate encoder
     public static final int TAGservo = 2;          // PWM spring tension Transition Assist Guide
     
     public static final int loadEncoderA = 1;
     public static final int loadEncoderB = 2;     
     public static final int rangeSensor = 3;
     public static final int armSwitch = 4;
     
     
     
     
             
     
    
    // If you are using multiple+- modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;
}
