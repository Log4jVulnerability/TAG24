// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.recordplayback.RecordPlaybackSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.Drivebase;
import frc.robot.subsystems.Winch;
import frc.robot.subsystems.PistonGroup;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.WinchController;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    private final RecordPlaybackSubsystem recordPlaybackSubsystem = new RecordPlaybackSubsystem();
    public final static XboxController xbox0 = new XboxController(0);
    public final static XboxController xbox1 = new XboxController(1);
    public final static Drivebase m_drivebase = new Drivebase();
    public final static Winch m_winch = new Winch();
    public final static PistonGroup m_pistons0 = new Pistons(Constants.BUTTON_PISTONS);
    public final static PistonGroup m_pistons1 = new Pistons(Constants.LEVEL_PISTONS);
  
    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();
        ArcadeDrive m_arcadeDrive = new ArcadeDrive(m_drivebase);
        WinchController m_winchController = new WinchController(m_winch);
        m_drivebase.setDefaultCommand(m_arcadeDrive);
        m_winch.setDefualtCommand(m_winchController);
    }

    public static XboxController getXbox0() {return xbox0;}
    public static XboxController getXbox1() {return xbox1;}
    public static double getDriveRightTrigger() {return xbox0.getRightTriggerAxis();}
    public static double getDriveLeftTrigger() {return xbox0.getLeftTriggerAxis();}
    public static double getDriveSteer() {return xbox0.getLeftX();}
    public static double getCopilotRightTrigger() {return xbox1.getRightTriggerAxis();}
    public static double getCopilotLeftTrigger() {return xbox1.getLeftTriggerAxis();}

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
