package frc.robot.subsystems.indexer;

import com.ctre.phoenix6.StatusSignal;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkFlexConfig;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.VoltageUnit;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Temperature;
import edu.wpi.first.units.measure.Voltage;

public class IndexerIOSparkFlex implements IndexerIO {
    private final SparkFlex leftRollerMotor = new SparkFlex(0, MotorType.kBrushless);
    private final SparkFlex rightRollerMotor = new SparkFlex(0, MotorType.kBrushless);

    private final SparkFlexConfig leftRollerConfig = new SparkFlexConfig();
    private final SparkFlexConfig rightRollerConfig = new SparkFlexConfig();

    private final double leftRollerVoltage = leftRollerMotor.getBusVoltage();
    private final double leftRollerCurrent = leftRollerMotor.getOutputCurrent();
    private final double leftRollerTemp = leftRollerMotor.getMotorTemperature();

    private final double rightRollerVoltage = rightRollerMotor.getBusVoltage();
    private final double rightRollerCurrent = rightRollerMotor.getOutputCurrent();
    private final double rightRollerTemp = rightRollerMotor.getMotorTemperature();

    public IndexerIOSparkFlex() {


    }
}
