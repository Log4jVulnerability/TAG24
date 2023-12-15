package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Constants;

public class ButtonPiston extends SubsystemBase {
		final DoubleSolenoid piston;

		public ButtonPiston() {
				piston = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.BUTTON_PISTON_IN, Constants.BUTTON_PISTON_OUT);;
		}
		
		@Override
		public void periodic() {}

		public void out() {piston.set(Value.kForward);}
		public void in() {piston.set(Value.kReverse);}
}
