/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Global;
import frc.robot.PIDCalculator;
import frc.robot.subsystems.Hook;

public class HookPID extends CommandBase {
  /**
   * Creates a new starting_infront_port.
   */
  private double error;
  private double current;
  private double setpoint;
  private PIDCalculator hookPID;
  private Hook m_hook;


  public HookPID(double setpoint, Hook hook) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_hook = hook;
    addRequirements(hook);
    this.setpoint = setpoint; //Ticks should be a global constant - MAKE THAT CHANGE
    m_hook.HookSpeed(0); 
    //why does this need to be static in this instance but it doesn't need to be in tankdrive command

    hookPID = new PIDCalculator(Global.HOOK_P, 0, 0);
    
  }

  

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    current = m_hook.getCurrent();
    error = setpoint - current;
    
    double currentOutput = hookPID.getOutput(current);
    
    SmartDashboard.putNumber("Current Error", current);
    
    m_hook.HookSpeed(0.5*currentOutput);
    

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_hook.HookSpeed(0);
    //ahhhhhhhhhhhhhhhhhhhhhhhh
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(error) <= Global.HOOK_TOLERANCE;
  }
}
