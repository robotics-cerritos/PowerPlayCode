/*
 * Copyright (c) 2021 OpenFTC Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

import java.util.ArrayList;

import java.lang.Math;

//@TeleOp
public class AprilTagExample extends LinearOpMode
{
    //WHEELS
    private DcMotorEx leftFront;
    private DcMotorEx rightFront;
    private DcMotorEx leftRear;
    private DcMotorEx rightRear;
      
    OpenCvCamera camera;
    AprilTagDetectionPipeline aprilTagDetectionPipeline;

    static final double FEET_PER_METER = 3.28084;

    // Lens intrinsics
    // UNITS ARE PIXELS
    // NOTE: this calibration is for the C920 webcam at 800x448.
    // You will need to do your own calibration for other configurations!
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;

    // UNITS ARE METERS
    double tagsize = 0.166;

    //Tag ID's on Sleeve
    int left = 1;
    int middle = 2;
    int right = 3;
    

    AprilTagDetection tagOfInterest = null;

    @Override
    public void runOpMode()
    {   
        leftFront = hardwareMap.get(DcMotorEx.class, "leftF");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightF");
        leftRear = hardwareMap.get(DcMotorEx.class, "leftR");
        rightRear = hardwareMap.get(DcMotorEx.class, "rightR");
        
        
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);

        camera.setPipeline(aprilTagDetectionPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(800,448, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {

            }
        });

        telemetry.setMsTransmissionInterval(50);

        /*
         * The INIT-loop:
         * This REPLACES waitForStart!
         */
        while (!isStarted() && !isStopRequested())
        {
            ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();

            if(currentDetections.size() != 0)
            {
                boolean tagFound = false;

                for(AprilTagDetection tag : currentDetections)
                {
                    if(tag.id == left || tag.id == middle || tag.id == right)
                    {
                        tagOfInterest = tag;
                        tagFound = true;
                        break;
                    }
                }

                if(tagFound)
                {
                    telemetry.addLine("Tag of interest is in sight!\n\nLocation data:");
                    tagToTelemetry(tagOfInterest);
                }
                else
                {
                    telemetry.addLine("Don't see tag of interest :(");

                    if(tagOfInterest == null)
                    {
                        telemetry.addLine("(The tag has never been seen)");
                    }
                    else
                    {
                        telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                        tagToTelemetry(tagOfInterest);
                    }
                }

            }
            else
            {
                telemetry.addLine("Don't see tag of interest :(");

                if(tagOfInterest == null)
                {
                    telemetry.addLine("(The tag has never been seen)");
                }
                else
                {
                    telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                    tagToTelemetry(tagOfInterest);
                }

            }

            telemetry.update();
            sleep(20);
        }

        /*
         * The START command just came in: now work off the latest snapshot acquired
         * during the init loop.
         */
         
         boolean hasMoved = false; 
        while(opModeIsActive()){
          
        
        /* Update the telemetry */
        if(tagOfInterest != null)
        {
            telemetry.addLine("Tag snapshot:\n");
            tagToTelemetry(tagOfInterest);
            telemetry.update();
        }
        else
        {
            telemetry.addLine("No tag snapshot available, it was never sighted during the init loop :(");
            telemetry.update();
        }

        //Auto Code Goes Here
        if(!hasMoved&&(tagOfInterest == null || tagOfInterest.id == middle)){
          forwardsBackwards(1300);
          if(Math.abs(leftFront.getCurrentPosition()-1300)<10){
            resetEncoders();
            hasMoved = true; 
          }
          
        }else if(!hasMoved&&tagOfInterest.id == left){
          forwardsBackwards(1200);
          if(Math.abs(leftFront.getCurrentPosition()-1200)<10){
            resetEncoders();
            strafe(-1300);
            hasMoved = true;
          }
          
        }else if(!hasMoved&&tagOfInterest.id == right){
          forwardsBackwards(1200);
          if(Math.abs(leftFront.getCurrentPosition()-1200)<10){
            resetEncoders();
            strafe(1300);
            hasMoved = true; 
          }
         
        }
        
        telemetry.addData("encoder position: ", leftRear.getCurrentPosition());
        telemetry.update();
      }

    }

    void tagToTelemetry(AprilTagDetection detection)
    {
        telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
        telemetry.addLine(String.format("tag of Interest:", tagOfInterest.id));
        telemetry.addLine(String.format("Translation X: %.2f feet", detection.pose.x*FEET_PER_METER));
        telemetry.addLine(String.format("Translation Y: %.2f feet", detection.pose.y*FEET_PER_METER));
        telemetry.addLine(String.format("Translation Z: %.2f feet", detection.pose.z*FEET_PER_METER));
        telemetry.addLine(String.format("Rotation Yaw: %.2f degrees", Math.toDegrees(detection.pose.yaw)));
        telemetry.addLine(String.format("Rotation Pitch: %.2f degrees", Math.toDegrees(detection.pose.pitch)));
        telemetry.addLine(String.format("Rotation Roll: %.2f degrees", Math.toDegrees(detection.pose.roll)));
    }
    
    public void forwardsBackwards(int encoderVal){
      leftFront.setTargetPosition(encoderVal);
      leftRear.setTargetPosition(encoderVal);
      rightFront.setTargetPosition(-encoderVal);
      rightRear.setTargetPosition(-encoderVal);
      
      leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      
      leftFront.setPower(0.2);
      leftRear.setPower(0.2);
      rightFront.setPower(0.2);
      rightRear.setPower(0.2);
      
    }
    
    public void strafe(int encoderVal){
        leftFront.setTargetPosition(encoderVal);
        leftRear.setTargetPosition(-encoderVal);
        rightFront.setTargetPosition(encoderVal);
        rightRear.setTargetPosition(-encoderVal);
        
    leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      
      leftFront.setPower(0.2);
      leftRear.setPower(0.2);
      rightFront.setPower(0.2);
      rightRear.setPower(0.2);
      
      
    }
    
    public void resetEncoders(){
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    
    
}
