/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.HandsGroupChangeHands;

/**
 *
 * @author Administrator
 */
public class Hands extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    CANJaguar topHandMotor;
    CANJaguar bottomHandMotor;
    AnalogChannel rangeSensor;
    double range;
    public boolean begin;
    double encoderRange;
    int forwardStopPoint;
    int reverseStopPoint;
    
    public Hands() {
        try {            
            topHandMotor = new CANJaguar(RobotMap.topHandMotor);
            topHandMotor.configEncoderCodesPerRev(360);            
            topHandMotor.setSpeedReference(CANJaguar.SpeedReference.kQuadEncoder);
            topHandMotor.setPositionReference(CANJaguar.PositionReference.kQuadEncoder);            
            bottomHandMotor = new CANJaguar(RobotMap.bottomHandMotor);
            bottomHandMotor.configEncoderCodesPerRev(360);  
            bottomHandMotor.setSpeedReference(CANJaguar.SpeedReference.kQuadEncoder);
            bottomHandMotor.setPositionReference(CANJaguar.PositionReference.kQuadEncoder);
            rangeSensor = new AnalogChannel(1);        
            begin = false;
            encoderRange = 40.0;
            forwardStopPoint = 300;
            reverseStopPoint = 10;
        }
        catch (CANTimeoutException ex) {
            ;
        }
    }
    

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new HandsGroupChangeHands());        
    }    
    public void extendHand(int Hand){
        if (begin) {
            try {                
                if (Hand == RobotMap.topHandMotor) {                
                    topHandMotor.changeControlMode(CANJaguar.ControlMode.kPosition);
                    topHandMotor.enableControl(0.0);
                    topHandMotor.changeControlMode(CANJaguar.ControlMode.kPercentVbus);
                    topHandMotor.setX(0.2);
                    while (true) {                    
                        if (topHandMotor.getPosition()*360 > forwardStopPoint) {
                            break;
                        }
                    }
                    topHandMotor.disableControl();
                }
                else if (Hand == RobotMap.bottomHandMotor) {
                    bottomHandMotor.changeControlMode(CANJaguar.ControlMode.kPosition);
                    bottomHandMotor.enableControl(0.0);
                    bottomHandMotor.changeControlMode(CANJaguar.ControlMode.kPercentVbus);
                    bottomHandMotor.setX(0.2);
                    while (true) {
                        if (bottomHandMotor.getPosition()*360 > forwardStopPoint) {
                            break;
                        }
                    }
                    bottomHandMotor.disableControl();
                } 

            }

            catch (CANTimeoutException Exception) {
                ;
            }
        }
        
    }    
    public void retractHand(int Hand){
        if (begin) {
            try {                
                if (Hand == RobotMap.topHandMotor) {
                    topHandMotor.setX(-0.2);
                    while (true) {
                        if (topHandMotor.getPosition()*360 < reverseStopPoint) {
                            break;
                        }
                    }
                    topHandMotor.disableControl();
                }
                else if (Hand == RobotMap.bottomHandMotor) {
                    bottomHandMotor.setX(-0.2);
                    while (true) {
                        if (bottomHandMotor.getPosition()*360 < reverseStopPoint) {
                            break;
                        }
                    }
                    bottomHandMotor.disableControl();
                }
            }
            catch (CANTimeoutException Exception) {
                ;
            }
        }
    }        
    public void checkChangeHands() {          
        range = rangeSensor.getVoltage()*1000/9.8;  
        //9.8mV/in 
        if (range <= encoderRange) { // sensor staring into space inside pyramid, then registers crossbar
            begin = true;
        }         
        if (range > encoderRange) {            
            begin = false;
        }
    }      
}
