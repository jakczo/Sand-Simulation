package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Main extends Application {
    // variable
    static int speed = 5;
    static int width = 20;
    static int height = 20;
    static int cornersize = 25;
    static List<Sand> snake = new ArrayList<>();
    static Dir direction = Dir.down;
    static boolean gameOver = false;

    static Area area = new Area();


    static int particleCount = 0;

    public enum Dir {
        left, right, up, down
    }

    public void start(Stage primaryStage) {
        try {
            VBox root = new VBox();
            Canvas c = new Canvas(Particle.PARTICLE_CORNER_SIZE * Area.AREA_CORNER_SIZE, Particle.PARTICLE_CORNER_SIZE * Area.AREA_CORNER_SIZE);
            GraphicsContext gc = c.getGraphicsContext2D();
            root.getChildren().add(c);

            new AnimationTimer() {
                long lastTick = 0;

                public void handle(long now) {
                    if (lastTick == 0) {
                        lastTick = now;
                        tick(gc);
                        return;
                    }

                    if (now - lastTick > 1000000000 / speed) {
                        lastTick = now;
                        tick(gc);
                    }
                }

            }.start();

            Scene scene = new Scene(root, Particle.PARTICLE_CORNER_SIZE * Area.AREA_CORNER_SIZE, Particle.PARTICLE_CORNER_SIZE * Area.AREA_CORNER_SIZE);

            // control
            scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
//                if (key.getCode() == KeyCode.D) {
//                    direction = Dir.right;
//                }
                if (key.getCode() == KeyCode.Q) {
                    gameOver = true;
                }

            });


            primaryStage.setScene(scene);
            primaryStage.setTitle("Sand Simulation");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // tick
    public static void tick(GraphicsContext gc) {
        if (gameOver) {
            gc.setFill(Color.RED);
            gc.setFont(new Font("", 50));
            gc.fillText("GAME OVER", 100, 250);
            return;
        }

//        snake.get(0).setY(snake.get(0).getY() + 1);

        // background
//        gc.setFill(Color.BLACK);
//        gc.fillRect(0, 0, width * cornersize, height * cornersize);

        // counter
//        gc.setFill(Color.WHITE);
//        gc.setFont(new Font("", 10));
//        gc.fillText("Particle count: " + particleCount++, 10, 30);

        area.draw(gc);

    }

    public static void main(String[] args) {
        launch(args);
    }
}