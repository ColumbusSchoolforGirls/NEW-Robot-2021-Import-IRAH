/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Global;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class FullSpeedAhead extends CommandBase {
  /**
   * Creates a new FullSpeedAhead.
   */
  private DriveTrain m_drivetrain;

  public FullSpeedAhead(DriveTrain drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrain = drivetrain;
    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled. q
  @Override
  public void initialize() {
    m_drivetrain.Wheelspeed(0,0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    /*if (Math.abs(RobotContainer.driveCont.getRawAxis(1)) <= Global.DEADZONE) {
      m_drivetrain.Wheelspeed(0, 0);
    }else{*/
      //should we be concerned about this? make sure to test it still works
      m_drivetrain.Wheelspeed(RobotContainer.driveCont.getRawAxis(5), RobotContainer.driveCont.getRawAxis(1));
    //}
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.Wheelspeed(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
