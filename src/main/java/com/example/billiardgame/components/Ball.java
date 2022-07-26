package com.example.billiardgame.components;

import com.example.billiardgame.Vector2d;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.Paint;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Sphere;

public class Ball extends Sphere {
    // baksh physic barkhord toop az stackOverflow copy shode ~_~
    public Vector2d velocity;
    public Vector2d position;
    public int number;

    public Ball(double centerX, double centerY,double radius, Paint fill) {
        super(radius);
        this.setTranslateX(centerX);
        this.setTranslateY(centerY);
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.AQUA);
        this.velocity = new Vector2d(0.6f, 2);
        this.position = new Vector2d((float) centerX, (float) centerY);
    }

//    public Ball(double centerX, double centerY, float radius, Paint fill) {
//        super(centerX, centerY, radius, fill);
//        this.velocity = new Vector2d(-2, -2);
//        this.position = new Vector2d((float) centerX, (float) centerY);
//    }

    public boolean colliding(Ball ball)
    {
        return ball.getBoundsInParent().intersects(this.getBoundsInParent());
    }
    public void resolveCollision(Ball ball)
    {

        // get the mtd
        Vector2d delta = (position.subtract(ball.position));
        float r = (float) (getRadius() + ball.getRadius());
        float dist2 = delta.dot(delta);

        if (dist2 > r*r) return; // they aren't colliding


        float d = delta.getLength();

        Vector2d mtd;
        if (d != 0.0f)
        {
            mtd = delta.multiply((float) (((getRadius() + ball.getRadius())-d)/d)); // minimum translation distance to push balls apart after intersecting

        }
        else // Special case. Balls are exactly on top of eachother.  Don't want to divide by zero.
        {
            d = (float) (ball.getRadius() + getRadius() - 1.0f);
            delta = new Vector2d((float) (ball.getRadius() + getRadius()), 0.0f);

            mtd = delta.multiply((float) (((getRadius() + ball.getRadius())-d)/d));
        }



        // push-pull them apart
        position = position.add(mtd.multiply(1 / (2)));
        ball.position = ball.position.subtract(mtd.multiply(1 / (2)));

        // impact speed
        Vector2d v = (this.velocity.subtract(ball.velocity));
        float vn = v.dot(mtd.normalize());

        // sphere intersecting but moving away from each other already
        if (vn > 0.0f) return;

        // collision impulse
        float i = (-(1.0f + 0.7f) * vn) / (2);
        Vector2d impulse = mtd.multiply(i);

        // change in momentum
        this.velocity = this.velocity.add(impulse.multiply(1));
        ball.velocity = ball.velocity.subtract(impulse.multiply(1));
    }

    public int compareTo(Ball ball) {
        if (this.position.getX() - this.getRadius() > ball.position.getX() - ball.getRadius())
        {
            return 1;
        }
        else if (this.position.getX() - this.getRadius() < ball.position.getX() - ball.getRadius())
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }
    public void move(){
        this.position = this.position.add(this.velocity);
        this.setTranslateX(this.position.getY());
        this.setTranslateY(this.position.getY());
        velocity = velocity.multiply(0.999f);
        if (velocity.getLength() < 0.25) {
                velocity = velocity.multiply(0.95f);
            if (velocity.getLength() < 0.000001f){
                velocity.setX(0);
                velocity.setY(0);
            }
        }

    }
}
