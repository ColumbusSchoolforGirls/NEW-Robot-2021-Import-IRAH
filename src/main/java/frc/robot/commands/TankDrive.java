/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Global;
//import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * An example command.  You can replace me with your own command.
 */
public class TankDrive extends CommandBase {

  private final DriveTrain m_drivetrain;

  public TankDrive(DriveTrain drivetrain) {
    // Uses add requirements to bring the subsystem in
    m_drivetrain = drivetrain; 
    addRequirements(m_drivetrain);
    //System.out.print("hello tank drive");
    
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    // sets wheelspeed to 0 when the robot turns on
    //m_drivetrain.Wheelspeed(0.0005, 0.0005);
    m_drivetrain.setTeleop();

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    // sets wheelspeed when joysticks are moved forward/backward/up/down
    if (Math.abs(RobotContainer.driveCont.getRawAxis(5)) <= Global.DEADZONE
        && Math.abs(RobotContainer.driveCont.getRawAxis(1)) <= Global.DEADZONE) {
      m_drivetrain.Wheelspeed(0.0005, 0.0005);
    } else if (Math.abs(RobotContainer.driveCont.getRawAxis(1)) <= Global.DEADZONE
        && Math.abs(RobotContainer.driveCont.getRawAxis(5)) > Global.DEADZONE) {
      m_drivetrain.Wheelspeed(0, -0.5*RobotContainer.driveCont.getRawAxis(5));
    } else if (Math.abs(RobotContainer.driveCont.getRawAxis(1)) > Global.DEADZONE
        && Math.abs(RobotContainer.driveCont.getRawAxis(5)) <= Global.DEADZONE) {
        m_drivetrain.Wheelspeed(-0.5*RobotContainer.driveCont.getRawAxis(1), 0);
    } else {
      //this should still work, has to negative for some reason?
      m_drivetrain.Wheelspeed(-0.5*RobotContainer.driveCont.getRawAxis(1), -0.5*RobotContainer.driveCont.getRawAxis(5));
    }
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
public void end(boolean interrupted) {
    //sets wheelspeed to 0 when the robot is turned off
    m_drivetrain.Wheelspeed(0.0005,0.0005);
    interrupted = false;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  /*@Override
  protected void interrupted() {
    //sets wheelspeed to 0 when the robot is estopped
    DriveTrain.Wheelspeed(0,0);
  }*/
}
