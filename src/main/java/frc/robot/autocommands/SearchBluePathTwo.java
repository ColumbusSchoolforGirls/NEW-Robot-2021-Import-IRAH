// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autocommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.ticks;
import frc.robot.commands.ConveyorManual;
import frc.robot.commands.ResetGyro;
import frc.robot.subsystems.ConveyorMotors;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class SearchBluePathTwo extends SequentialCommandGroup {
  /** Creates a new SearchBluePathTwo. */
  private final DriveTrain m_drivetrain;
  private final ConveyorMotors m_conveyor;
  //private final ticks m_ticks;

  public SearchBluePathTwo(DriveTrain drivetrain, ConveyorMotors conveyor) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    m_drivetrain = drivetrain;
    m_conveyor = conveyor;
    //m_ticks = ticks;
    addCommands(
      new straightforward(ticks.calculateTicks(180), m_drivetrain, false),
      new ConveyorManual(1, m_conveyor, true),
      new ResetGyro(m_drivetrain),
      new Turn(45, m_drivetrain),

      new straightforward(ticks.calculateTicks(84.853), m_drivetrain, false),
      new ConveyorManual(1, m_conveyor, true),
      new ResetGyro(m_drivetrain),
      new Turn(-45, m_drivetrain),

      new straightforward(ticks.calculateTicks(180), m_drivetrain, false),
      new ConveyorManual(1, m_conveyor, true),
      new ResetGyro(m_drivetrain),
      new Turn(135, m_drivetrain),
      new straightforward(ticks.calculateTicks(60), m_drivetrain, false)
      );
  }
}
