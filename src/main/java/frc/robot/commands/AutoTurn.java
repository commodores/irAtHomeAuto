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

public class AutoTurn extends CommandBase {
  private double degrees;
  private double currentHeading;
  /**
   * Creates a new AutoTurn.
   */
  public AutoTurn(double getDegrees) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_drivetrain);
    degrees = getDegrees;
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
    if(degrees > 0){
      RobotContainer.m_drivetrain.tankDrive(DriveConstants.kAutoSpeed, -DriveConstants.kAutoSpeed);
    } else {
      RobotContainer.m_drivetrain.tankDrive(-DriveConstants.kAutoSpeed, DriveConstants.kAutoSpeed);
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
    if(degrees > 0){
      //turn right
      return currentHeading - RobotContainer.m_drivetrain.getCurrentAngle() > degrees;
    } else {
      //turn left
      return currentHeading - RobotContainer.m_drivetrain.getCurrentAngle() < degrees;
    }
  }
}
