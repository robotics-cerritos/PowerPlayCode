package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

//@Autonomous(name="BlueAuto", group="Linear Opmode")

public class BlueAuto extends LinearOpMode{
  private ElapsedTime runtime = new ElapsedTime();
  //WHEELS
  private DcMotorEx leftFront;
  private DcMotorEx rightFront;
  private DcMotorEx leftRear;
  private DcMotorEx rightRear;
  
  //TURNTABLE
  private DcMotor turntableMotor;
  
   //Servo Variables
  private Servo claw;
  
  //SLIDES
  private DcMotor motor1;
  private DcMotor motor2;


  //X and Y Power of Robot Chasis Movement Motors
  private double x=0;
  private double y=0;
  

  @Override
  public void runOpMode(){
    leftFront = hardwareMap.get(DcMotorEx.class, "leftF");
    rightFront = hardwareMap.get(DcMotorEx.class, "rightF");
    leftRear = hardwareMap.get(DcMotorEx.class, "leftR");
    rightRear = hardwareMap.get(DcMotorEx.class, "rightR");
  
    rightFront.setDirection(DcMotor.Direction.REVERSE);
    rightRear.setDirection(DcMotor.Direction.REVERSE);
    // Claws
    claw = hardwareMap.servo.get("claw");
    
    
    //Test Motor
    turntableMotor = hardwareMap.dcMotor.get("testMotor");
    
     //Slides
    motor1 = hardwareMap.dcMotor.get("rightSlide");
    motor2 = hardwareMap.dcMotor.get("leftSlide");
    
    
    
    motor1.setDirection(DcMotor.Direction.REVERSE);
    waitForStart();

    runtime.reset();

    

    while(opModeIsActive()&&(runtime.seconds()<=0.5)){
      claw.setPosition(0.0);
      
    }
    strafe(0.6,2.8);
    allPower(0.2,0.5);
    while(opModeIsActive()&&(runtime.seconds()<=0.5)){
      claw.setPosition(0.0);
      
    }
    
  }
  
  public void pause(double time){
    while(opModeIsActive()&&(runtime.seconds()<=time)){
      leftFront.setPower(0.0);
    }
  }
  
  public void moveSlides(double directionPower, double time){
    while(opModeIsActive() && (runtime.seconds()<=time)){
      motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
      motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
      if(runtime.seconds()<=(0.25)*time){
        motor1.setPower(directionPower);
        motor2.setPower(directionPower);
        
      }
      if(runtime.seconds()>(0.25)*time){
        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor1.setPower(0.0);
        motor2.setPower(0.0);
        claw.setPosition(0.6);
      }
      
    }
    runtime.reset();
    
  }

  public void allPower(double power, double time){
    while(opModeIsActive() && (runtime.seconds()<=time)){
      leftFront.setPower(power);
      leftRear.setPower(power);
      rightFront.setPower(power);
      rightRear.setPower(power);
    }
    runtime.reset();
    
  }

  public void strafe(double power, double time){
    while(opModeIsActive() && (runtime.seconds()<=time)){
      leftFront.setPower(power);
      leftRear.setPower(-power);
      rightFront.setPower(-power);
      rightRear.setPower(power);  
    }  
    runtime.reset();
  }

  public void turn(double power, double time){
    while(opModeIsActive() && (runtime.seconds()<=time)){
      leftFront.setPower(power);
      leftRear.setPower(power);
      rightFront.setPower(-power);
      rightRear.setPower(-power);
    }
    runtime.reset();
  } 
  public void stopMovement(){
    while(opModeIsActive() && (runtime.seconds()<=0.1)){
      leftFront.setPower(0.0);
      leftRear.setPower(0.0);
      rightFront.setPower(0.0);
      rightRear.setPower(0.0);
    }
    runtime.reset();
  } 
}
