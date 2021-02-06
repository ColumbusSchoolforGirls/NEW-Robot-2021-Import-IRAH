/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;

public class RelayCompressor extends SubsystemBase {
  Compressor comp;
  Relay relay;
  DigitalInput digitalInput;

  /**
   * Creates a new RelayCompressor.
   */
  public RelayCompressor() {
    comp = new Compressor();
    relay = new Relay(0);
    digitalInput = new DigitalInput(3);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (!digitalInput.get()){
      relay.set(Value.kForward);
    } else {
      relay.set(Value.kOff);
    }
  }
}
