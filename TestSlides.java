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

public class TestSlides extends LinearOpMode{
  //Outtake Variables
  private DcMotor outtakeM1;
  private DcMotor outtakeM2;
  
  private double RAISE_MAX = -1750;
  private double RAISE_MIN = 10;
  
  
  private int groundLevelVal = -10;
  private int firstLevelVal = -600;
  private int secondLevelVal = -1050;
  private int thirdLevelVal = -1600;
  

  @Override
  public void runOpMode(){
    // Outttake
    outtakeM1 = hardwareMap.dcMotor.get("motor1");
    outtakeM2 = hardwareMap.dcMotor.get("motor2");
    
    outtakeM2.setDirection(DcMotor.Direction.REVERSE);
    
    waitForStart();


    while(opModeIsActive()){
      if(outtakeM1.getCurrentPosition() >= RAISE_MAX && outtakeM1.getCurrentPosition() <= RAISE_MIN 
      && outtakeM2.getCurrentPosition() >= RAISE_MAX && outtakeM2.getCurrentPosition() <= RAISE_MIN ){
        if(gamepad2.b){
          outtakeMove(groundLevelVal);
        }
        if(gamepad2.a){
          outtakeMove(firstLevelVal);
        }
        if(gamepad2.x){
          outtakeMove(secondLevelVal);
        }
        if(gamepad2.y){
          outtakeMove(thirdLevelVal);
        }
      }
    }
    
  }


  public void outtakeMove(int move){
    outtakeM1.setTargetPosition(move);
    outtakeM2.setTargetPosition(move);
    
    outtakeM1.setPower(0.3);
    outtakeM2.setPower(0.3);
    outtakeM1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    outtakeM2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    /**
     *
    while(opModeIsActive() && outtakeM.isBusy());{
      leftFront.setPower(0);
      leftRear.setPower(0);
      rightFront.setPower(0);
      rightRear.setPower(0);
      
    } 
    */
  }
}


