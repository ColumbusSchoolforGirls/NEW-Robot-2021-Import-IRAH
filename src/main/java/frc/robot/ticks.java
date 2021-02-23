// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
//hey dummy this is this for when you forget it--devin
//method for encoder ticks and distance and stuff :/

//42 encoder ticks = 1 revolution
//circumfrence of the wheel is 4pi

package frc.robot;

/** Add your docs here. */
public class ticks {

    private double circumfrence = 4*Math.PI;
    private double distance;

    // public ticks(double d){
    //     distance = d;
    // }

<<<<<<< HEAD
    public double calculateTicks(double distanceInches){
        return (distance*451/circumfrence);
=======
    public static double calculateTicks(double distanceInches){
        //return distance*451/circumfrence;
        return 5.0;
>>>>>>> 7e6aeca8c9472879764556495bdcf04449b84d80
    }

}
