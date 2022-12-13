package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="SamplePowerPlayAuto", group="Linear Opmode")

public class SamplePowerPlayAuto extends LinearOpMode{
  private ElapsedTime runtime = new ElapsedTime();

  private DcMotor leftFront;
  private DcMotor rightFront;
  private DcMotor leftRear;
  private DcMotor rightRear;

  @Override
  public void runOpMode(){
    leftFront = hardwareMap.dcMotor.get("leftF");
    rightFront = hardwareMap.dcMotor.get("rightF");
    leftRear = hardwareMap.dcMotor.get("leftR");
    rightRear = hardwareMap.dcMotor.get("rightR");

    rightFront.setDirection(DcMotor.Direction.REVERSE);
    rightRear.setDirection(DcMotor.Direction.REVERSE);
    

    waitForStart();

    runtime.reset();

    //Plan: strafe left 
    while(opModeIsActive() && (runtime.seconds()<=1.35)){

      allPower(0.5);
    }
    runtime.reset();

    while(opModeIsActive() && (runtime.seconds()<=0.3)){
      allPower(0);
    }
    runtime.reset();
    
    
  }


  public void allPower(double power){
    leftFront.setPower(power);
    leftRear.setPower(power);
    rightFront.setPower(power);
    rightRear.setPower(power);
  }

  public void strafe(double power){
    leftFront.setPower(power);
    leftRear.setPower(-power);
    rightFront.setPower(-power);
    rightRear.setPower(power);
  }

  public void turn(double power){
    leftFront.setPower(power);
    leftRear.setPower(power);
    rightFront.setPower(-power);
    rightRear.setPower(-power);
  }
}
