package com.example.billiardgame;

import com.example.billiardgame.Transitions.MyAnimation;
import com.example.billiardgame.components.Ball;
import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class GameView extends Application {

    public Pane pane;
    @FXML
    ImageView backGround;
    @FXML
    Label pos;
    public static ArrayList<Ball> balls = new ArrayList<>();
    public static int wallLeft = 180;
    public static int wallRight = 1020;
    public static int wallUp = 130;
    public static int wallDown = 570;
    public static Ball whiteBall;
    public static Line line;
    Color[] colors = {
            Color.YELLOW,
            Color.RED,
            Color.AQUA,
            Color.GRAY,
            Color.BLACK,
            Color.GREEN,
            Color.BROWN,
            Color.PURPLE,
            Color.ORANGE,
            Color.OLIVE
    };

//    static Rectangle cue;
    @Override
    public void start(Stage primaryStage) throws Exception {
         pane = FXMLLoader.load(getClass().getResource("fxml/game-view.fxml"));
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        pane.requestFocus();

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


    private void createBalls() {
        whiteBall = new Ball(wallLeft + 120, (wallDown - wallUp)/2 + wallUp, 15,Color.WHITE);
        balls.add(whiteBall);
        pane.getChildren().add(whiteBall);
        int k = 0;
        for (int i = 1; i <= 4; i++) {
            for (int j = 0; j <i; j++) {
                Ball ball = new Ball(720+40*i, (wallDown - wallUp)/2 + wallUp + 10 -20*i + 42*j, 15, colors[k]);
                pane.getChildren().add(ball);
                balls.add(ball);
                k++;
            }
        }
        line = new Line(whiteBall.getTranslateX(),whiteBall.getTranslateY(),whiteBall.getTranslateX()+200, whiteBall.getTranslateY());
        line.getStrokeDashArray().addAll(2d,10d);
//        pane.getChildren().add(line);
        MyAnimation animation = new MyAnimation(balls,pane);
        animation.play();
    }





    public void onMousePressed(MouseEvent mouseEvent) {
        line.setEndX(mouseEvent.getX());
        line.setEndY(mouseEvent.getY());
    }

    public void onMouseDragged(MouseEvent mouseEvent) {
        line.setEndX(mouseEvent.getX());
        line.setEndY(mouseEvent.getY());
        pos.setText("x=" + mouseEvent.getX()+"y="+mouseEvent.getY());
    }
}
