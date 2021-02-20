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
public class SearchBluePathOne extends SequentialCommandGroup {

  private final DriveTrain m_drivetrain;  
  private final ConveyorMotors m_conveyor;
  private final ticks m_ticks;

  /** Creates a new SearchBluePathOne. */
  public SearchBluePathOne(DriveTrain drivetrain, ConveyorMotors conveyor, ticks ticks) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    m_drivetrain = drivetrain;
    m_conveyor = conveyor;
    m_ticks = ticks;
    addCommands(
      new straightforward(ticks.calculateTicks(180), m_drivetrain, false),
      new ConveyorManual(1, m_conveyor, true),
      new ResetGyro(m_drivetrain),
      new Turn(18.434, m_drivetrain),

      new straightforward(ticks.calculateTicks(95.254), m_drivetrain, false),
      new ConveyorManual(1, m_conveyor, true),
      new ResetGyro(m_drivetrain),
      new Turn(26.565, m_drivetrain),
      
      new straightforward(ticks.calculateTicks(94.868), m_drivetrain, false),
      new ConveyorManual(1, m_conveyor, true),
      new ResetGyro(m_drivetrain),
      new Turn(153.434, m_drivetrain),

      new straightforward(ticks.calculateTicks(90), m_drivetrain, false)

    );
  }
}
