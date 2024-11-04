package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;



public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(400);
        autonPoints autoPoints = new autonPoints();


        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(autoPoints.startBluePark)
                //Go score specimen
                .splineToConstantHeading(autoPoints.blueRungMidpoint.component1(), autoPoints.startBluePark.component2())
                .waitSeconds(.5)
                .strafeToConstantHeading(new Vector2d(autoPoints.blueRungMidpoint.component1().x, autoPoints.blueScore.component1().y))
                .strafeToConstantHeading(autoPoints.blueParkTile.component1())
                        .turnTo(0)
                .waitSeconds(.5)
                .strafeTo(autoPoints.blueParkTile.component1().plus(new Vector2d(0, -48)))
                .strafeTo(autoPoints.bluePark.component1().plus(new Vector2d(0, -48)))
                .strafeTo(autoPoints.bluePark.component1())
                .waitSeconds(.5)
                .strafeTo(autoPoints.bluePark.component1().plus(new Vector2d(0, -48)))
                .strafeTo(autoPoints.bluePark.component1().plus(new Vector2d(-8, -48)))
                .strafeTo(autoPoints.bluePark.component1().plus(new Vector2d(-8, 0)))
                .waitSeconds(.5)
                .strafeTo(autoPoints.bluePark.component1().plus(new Vector2d(-8, -48)))
                .strafeTo(autoPoints.bluePark.component1().plus(new Vector2d(-12, -48)))
                .strafeTo(autoPoints.bluePark.component1().plus(new Vector2d(-12, 0)))
                .waitSeconds(.5)
                .strafeTo(autoPoints.bluePark.component1().plus(new Vector2d(0, -24)))
                .waitSeconds(1)
                .strafeTo(autoPoints.bluePark.component1())
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)

                .start();
    }
}