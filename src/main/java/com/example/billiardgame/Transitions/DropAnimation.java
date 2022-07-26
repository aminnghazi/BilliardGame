package com.example.billiardgame.Transitions;

import com.example.billiardgame.Vector2d;
import com.example.billiardgame.components.Ball;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.util.Duration;

public class DropAnimation extends Transition {
    Ball ball;
    Vector2d vector2d;
    double startX;
    double startY;
    public DropAnimation(Ball ball, Vector2d vector2d) {
        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(600));
        this.setInterpolator(Interpolator.EASE_IN);
        this.ball = ball;
        this.startX = ball.getTranslateX();
        this.startY = ball.getTranslateY();
        this.vector2d = vector2d;
    }

    @Override
    protected void interpolate(double frac) {
        ball.setTranslateX(startX + frac * -(ball.getTranslateX() - vector2d.getX()));
        ball.setTranslateY(startY + frac * -(ball.getTranslateY() - vector2d.getY()));

        ball.setScaleX(1-frac);
        ball.setScaleY(1-frac);
    }
}
