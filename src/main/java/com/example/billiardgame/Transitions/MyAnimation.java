package com.example.billiardgame.Transitions;

import com.example.billiardgame.GameView;
import com.example.billiardgame.Vector2d;
import com.example.billiardgame.components.Ball;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Material;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Iterator;

public class MyAnimation extends Transition {
    ArrayList<Ball> balls;
    Pane pane;
    private static boolean isMoving;
    static Vector2d[] holes ={
            new Vector2d(600,90),
            new Vector2d(165,120),
            new Vector2d(1035,120),
            new Vector2d(1035,580),
            new Vector2d(165,580),
            new Vector2d(600,610),
    };

    public MyAnimation(ArrayList<Ball> balls, Pane pane) {
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
        this.balls = balls;
        this.pane = pane;
    }

    @Override
    protected void interpolate(double frac) {
        handleMoves();
    }



    public void handleMoves(){
        isMoving = false;
        for (Ball ball : balls) {
            if (ball.velocity.getLength() != 0 && !isMoving)
                isMoving = true;
        }
        if (isMoving)
            for (int i = 0; i < balls.size(); i++) {
                for (int j = i+1; j < balls.size(); j++) {
                    if (balls.get(i).colliding( balls.get(j)))
                        balls.get(i).resolveCollision(balls.get(j));
                    //                cue.setRotate(i*5);
                }
                Ball ball = balls.get(i);
                ball.move();
                wallCollision(ball);
            }
        enterTheHoles();

    }

    private void enterTheHoles() {
        Iterator<Ball> iterator = balls.iterator();
        while (iterator.hasNext()){
            Ball temp = iterator.next();
            Vector2d hole = dropInHole(temp);
            if (hole != null) {
                iterator.remove();
                DropAnimation dropAnimation = new DropAnimation(temp, hole);
                dropAnimation.play();
            }
        }

    }
    private static Vector2d dropInHole(Ball ball){
        for (Vector2d hole : holes) {
            if (ball.position.getDistance(hole) < 25) {
                ball.velocity = ball.velocity.multiply(0);
                return hole;
            }
        }
        return null;
    }


    public static void wallCollision(Ball ball){
        double x = ball.getTranslateX();
        double y = ball.getTranslateY();
        if (x > GameView.wallRight && (y>116 && y< 540))
            ball.velocity.setX(ball.velocity.getX() * -0.8f);
        if (ball.getTranslateX() < GameView.wallLeft && (y>116 && y<540))
            ball.velocity.setX(ball.velocity.getX() * -0.8f);
        if (ball.getTranslateY() < GameView.wallUp &&(x>205 && x<1005 &&!(x>570&&x<630)))
            ball.velocity.setY(ball.velocity.getY() * -0.8f);
        if (ball.getTranslateY() < GameView.wallUp &&(x>205 && x<1005 &&!(x>570&&x<630)))
            ball.velocity.setY(ball.velocity.getY() * -0.8f);
    }
}