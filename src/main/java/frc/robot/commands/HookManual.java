/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
//import frc.robot.RobotContainer;
//import frc.robot.Robot;
import frc.robot.subsystems.Hook;

public class HookManual extends CommandBase {
  
  private Hook m_hook;
  
  private double speed;

  

  /**
   * Creates a new HookManual.
   */
  public HookManual(double speed, Hook hook) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_hook = hook;
    addRequirements(hook);
    this.speed = speed;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_hook.HookSpeed(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  // if the encoder is equal to the setpoint, then the motor stops
  // if the encoder is greater than the setpoint, it backdrives
  // otherwise, the hook moves at half speed
  /*if (Math.abs(RobotContainer.auxCont.getRawAxis(5)) >= Global.DEADZONE){
    m_hook.HookSpeed(RobotContainer.auxCont.getRawAxis(5));
  } else {
    m_hook.HookSpeed(0);
  }
  SmartDashboard.putNumber("Hook Number", RobotContainer.auxCont.getRawAxis(5));*/
    m_hook.HookSpeed(speed);
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //is this possible to allow for the hook to go backwards
    m_hook.HookSpeed(0);
    interrupted=false;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
