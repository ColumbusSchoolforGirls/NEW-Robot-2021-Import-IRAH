// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autocommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.ticks;
import frc.robot.commands.ResetGyro;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class BouncyBoy extends SequentialCommandGroup {
  /** Creates a new BouncyBoy. */

  private DriveTrain m_drivetrain;
  private ticks m_ticks;

  public BouncyBoy(DriveTrain drivetrain) {
    m_drivetrain = drivetrain;
    //m_ticks = tick;
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());

    /***
     * HEY HEY FUTURE US HEY HEY HEY HEY
     * so... he was working ok but now he's skipping the boop forward (where it hits the cone)
     * and also sometimes he just freaks out and spins around idk what that's about
     * all the batteries suck...
     * and even when it was working, the backwards after the 20 degree turn just doesn't go
     * so yea... idk
    */

    addCommands(
      //starts in back of box
      new ResetGyro(m_drivetrain),
      new straightforward(35, m_drivetrain, false),
      //new ResetGyro(m_drivetrain),
      new Turn(90, m_drivetrain, 0.8),
      new ResetGyro(m_drivetrain),

      //first boop
      new straightforward(55, m_drivetrain, false).withTimeout(0.67),
      new WaitCommand(0.25),
      new straightforward(-20, m_drivetrain, false).withTimeout(0.77),
      new Turn(30, m_drivetrain, 1).withTimeout(1),
      new ResetGyro(m_drivetrain),

      // driving back and turning around the first cone
      new straightforward(-75, m_drivetrain, false),
      new ResetGyro(m_drivetrain),
      new Turn(-57, m_drivetrain, 1),
      new ResetGyro(m_drivetrain),
      new straightforward(40, m_drivetrain, false),
      new Turn(30, m_drivetrain, 1),

      // second boop
      new ResetGyro(m_drivetrain),
      new straightforward(87, m_drivetrain, false).withTimeout(0.73),
      new WaitCommand(0.5),
      new straightforward(-70, m_drivetrain, false),

      // turning around the two cones 
      new Turn(-80, m_drivetrain, 0.8),
      //new WaitCommand(0.25),
      new ResetGyro(m_drivetrain),
      new straightforward(60, m_drivetrain, false),
      new ResetGyro(m_drivetrain),
      new Turn(87, m_drivetrain, 0.8).withTimeout(1),
      //new WaitCommand(0.25),

    // third boop
    new ResetGyro(m_drivetrain),
    new straightforward(125, m_drivetrain, false).withTimeout(0.8),
    new WaitCommand(0.5),
    new straightforward(-45, m_drivetrain, false),
    new Turn(70, m_drivetrain, 1),
    new straightforward(-25, m_drivetrain,false)

      
    );
  }
}
