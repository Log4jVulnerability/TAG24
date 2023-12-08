// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.Drivebase;
import frc.robot.subsystems.PistonGroup;
import frc.robot.commands.ArcadeDrive;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    public final static XboxController xbox0 = new XboxController(0);
    public final static Drivebase m_drivebase = new Drivebase();
    public final static PistonGroup m_pistons0 = new Pistons(Constants.BUTTON_PISTONS);
    public final static PistonGroup m_pistons1 = new Pistons(Constants.LEVEL_PISTONS);
  
    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();
        ArcadeDrive m_arcadeDrive = new ArcadeDrive(m_drivebase);
        m_drivebase.setDefaultCommand(m_arcadeDrive);
    }

    public static XboxController getXbox0() {return xbox0;}
    public static double getDriveRightTrigger() {return getXbox0().getRightTriggerAxis();}
    public static double getDriveLeftTrigger() {return getXbox0().getLeftTriggerAxis();}
    public static double getDriveSteer() {return getXbox0().getLeftX();}

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        xbox1.x().onTrue(new InstantCommand(m_pistons0::out));
        xbox1.a().onTrue(new InstantCommand(m_pistons0::in));
        xbox1.y().onTrue(new InstantCommand(m_pistons1::out));
        xbox1.b().onTrue(new InstantCommand(m_pistons1::in));
    }
}
