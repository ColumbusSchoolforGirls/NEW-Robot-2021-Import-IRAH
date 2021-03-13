/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autocommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ConveyorManual;
//import frc.robot.commands.FlapManual;
import frc.robot.commands.ResetGyro;
import frc.robot.subsystems.ConveyorMotors;
import frc.robot.subsystems.DriveTrain;
//import frc.robot.subsystems.Flap;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class middleStartAutoMiddle extends SequentialCommandGroup {
  /**
   * Creates a new secondStartAutoMiddle.
   */
  private DriveTrain m_drivetrain;
  private ConveyorMotors m_conveyormotors;
  //private Flap m_flap;

  public middleStartAutoMiddle(DriveTrain drivetrain, ConveyorMotors conveyormotors) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    m_drivetrain = drivetrain;
    m_conveyormotors = conveyormotors;
    //m_flap = flap;
    //add gyro reset after turn
    addCommands(
      new straightforward(1000, m_drivetrain, false),
      new ResetGyro(m_drivetrain),
      new Turn(90, m_drivetrain), 
      new straightforward(1000, m_drivetrain, false),
      new ResetGyro(m_drivetrain),
      new Turn(-90, m_drivetrain), 
      new straightforward(1000, m_drivetrain, false),
      //new FlapManual(m_flap, true, false),
      new ConveyorManual(1, m_conveyormotors, true)
    );

  }
}
