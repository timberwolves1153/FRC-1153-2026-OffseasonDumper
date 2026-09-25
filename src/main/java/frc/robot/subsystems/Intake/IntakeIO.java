package frc.robot.subsystems.Intake;

import org.littletonrobotics.junction.AutoLog;

public interface IntakeIO {
  @AutoLog
  public static class IntakeInputs {
    public double deployVoltage = 0.0;
    public double deployCurrent = 0.0;
    public double intakeVoltage = 0.0;
    public double intakeCurrent = 0.0;
  }

  /** Run open loop at the specified voltage. */
  public default void setDeployVoltage(double volts) {}

  /** Stop in open loop. */
  public default void stopDeploy() {}

  public default void resetDeployEncoder() {}

  public default void setCollectVoltage(double volts) {}
  /** Stop in open loop. */
  public default void stopCollect() {}

  public default void updateInputs(IntakeInputs inputs) {}
}
