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

        myBot.runAction(myBot.getDrive().actionBuilder(autoPoints.startRedScore)
                //Gets in position to score red Specimen on high bar, this goess to the midpoint but we can offset the x
                .splineToConstantHeading(autoPoints.redRungMidpoint.component1(), autoPoints.startRedScore.component2())
                .waitSeconds(.5)
                //Navigates to the red score zone
                .strafeToConstantHeading(new Vector2d(autoPoints.redRungMidpoint.component1().x, autoPoints.redScore.component1().y))
                .strafeToConstantHeading(autoPoints.redScore.component1())
                .waitSeconds(.5)
                //Grab the right yellow and then score in the high basket
                .turnTo(autoPoints.redScore.component2())
                .waitSeconds(.5)
                //Grab the middle yellow and then score in the high basket
                .turnTo(autoPoints.middleYellowSpecimenR.component2())
                .waitSeconds(.5)
                .turnTo(autoPoints.redScore.component2())
                .waitSeconds(.5)
                //Grab the left yellow and then score in the high basket
                .turnTo(autoPoints.rightYellowSpecimenR.component2())
                .waitSeconds(.5)
                .turnTo(autoPoints.redScore.component2())
                .waitSeconds(.5)
                .splineToLinearHeading(autoPoints.redSubmersibleMidpoint, autoPoints.redSubmersibleMidpoint.component2())
                .turnTo(Math.toRadians(180))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)

                .start();
    }
}