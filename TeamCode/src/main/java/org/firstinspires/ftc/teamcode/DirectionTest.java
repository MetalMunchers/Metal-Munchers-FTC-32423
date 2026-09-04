package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;


@TeleOp(name="DirectionTest", group="Linear OpMode")
public class DirectionTest extends LinearOpMode{
    DcMotorEx frontLeft;
    DcMotorEx frontRight;
    DcMotorEx backLeft;
    DcMotorEx backRight;

    int selected = 0;
    boolean lastState = false;
    boolean state = false;

    @Override
    public void runOpMode() {
        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");

        frontLeft.setDirection(DcMotorEx.Direction.FORWARD);
        frontRight.setDirection(DcMotorEx.Direction.REVERSE);
        backLeft.setDirection(DcMotorEx.Direction.FORWARD);
        backRight.setDirection(DcMotorEx.Direction.REVERSE);
        
        frontLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        frontLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();
        while(opModeIsActive()){
            frontRight.setVelocity(2800);
            frontLeft.setVelocity(2800);
            backRight.setVelocity(2800);
            backLeft.setVelocity(2800);


            telemetry.addData("selected Motor", selected);
            telemetry.addData("frontLeft T/s: ", frontLeft.getVelocity());
            telemetry.addData("frontRight T/s: ", frontRight.getVelocity());
            telemetry.addData("backLeft T/s: ", backLeft.getVelocity());
            telemetry.addData("backRight T/s: ", backRight.getVelocity());
            telemetry.update();
        }
    }
}
