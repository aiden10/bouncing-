package com.example.ball;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class bouncing extends Application {
    @Override
    public void start(Stage stage) {
        System.out.println("Current Directory: " + System.getProperty("user.dir"));
        final Random random = new Random();
        double rectWidth = 20;
        double rectHeight = 20;
        double screenWidth = 500;
        double screenHeight = 500;
        final int[] bounces = {0};
        Pane pane = new Pane();
        pane.setBackground(new Background(new BackgroundFill(randomColor(), null, null)));

        Rectangle rect = new Rectangle(screenWidth/2, screenHeight/2, rectWidth, rectHeight);
        rect.setFill(randomColor());
        pane.getChildren().add(rect);
        final int[] moveX = {(int)(Math.random() * 10 + 5)};
        final int[] moveY = {(int)(Math.random() * 10 + 5)};

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.1), event -> {
                    if (rect.getX() >= screenWidth) {
                        pane.setBackground(new Background(new BackgroundFill(randomColor(), null, null)));
                        rect.setFill(randomColor());
                        if (random.nextBoolean()) {
                            moveX[0] = (int)(Math.random() * 10 + 5 + bounces[0]);
                        }
                        else{
                            moveX[0] = (int)(Math.random() * 10 + 5 + bounces[0]);
                        }
                        moveX[0] *= -1;
                        bounces[0]++;
                        playSound();
                    }

                    if (rect.getX() <= 0){
                        pane.setBackground(new Background(new BackgroundFill(randomColor(), null, null)));
                        rect.setFill(randomColor());
                        if (random.nextBoolean()) {
                            moveX[0] = -(int)(Math.random() * 10 + 5 + bounces[0]);
                        }
                        else{
                            moveX[0] = -(int)(Math.random() * 10 + 5 + bounces[0]);
                        }

                        moveX[0] *= -1;
                        bounces[0]++;
                        playSound();
                    }
                    if (rect.getX() + rectWidth >= screenWidth){
                        rect.setFill(randomColor());
                        pane.setBackground(new Background(new BackgroundFill(randomColor(), null, null)));

                        if (random.nextBoolean()) {
                            moveX[0] = (int)(Math.random() * 10 + 5 + bounces[0]);
                        }
                        else{
                            moveX[0] = (int)(Math.random() * 10 + 5 + bounces[0]);
                        }

                        moveX[0] *= -1;
                        bounces[0]++;
                        playSound();
                    }

                    if (rect.getY() >= screenHeight || rect.getY() <= 0){
                        rect.setFill(randomColor());
                        pane.setBackground(new Background(new BackgroundFill(randomColor(), null, null)));

                        moveY[0] *= -1;
                        playSound();
                    }
                    if (rect.getY() + rectHeight >= screenHeight){
                        rect.setFill(randomColor());
                        pane.setBackground(new Background(new BackgroundFill(randomColor(), null, null)));

                        if (random.nextBoolean()) {
                            moveY[0] = (int)(Math.random() * 10 + 5 + bounces[0]);
                        }
                        else{
                            moveY[0] = (int)(Math.random() * 10 + 5 + bounces[0]);
                        }

                        moveY[0] *= -1;
                        bounces[0]++;
                        playSound();
                    }

                    rect.setX(rect.getX() + moveX[0]);
                    rect.setY(rect.getY() + moveY[0]);
                })
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        Scene scene = new Scene(pane, screenWidth, screenHeight);
        stage.setTitle("bouncing");
        stage.setScene(scene);
        timeline.play();
        stage.show();

    }
    private Color randomColor(){
        return Color.rgb((int)(Math.random() * 254 + 1), (int)(Math.random() * 254 + 1), (int)(Math.random() * 254 + 1));
    }
    private void playSound(){
        String soundFilePath = "click.wav";

        try {
            File soundFile = new File(soundFilePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);

            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}