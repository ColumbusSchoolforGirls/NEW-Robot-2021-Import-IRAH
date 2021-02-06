/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Flap extends SubsystemBase {
  /**
   * Creates a new Flap.
   */
  // upper flap
  private final DoubleSolenoid flap = new DoubleSolenoid(RobotMap.flap_solenoid_a, RobotMap.flap_solenoid_b);
  // bottom piston
  private final DoubleSolenoid piston = new DoubleSolenoid(RobotMap.piston_solenoid_a, RobotMap.piston_solenoid_b);

  public Flap() {
  
  }

  public void Up(boolean open){
    if (open){
      flap.set(Value.kForward);
      piston.set(Value.kForward);
    } else {
      flap.set(Value.kReverse);
      piston.set(Value.kReverse);
    }
  }

 

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
