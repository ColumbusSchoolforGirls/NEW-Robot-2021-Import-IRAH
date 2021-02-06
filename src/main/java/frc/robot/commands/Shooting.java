/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.ConveyorMotors;
import frc.robot.subsystems.Flap;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Shooting extends SequentialCommandGroup {
  /**
   * Creates a new Shooting.
   */
  private ConveyorMotors m_conveyormotors;
  private Flap m_flap;
  public Shooting(ConveyorMotors conveyormotors, Flap flap) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    m_conveyormotors = conveyormotors;
    m_flap = flap;

    addRequirements(conveyormotors);
    addRequirements(flap);

    addCommands(
      /*new ConveyorManual(1, m_conveyormotors),
      new SequentialCommandGroup(new WaitCommand(0.5), new FlapManual(m_flap, true))*/

      //when the button is pushed nothing happens, it used to open the flap but not it does nothing
      //this does nothing?
      //new ConveyorManual(1, m_conveyormotors).withTimeout(1),

      new ParallelCommandGroup(
        //new ConveyorManual(1, m_conveyormotors),
        new FlapManual(m_flap, true, false)
      )
    );
      
    
  }
}
