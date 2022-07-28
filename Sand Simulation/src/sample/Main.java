package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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
    static boolean gameOver = false;
    static boolean newParticle = false;

    static Area area = new Area();
    static int particleCount = 0;

    public void start(Stage stage) {
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
                        tick(gc, stage);
                        return;
                    }

                    if (now - lastTick > 1000000000 / speed) {
                        lastTick = now;
                        tick(gc, stage);
                    }
                }
            }.start();

            Scene scene = new Scene(root, Particle.PARTICLE_CORNER_SIZE * Area.AREA_CORNER_SIZE, Particle.PARTICLE_CORNER_SIZE * Area.AREA_CORNER_SIZE);

            // control
            scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
                if (key.getCode() == KeyCode.N) {
                    newParticle = true;
                }
                if (key.getCode() == KeyCode.Q) {
                    gameOver = true;
                }
            });

            scene.setOnMouseClicked(event -> {
//                ToDo: clean it?
                area.generateSand((int)event.getX(), (int)event.getY());
            });

            stage.setScene(scene);
            stage.setTitle("Sand Simulation");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // tick
    public static void tick(GraphicsContext gc, Stage stage) {
        if (gameOver) {
            stage.close();
            return;
        }
        if (newParticle) {
//            add new sand particle to area at specified (x,y). Later add particle where the mouse click happened
        }

        area.draw(gc);
    }

    public static void main(String[] args) {
        launch(args);
    }
}