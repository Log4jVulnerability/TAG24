package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Winch;
import frc.robot.RobotContainer;

public class WinchController extends CommandBase {
    private final Winch m_winch;

    public WinchController(Winch subsystem) {
        m_winch = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {m_winch.run(RobotContainer.getCopilotRightTrigger() - RobotContainer.getCopilotLeftTrigger());}

    @Override
    public void end(boolean interrupted) {m_winch.stop();}

    @Override
    public boolean isFinished() {return false;}
}
