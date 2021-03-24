/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class ResetGyro extends CommandBase {
  /**
   * Creates a new ResetGyro.
   */
  private DriveTrain m_drivetrain;
  public ResetGyro(DriveTrain drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrain = drivetrain;
    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drivetrain.resetGyro();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //m_drivetrain.resetGyro();
    //SmartDashboard.putNumber("yawValue", value)
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
   // m_drivetrain.resetGyro();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
      return true;
  }
}
