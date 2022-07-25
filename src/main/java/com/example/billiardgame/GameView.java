package com.example.billiardgame;

import com.example.billiardgame.Transitions.MyAnimation;
import com.example.billiardgame.components.Ball;
import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class GameView extends Application {

    @FXML
    ImageView backGround;
    @FXML
    Pane pane;
    @FXML
    Label pos;
    public static ArrayList<Ball> balls = new ArrayList<>();
    private static int wallLeft = 80;
    private static int wallRight = 920;
    private static int wallUp = 80;
    private static int wallDown = 520;

    @Override
    public void start(Stage primaryStage) throws Exception {
        pane = FXMLLoader.load(getClass().getResource("fxml/game-view.fxml"));
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        pane.requestFocus();
    }

    private void createBalls() {
        Color c = Color.AQUA;
        for (int i = 1; i <= 4; i++) {
            for (int j = 0; j <i; j++) {
                Ball ball = new Ball(200+40*i, 300-20*i + 42*j, 15, c);
                pane.getChildren().add(ball);
                balls.add(ball);
            }
        }
        MyAnimation animation = new MyAnimation();
        animation.play();
    }

    public void initialize() {
        try {
            Image bg = new Image(new File("bg.png").toURI().toString());
            System.out.println(bg);
            backGround.setImage(bg);
            System.out.println(bg.getUrl());
        }
        catch (Exception e){
            System.out.println("background picture not found !!!");
        }
        createBalls();

    }



    public static void handleMoves(){

        for (int i = 0; i < balls.size(); i++) {
            for (int j = i+1; j < balls.size(); j++) {
              if (ballsCollision(balls.get(i), balls.get(j)))
                  resolveCollision();
            }
            Ball ball = balls.get(i);
            wallCollision(ball);
            ball.move();
        }

    }
    public static boolean ballsCollision(Ball ball1 , Ball ball2){
        return ball1.getBoundsInParent().intersects(ball2.getBoundsInParent());
    }
    public static void resolveCollision(){}

    public static void wallCollision(Ball ball){
        if (ball.getCenterX() > wallRight)
            ball.setDx(ball.getDx() * -1);
        if (ball.getCenterX() < wallLeft)
            ball.setDx(ball.getDx() * -1);
        if (ball.getCenterY() < wallUp)
            ball.setDy(ball.getDy() * -1);
        if (ball.getCenterY() > wallDown)
            ball.setDy(ball.getDy() * -1);    }


}
