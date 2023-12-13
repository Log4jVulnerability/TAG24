// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static int frontLeftID = 13;
    public static int rearLeftID = 11;
    public static int frontRightID = 14;
    public static int rearRightID = 12;

    public static double SPEED_SCALING = 0.8;
    public static double TURN_SCALING = 0.6;

    // TODO change values
    public static int[][] BUTTON_PISTONS = {{0, 1}, {2, 3}};
    public static int[][] LEVEL_PISTONS = {{4, 5}, {6, 7}};

    public static int WINCH_ID = 0;
}
