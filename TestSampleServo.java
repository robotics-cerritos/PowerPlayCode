package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;


//@TeleOp

public class TestSampleServo extends LinearOpMode {

    private Servo servo;

    
    @Override
    public void runOpMode() {
        
        servo = hardwareMap.servo.get("servo");

        
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            if(gamepad1.dpad_up){
                servo.setPosition(servo.getPosition()+0.1);
            }
            if(gamepad1.dpad_down){
                servo.setPosition(servo.getPosition()-0.1);
            }
            telemetry.addData("Servo: ",servo.getPosition());
            telemetry.update();
            idle();
        }
    }
}
