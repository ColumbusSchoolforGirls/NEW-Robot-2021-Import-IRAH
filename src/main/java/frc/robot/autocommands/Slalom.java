// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autocommands;

import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.ticks;
import frc.robot.commands.ResetGyro;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Slalom extends SequentialCommandGroup {
  /** Creates a new Slalom. */
  private DriveTrain m_drivetrain;
  //private ticks m_ticks;

  public Slalom(DriveTrain drivetrain) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    m_drivetrain = drivetrain;
    //m_ticks = ticks;
    m_drivetrain.setAuto();

    addCommands(
      //STARTS IN CENTER BACK OF BOX... bumpers at back
      //don't use calculate ticks it no work
      new ResetGyro(m_drivetrain),
      new straightforward(35, m_drivetrain, false),
      //new ResetGyro(m_drivetrain),
      new Turn(55, m_drivetrain, 1),
      new WaitCommand(0.2),
      //new ResetGyro(m_drivetrain),
      new straightforward(50, m_drivetrain, false),
      new ResetGyro(m_drivetrain),
      new Turn(-60, m_drivetrain, 1),
      new WaitCommand(0.25),
      new ResetGyro(m_drivetrain),
      new straightforward(95, m_drivetrain, false),
      //new ResetGyro(m_drivetrain),
      new Turn(-60, m_drivetrain, 1),
      new WaitCommand(0.2),
      new ResetGyro(m_drivetrain),
      new straightforward(25, m_drivetrain, false),
      //new ResetGyro(m_drivetrain),
      new barrelTurn(280, m_drivetrain, 2.87),
      new WaitCommand(0.25),
      new ResetGyro(m_drivetrain),
      new straightforward(35, m_drivetrain, false),
      //new ResetGyro(m_drivetrain),
      new Turn(-55, m_drivetrain, 1),
      new WaitCommand(0.2),
      new ResetGyro(m_drivetrain),
      new straightforward(85, m_drivetrain, false),
      new Turn(-55, m_drivetrain, 1),
      new ResetGyro(m_drivetrain),
      new straightforward(45, m_drivetrain, false)

    );
  }
}
