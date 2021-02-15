/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;


/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class ConveyorMotors extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  // this is setting the variable talon to the talons that are on the robot 
  private static final TalonSRX talon = new TalonSRX(RobotMap.talon);

  public ConveyorMotors(){
  }

  public static TalonSRX getTalon() {
    return talon;
  }

   // sets speed of each talon/cim
   public void Motorspeed(final double speed) {
    talon.set(ControlMode.PercentOutput, -speed);
  }

  //public void initDefaultCommand(){
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    //we don't use this...
  //}


    
    
}
