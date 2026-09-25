package frc.robot.subsystems.Intake;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  private TalonFX deployMotor;
  private TalonFX intakeMotor1;
  private TalonFX intakeMotor2;
  private IntakeIO intakeIO;
  public IntakeInputsAutoLogged intakeInputs;

  public Intake(IntakeIO intakeIO) {

    this.intakeIO = intakeIO;
    intakeInputs = new IntakeInputsAutoLogged();
  }

  public void setDeployVoltage(double voltage) {
    deployMotor.setVoltage(voltage);
  }

  public void StopDeploy() {
    deployMotor.setVoltage(0);
  }

  public void setIntakeVoltage(double voltage) {
    intakeMotor1.setVoltage(voltage);
    intakeMotor2.setVoltage(voltage);
  }

  public void StopIntake() {
    intakeMotor1.setVoltage(0);
    intakeMotor2.setVoltage(0);
  }

  @Override
  public void periodic() {
    intakeIO.updateInputs(intakeInputs);
    Logger.processInputs("Intake", intakeInputs);
  }
}
