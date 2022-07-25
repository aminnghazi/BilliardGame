package com.example.billiardgame.Transitions;

import com.example.billiardgame.GameView;
import com.example.billiardgame.components.Ball;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.scene.paint.Material;
import javafx.util.Duration;

public class MyAnimation extends Transition {

    public MyAnimation() {
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double frac) {
        GameView.handleMoves();
    }
}