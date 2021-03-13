/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autocommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Global;
import frc.robot.PIDCalculator;
import frc.robot.subsystems.DriveTrain;

public class barrelTurn extends CommandBase {
  /**
   * Creates a new starting_infront_port.
   */
  private double angle; 
  private DriveTrain m_drivetrain;
  private double ratio = 2.15;
  //left -- true, right -- false

  

  public barrelTurn(double angle,  DriveTrain drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrain = drivetrain;
    this.angle = angle;
    addRequirements(drivetrain);
 //Ticks should be a global constant - MAKE THAT CHANGE
     
    //why does this need to be static in this instance but it doesn't need to be in tankdrive command
  }

  

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drivetrain.resetEncoders();
    m_drivetrain.resetGyro();
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // if angle negative, turn right
    // if angle positive, turn left
    // for < 0, -0.3, -0.1
    // for > 0, -0.1, -0.3
    if (angle < 0){
      m_drivetrain.Wheelspeed(-0.1*ratio, -0.1);
    } else if (angle > 0){
      m_drivetrain.Wheelspeed(-0.1, -0.1*ratio);
    } else {
      m_drivetrain.Wheelspeed(0, 0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.Wheelspeed(0,0);
    //ahhhhhhhhhhhhhhhhhhhhhhhh
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(m_drivetrain.getFacingAngle()) >= Math.abs(angle) ;
  }
}
