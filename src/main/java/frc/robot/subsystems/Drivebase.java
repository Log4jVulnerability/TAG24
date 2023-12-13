// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;

public class Drivebase extends SubsystemBase {
    final CANSparkMax frontLeftMotor;
    final CANSparkMax rearLeftMotor;
    final CANSparkMax frontRightMotor;
    final CANSparkMax rearRightMotor;
    final MotorControllerGroup m_left;
    final MotorControllerGroup m_right;
    final DifferentialDrive m_myRobot;

    /** Creates a new Drivebase. */
    public Drivebase() {
        frontLeftMotor = new CANSparkMax(Constants.frontLeftID, MotorType.kBrushless);
        rearLeftMotor = new CANSparkMax(Constants.rearLeftID, MotorType.kBrushless);
        m_left = new MotorControllerGroup(frontLeftMotor, rearLeftMotor);

        frontRightMotor = new CANSparkMax(Constants.frontRightID, MotorType.kBrushless);
        rearRightMotor = new CANSparkMax(Constants.rearRightID, MotorType.kBrushless);
        frontRightMotor.setInverted(true);
        rearRightMotor.setInverted(true);
        m_right = new MotorControllerGroup(frontRightMotor, rearRightMotor);

        m_myRobot = new DifferentialDrive(m_left, m_right);
    }

    public void drive(double robotOutput, double turnAmount) {
        m_myRobot.arcadeDrive(robotOutput * Constants.SPEED_SCALING, -turnAmount * Constants.TURN_SCALING);
    }

    public void stopMotors() {
        m_myRobot.arcadeDrive(0.0, 0.0);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }
}