/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants.DriveConstants;

public class AutoDrive extends CommandBase {
  private double distance;
  private double speed;
  private double currentHeading;
  /**
   * Creates a new AutoDrive.
   */
  public AutoDrive(double getDistance, double getSpeed) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_drivetrain);
    distance = getDistance;
    speed = getSpeed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.m_drivetrain.resetEncoders();
    currentHeading = RobotContainer.m_drivetrain.getCurrentAngle();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(distance > 0){
      //drive forward
      double pTerm = DriveConstants.kDriveTrainGain * (currentHeading - RobotContainer.m_drivetrain.getCurrentAngle());
      RobotContainer.m_drivetrain.tankDrive(speed - pTerm, speed + pTerm);
    } else {
      //drive reverse
      double pTerm = DriveConstants.kDriveTrainGain * (currentHeading - RobotContainer.m_drivetrain.getCurrentAngle());
      RobotContainer.m_drivetrain.tankDrive(-speed + pTerm, -speed - pTerm);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.m_drivetrain.tankDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(distance > 0){
      //forward
      return RobotContainer.m_drivetrain.getAverageDistance() > distance;
    } else {
      //reverse
      return RobotContainer.m_drivetrain.getAverageDistance() < distance;
    }
  }
}
