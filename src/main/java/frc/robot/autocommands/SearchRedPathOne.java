// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autocommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.ticks;
import frc.robot.commands.ConveyorManual;
import frc.robot.commands.ResetGyro;
import frc.robot.subsystems.ConveyorMotors;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Limelight;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class SearchRedPathOne extends SequentialCommandGroup {
  /** Creates a new BouncyBoy. */

  private DriveTrain m_drivetrain;
  private ConveyorMotors m_conveyormotors;
  private Limelight m_limelight;
  //private ticks m_ticks;

  public SearchRedPathOne(DriveTrain drivetrain, ConveyorMotors conveyormotors, Limelight limelight) {
    m_drivetrain = drivetrain;
    m_limelight = limelight;
    m_conveyormotors = conveyormotors;
    //m_ticks = tick;
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ResetGyro(m_drivetrain),
      new ParallelRaceGroup(
        new ConveyorManual(0.5, m_conveyormotors, true).withTimeout(5),
        new straightforward(50, m_drivetrain, false)
      ),
      //take this out when we have pnumatics
      new ConveyorManual(-0.5, m_conveyormotors, true).withTimeout(0.5),
      new Turn(-30, m_drivetrain).withTimeout(2),
      new Tracking(m_limelight, m_drivetrain),
      new ParallelCommandGroup(
        new straightforward(10, m_drivetrain, false),
        new ConveyorManual(0.5, m_conveyormotors, true).withTimeout(1)
      ),
      new Turn(45, m_drivetrain).withTimeout(2),
      new Tracking(m_limelight, m_drivetrain),
      new ParallelCommandGroup(
        new straightforward(10, m_drivetrain, false),
        new ConveyorManual(0.5, m_conveyormotors, true).withTimeout(1)
      ),
      new Turn(-10, m_drivetrain),
      new straightforward(85, m_drivetrain, false)
      //new ParallelCommandGroup(
      //   new straightforward(15, m_drivetrain, false),
      //   new ConveyorManual(1, m_conveyormotors, true).withTimeout(1)
      // )
      // new straightforward(ticks.calculateTicks(90), m_drivetrain, false),
      // new ConveyorManual(1, m_conveyormotors, true).withTimeout(5),
      // new straightforward(ticks.calculateTicks(5), m_drivetrain, false),
      // new ResetGyro(m_drivetrain),
      // new Turn(-26.5, m_drivetrain),

      // new straightforward(ticks.calculateTicks(90), m_drivetrain, false),
      // new ConveyorManual(1, m_conveyormotors, true).withTimeout(5),
      // new straightforward(ticks.calculateTicks(5), m_drivetrain, false),
      // new ResetGyro(m_drivetrain),
      // new Turn(71, m_drivetrain),

      // new straightforward(ticks.calculateTicks(90), m_drivetrain, false),
      // new ConveyorManual(1, m_conveyormotors, true).withTimeout(5),
      // new straightforward(ticks.calculateTicks(5), m_drivetrain, false),
      // new ResetGyro(m_drivetrain),
      // new Turn(45.5, m_drivetrain),

      // new straightforward(ticks.calculateTicks(90), m_drivetrain, false)
           
    );
  }
}
