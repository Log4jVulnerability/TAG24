// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.recordplayback.RecordPlaybackSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.Drivebase;
import frc.robot.subsystems.Winch;
import frc.robot.subsystems.ButtonPiston;
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
//    public final static XboxController xbox1 = new XboxController(1);
    public final static Drivebase m_drivebase = new Drivebase();
    public final static Winch m_winch = new Winch();
    public final static ButtonPiston m_buttonpistons = new ButtonPiston();
  
    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();
        ArcadeDrive m_arcadeDrive = new ArcadeDrive(m_drivebase);
        m_drivebase.setDefaultCommand(m_arcadeDrive);
    }

    public static XboxController getXbox0() {return xbox0;}
//    public static XboxController getXbox1() {return xbox1;}
    public static double getDriveRightTrigger() {return xbox0.getRightTriggerAxis();}
    public static double getDriveLeftTrigger() {return xbox0.getLeftTriggerAxis();}
    public static double getDriveSteer() {return xbox0.getLeftX();}
    public static boolean getCopilotStart() {return xbox0.getStartButton();}

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        new Trigger(xbox0::getXButton).onTrue(new InstantCommand(m_buttonpistons::out));
        new Trigger(xbox0::getAButton).onTrue(new InstantCommand(m_buttonpistons::in));
        new Trigger(xbox0::getStartButton).whileTrue(new WinchController(m_winch, false));
        new Trigger(xbox0::getBackButton).whileTrue(new WinchController(m_winch, true));
    }
}
