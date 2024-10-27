package org.firstinspires.ftc.teamcode.auton;


import com.acmerobotics.roadrunner.Pose2d;

public class autonPoints {

    /*


    Making this so we can just call all the points we need
    hopefully we get some use out of this
    I'm just going to set Pose for now, with just x, y according to the
    Pedro Pathing Simulator at https://pedro-path-generator-itd.vercel.app/

    The buckets, on the left side, will be called Score
    And the parking zone will be called Park
    These don't describe actions happening within the program,
    just the location of the start.

    For the specimen locations, I'm going to set them to the point
    on which the specimen rests. In auton, we can just add/subtract the offset on the path
    Other note, the specimen starting inline with the tape changing slope will be called left

    For the yellow specimens on the field, left will represent closest to the wall


     */


    public Pose2d startRedScore = new Pose2d(-60, -60, Math.toRadians(-90));
    public Pose2d startBlueScore = new Pose2d(60, 60, Math.toRadians(90));

    public Pose2d startRedPark = new Pose2d(-60, 12, Math.toRadians(-90));
    public Pose2d startBluePark = new Pose2d(60, -12, Math.toRadians(90));

    public Pose2d leftRedSpecimen = new Pose2d(-50, 50, 0);
    public Pose2d middleRedSpecimen = new Pose2d(-50, 60, 0);
    public Pose2d rightRedSpecimen = new Pose2d(-50, 70, 0);

    public Pose2d leftBlueSpecimen = new Pose2d(50, -50, 0);
    public Pose2d middleBlueSpecimen = new Pose2d(50, -60, 0);
    public Pose2d rightBlueSpecimen = new Pose2d(50, -70, 0);

    public Pose2d leftYellowSpecimenR = new Pose2d(-26, -70, 0);
    public Pose2d middleYellowSpecimenR = new Pose2d(-26, -58, 0);
    public Pose2d rightYellowSpecimenR = new Pose2d(-26, -46, 0);

    public Pose2d leftYellowSpecimenB = new Pose2d(26, 70, 0);
    public Pose2d middleYellowSpecimenB = new Pose2d(26, 58, 0);
    public Pose2d rightYellowSpecimenB = new Pose2d(26, 46, 0);

    public Pose2d redSubmersibleMidpoint = new Pose2d(0, -16, 0);
    public Pose2d blueSubmersibleMidpoint = new Pose2d(0, 16, 0);

    public Pose2d redRungMidpoint = new Pose2d(-24, 0, 0);
    public Pose2d blueRungMidpoint = new Pose2d(24, 0, 0);

    public Pose2d redScore = new Pose2d(-48, -48, Math.toRadians(45));
    public Pose2d blueScore = new Pose2d(48, 48, Math.toRadians(-45));

    public Pose2d redPark = new Pose2d(-60,48, Math.toRadians(-90));
    public Pose2d bluePark = new Pose2d(60, -48, Math.toRadians(90));











}
