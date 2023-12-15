package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Winch;
import frc.robot.RobotContainer;

public class WinchController extends CommandBase {
    private final Winch m_winch;
    final boolean reverse;

    public WinchController(Winch subsystem, boolean reversed) {
        m_winch = subsystem;
        reverse = reversed;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {m_winch.run(reverse);}

    @Override
    public void end(boolean interrupted) {m_winch.stop();}

    @Override
    public boolean isFinished() {return false;}
}
