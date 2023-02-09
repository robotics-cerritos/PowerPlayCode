package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

//@TeleOp


public class TestDrive extends LinearOpMode {

  // Motor Variables
  private DcMotor leftFront;
  private DcMotor rightFront;
  private DcMotor leftRear;
  private DcMotor rightRear;

  // Default Speed
  //private double speed = 1;


  @Override
  public void runOpMode() {
    // HardwareMapping goes here
    // Wheels
    leftFront = hardwareMap.dcMotor.get("leftF");
    rightFront = hardwareMap.dcMotor.get("rightF");
    leftRear = hardwareMap.dcMotor.get("leftR");
    rightRear = hardwareMap.dcMotor.get("rightR");

    // Reversing direction goes here

    rightFront.setDirection(DcMotor.Direction.REVERSE);
    rightRear.setDirection(DcMotor.Direction.REVERSE);

    waitForStart();

    while (opModeIsActive()) {

      // x & y (strafing and forwards/backwards) are from left stick
      double x = -gamepad1.left_stick_x;
      double y = gamepad1.left_stick_y;
      // rotation is from right stick
      double rotation = -gamepad1.right_stick_x;

      // Double array of wheel speeds
      double wheelSpeeds[] = new double[4];
      wheelSpeeds[0] = x + y + rotation;
      wheelSpeeds[1] = -x + y - rotation;
      wheelSpeeds[2] = -x + y + rotation;
      wheelSpeeds[3] = x + y - rotation;
      normalize(wheelSpeeds);

      // setting the speed of motors
      
      // Changing wheel speeds
      leftFront.setPower(0.5*wheelSpeeds[0]);
      rightFront.setPower(0.5*wheelSpeeds[1]);
      leftRear.setPower(0.5*wheelSpeeds[2]);
      rightRear.setPower(0.5*wheelSpeeds[3]);

    }

  }

  private void normalize(double[] wheelSpeeds) {
    // Find the maximum wheel speed
    double maxMagnitude = Math.abs(wheelSpeeds[0]);
    for (int i = 1; i < wheelSpeeds.length; i++) {
      double magnitude = Math.abs(wheelSpeeds[i]);
      if (magnitude > maxMagnitude) {

        maxMagnitude = magnitude;
      }
    }
    // If the maximum wheel speed is greater than 1
    // then divide all the wheel speeds by the maximum wheel speed value
    if (maxMagnitude > 1.0) {
      for (int i = 0; i < wheelSpeeds.length; i++) {
        wheelSpeeds[i] /= maxMagnitude;
      }
    }
  } // normalize

}
