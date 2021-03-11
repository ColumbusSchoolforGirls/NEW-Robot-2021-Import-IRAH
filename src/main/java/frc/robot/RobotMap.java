/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  //codes the motors and motor ports
  //this might be what made it work even though it was backward? (ports reversed? how did we set them?)
  public static int right_front_motor = 1;
  public static int left_front_motor = 2;
  public static int right_back_motor = 3;
  public static int left_back_motor = 4; 

  public static int talon = 9;
  

  public static int lift_talon = 7;
  public static int hook_talon = 8;

  public static int spin_talon = 0;

  public static int flap_solenoid_a = 0;
  public static int flap_solenoid_b = 1;

  public static int piston_solenoid_a = 2;
  public static int piston_solenoid_b = 3;

  public static int spin_encoder_a = 0;
  public static int spin_encoder_b = 1;

  public static int hook_pneumatic_a = 4;
  public static int hook_pneumatic_b = 5;
  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
