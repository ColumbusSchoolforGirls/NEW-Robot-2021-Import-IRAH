/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
//import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.autocommands.NothingTest;
import frc.robot.commands.TankDrive;
/*import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.TankDrive;
import frc.robot.subsystems.ConveyorMotors;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Hook;*/
/*import frc.robot.autocommands.GTFO;
import frc.robot.autocommands.firstPlanAutoMiddle;
import frc.robot.autocommands.leftStartAutoMiddle;
import frc.robot.autocommands.middleStartAutoMiddle;
import frc.robot.subsystems.ControlSpinner;
import frc.robot.subsystems.ConveyorMotors;*/
import frc.robot.subsystems.DriveTrain;
//import frc.robot.subsystems.Flap;
//import frc.robot.subsystems.Hook;
import frc.robot.subsystems.Limelight;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /*public static DriveTrain driveTrain = new DriveTrain();
  public static RobotContainer m_oi;
  public static ConveyorMotors conveyorMotors = new ConveyorMotors();
  public static Hook hook = new Hook();

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();
  //can we delete this stuff ^

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  Command m_autonomousCommand;  //just makes a new command object? to represnt whichever autopath we choose to run?

  //creates instaances of all the important subsystems so we can use their methods and run things later
  private RobotContainer m_robotContainer;
  //public Hook m_hook = new Hook();
  //public ControlSpinner m_controlspinner = new ControlSpinner();
  public Limelight m_limelight = new Limelight();
  

  @Override
  //run once when the robot is turned on
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    CameraServer.getInstance().startAutomaticCapture();
    
    //
    /*m_oi = new RobotContainer();
    m_chooser.setDefaultOption("Default Auto", new TankDrive());
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);*/
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  //updates the values (specified in each subsystem) on smartdashboard (numbers and things)
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    m_robotContainer.driveTrain.update();
    //m_hook.update();
    //m_controlspinner.update();
    m_limelight.update();
  }



  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */

   //ig we don't use this rn, runs when robot is first disabled
  @Override
  public void disabledInit() {
    
  }

  //I assume this runs periodically while the robot is disabled
  @Override
  public void disabledPeriodic() {
   // CommandScheduler.getInstance().disable();
   }


  /**
   * This autonomous \/ (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. (This next bit doesn't really apply to us)
   * If you prefer the LabVIEW Dashboard, remove all of the chooser code 
   * and uncomment the getString code to get the auto name from the 
   * text box below the Gyro
   *
   * You can add additional auto modes by adding additional commands to the
   * chooser code in ROBOTCONTAINER (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */

  @Override
  //sets m_autocom thing to dashboard selection then schedules it
  public void autonomousInit() {
    //just calls getAutonomousCommand from robo container
    //sets m_autonomousCommand to whichever path is selected on dashboard

    //I added this here but might be wrong, should just set drivetrain to brake mode
    m_robotContainer.driveTrain.setAuto();

    m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     * 
     * I think this is just how to set the command when nothing is chosen on dahsboard?
     * but we should always default to selecting nothing (see robo container)
     */

    //schedule the autonomous command chosen on dashboard
    //like... adding it to the run queue (as long as it's actually set to something)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
   
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    //if (m_autonomousCommand != null) {
      //m_autonomousCommand.cancel();
     
    //apparently we did want that? 

    //should set into coast mode
    m_robotContainer.driveTrain.setTeleop();
    }
  

  /**
   * This function is called periodically during teleop
   */
  @Override
  //makes it listen to the controllers in teleop?
  public void teleopPeriodic() {
    CommandScheduler.getInstance().run();
    //CommandScheduler.schedule(TankDrive(Robot.Container.TankDrive));
    //double left_command = Joystick.getRawAxis(5);
    //double right_command = Joystick.getRawAxis(1);
  }}
    
