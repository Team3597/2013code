/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.*;

/**
 *
 * @author Matthew
 */

public class Chassis extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    Servo TAGservo;
    
    public Chassis() {
        TAGservo = new Servo(RobotMap.TAGservo);
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new RunServo());
    }
    public void runServo(double angle) {
        TAGservo.set(angle);                
        System.out.println(TAGservo.getAngle());
    }
}

// PUT IN FINISHED PRODUCT