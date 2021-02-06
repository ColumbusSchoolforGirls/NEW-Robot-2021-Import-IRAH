/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Global;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ConveyorMotors;



/**
 * An example command.  You can replace me with your own command.
 */
public class ConveyorManual extends CommandBase {
  private double speed;
  private ConveyorMotors m_conveyorMotors;
  private boolean auto;
  

  public ConveyorManual(double speed, ConveyorMotors conveyormotors, boolean auto) { 
    // Use addRequirements() to bring the conveyorMotors subsystem in
    m_conveyorMotors = conveyormotors;
    addRequirements(conveyormotors);
    this.speed=speed;
    this.auto = auto;

  }



  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    // sets motorspeed to 0 when it initially is turned on
    m_conveyorMotors.Motorspeed(0);

  }
  
  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    // runs motor while a button on second controller is being held
    if (!auto) {
      if (Math.abs(RobotContainer.auxCont.getRawAxis(1)) >= Global.DEADZONE){
        m_conveyorMotors.Motorspeed(-RobotContainer.auxCont.getRawAxis(1));
    } else {
      m_conveyorMotors.Motorspeed(0);
     }
    } else {
      m_conveyorMotors.Motorspeed(speed);
    }
      
        };
  
  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false;
  }
  
  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {
    // sets motor speed to 0 when the robot is turned off
    m_conveyorMotors.Motorspeed(0);
    interrupted = false;
    
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  /*Obsolete
  @Override
  protected void interrupted() {
    //sets motor speed to 0 when the robot is estopped
    ConveyorMotors.Motorspeed(0);
  }*/
}
