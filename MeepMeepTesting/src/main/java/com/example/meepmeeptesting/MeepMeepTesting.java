package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;



public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);
        autonPoints autoPoints = new autonPoints();


        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(autoPoints.startRedScore)
                .splineToConstantHeading(autoPoints.leftRedSpecimen.component1(), autoPoints.leftRedSpecimen.component2())
                .waitSeconds(.5)
                .strafeToLinearHeading(autoPoints.redScore.component1(), autoPoints.redScore.component2())
                .waitSeconds(.5)
                .strafeToLinearHeading(autoPoints.middleRedSpecimen.component1(), autoPoints.middleRedSpecimen.component2())
                .waitSeconds(.5)
                .strafeToLinearHeading(autoPoints.redScore.component1(), autoPoints.redScore.component2())
                        .waitSeconds(.5)
                .turnTo(autoPoints.rightRedSpecimen.component2())
                .waitSeconds(.5)
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)

                .start();
    }
}