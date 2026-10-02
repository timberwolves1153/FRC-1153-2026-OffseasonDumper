package frc.robot.subsystems.Intake;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;

public class Intake extends SubsystemBase {
  public IntakeIO intakeIO;
  public IntakeInputsAutoLogged intakeInputs;

  public Intake(IntakeIO intakeIO) {

    this.intakeIO = intakeIO;
    intakeInputs = new IntakeInputsAutoLogged();
  }

  public void setDeployVoltage(double volts) {
    intakeIO.setDeployVoltage(volts);
  }

  public void setIntakeVoltage(double volts) {
    intakeIO.setIntakeVoltage(volts);
  }

  @Override
  public void periodic() {
    intakeIO.updateInputs(intakeInputs);
    Logger.processInputs("Intake", intakeInputs);
  }
}
