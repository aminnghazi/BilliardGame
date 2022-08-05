package com.example.billiardgame;

import com.example.billiardgame.Transitions.MyAnimation;
import com.example.billiardgame.components.Ball;
import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.stage.Screen;
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
    private static boolean steady = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane pane= FXMLLoader.load(getClass().getResource("fxml/game-view.fxml"));
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        primaryStage.setMaximized(true);
        pane.requestFocus();
    }

    private void createBalls() {
        int k = 0;
        for (int i = 1; i <= 4; i++) {
            for (int j = 0; j <i; j++) {
                k++;
                String url = k + ".png";
                Image image = new Image(new File(url).toURI().toString());
                ImagePattern pattern = new ImagePattern(image);
                Ball ball = new Ball(pane.getLayoutX() + 600+40*i,
                        pane.getLayoutY()+300-20*i + 42*j, 15,pattern, k);
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
        }
        catch (Exception e){
            System.out.println("background picture not found !!!");
        }
         wallLeft = 80 + (int)pane.getLayoutX();
         wallRight = 900 + (int)pane.getLayoutX();
         wallUp = 80 + (int)pane.getLayoutY();
         wallDown = 475 + (int)pane.getLayoutY();
        createBalls();
    }



    public static void handleMoves(){
        if (!steady) {
            steady = true;
            for (int i = 0; i < balls.size(); i++) {
                for (int j = i + 1; j < balls.size(); j++) {
                    if (balls.get(i).colliding(balls.get(j)))
                        balls.get(i).resolveCollision(balls.get(j));
//                System.out.println("coll");
                }
                Ball ball = balls.get(i);
                if (ball.velocity.getLength() > 0.0001f)
                    steady = false;
                ball.move();
                wallCollision(ball);
            }
            if (steady)
                System.out.println("staedy");
        }
    }


    public static void wallCollision(Ball ball){
        if (ball.getCenterX() > wallRight)
            ball.velocity.setX(ball.velocity.getX() * -0.8f);
        if (ball.getCenterX() < wallLeft)
            ball.velocity.setX(ball.velocity.getX() * -0.8f);
        if (ball.getCenterY() < wallUp)
            ball.velocity.setY(ball.velocity.getY() * -0.8f);
        if (ball.getCenterY() > wallDown)
            ball.velocity.setY(ball.velocity.getY() * -0.8f);
    }


}
