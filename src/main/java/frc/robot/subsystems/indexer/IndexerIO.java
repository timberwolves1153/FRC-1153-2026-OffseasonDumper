package frc.robot.subsystems.indexer;

import org.littletonrobotics.junction.AutoLog;

public interface IndexerIO {
    @AutoLog
    public static class IndexerIOInputs {
        public double leftRollerVoltage = 0.0;
        public double leftRollerCurrent = 0.0;
        public double leftRollerTemp = 0.0;

        public double rightRollerVoltage = 0.0;
        public double rightRollerCurrent = 0.0;
        public double rightRollerTemp = 0.0;
    }

    public default void updateInputs(IndexerIOInputs inputs) {}

    public default void setVoltageRoller(double volts) {}

    public default void stopRoller() {}

}
