package frc.robot.subsystems.indexer;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.config.SparkFlexConfig;

public class IndexerIOSparkFlex implements IndexerIO {
    private final SparkFlex leftRollerMotor;
    private final SparkFlex rightMotorRoller;

    private final SparkFlexConfig leftRollerConfig;
    private final SparkFlexConfig rightRollerConfig;

}
