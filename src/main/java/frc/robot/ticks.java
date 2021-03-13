// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
//hey dummy this is this for when you forget it--devin
//method for encoder ticks and distance and stuff :/

//42 encoder ticks = 1 revolution
//circumfrence of the wheel is 4pi

package frc.robot;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** Add your docs here. */
public class ticks {

    //circumfrence of the wheels (number should be diameter of wheel)
    private static double circumfrence = 6 * Math.PI;
    private static double distance; //the distance you want to go

    // public ticks(double d){
    // distance = d;
    // }

    //should return the number of ticks needed to go specified distance (in inches)
    public static double calculateTicks(double distanceInches) {
        //System.out.println(calculateTicks(10));

        //451 ticks per wheel rotation?
        return (distance*(451/circumfrence));
    }

}
