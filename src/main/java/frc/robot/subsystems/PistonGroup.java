package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class PistonGroup extends SubsystemBase {
		final DoubleSolenoid[] pistons;

		public PistonGroup(int[][] pistons) {
				this.pistons = new DoubleSolenoid[pistons.length];
				for (int i = 0; i < pistons.length; i++) {this.pistons[i] = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, pistons[i][0], pistons[i][1]);}
		}
		
		@Override
		public void periodic() {}

		public void out() {for (DoubleSolenoid piston : pistons) {piston.set(Value.kForward);}}
		public void in() {for (DoubleSolenoid piston : pistons) {piston.set(Value.kReverse);}}
}
