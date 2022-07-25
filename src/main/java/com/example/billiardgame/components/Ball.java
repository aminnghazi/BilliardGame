package com.example.billiardgame.components;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Sphere;

public class Ball extends Circle {
//    private double speed=5;
//    private double angle=45;
    double dy = 1;
    double dx = 0.5;

    public Ball(double centerX, double centerY, double radius, Paint fill) {
        super(centerX, centerY, radius, fill);
    }
//
//    public void updateSpeed(){
//        dy = -speed* Math.sin(Math.toRadians(angle));
//        dx = speed* Math.cos(Math.toRadians(angle));
//    }

//    public double getSpeed() {
//        return speed;
//    }
//
//    public void setSpeed(double speed) {
//        this.speed = speed;
//    }
//
//    public double getAngle() {
//        return angle;
//    }
//
//    public void setAngle(double angle) {
//        this.angle = angle;
//    }


    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void move(){
//        updateSpeed();
        this.setCenterY(this.getCenterY() + dy);
        this.setCenterX(this.getCenterX() + dx);
    }
}
