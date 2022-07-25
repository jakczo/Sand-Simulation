package sample;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;


public class Particle implements Fallable {
    private int x;
    private int y;
    final static int PARTICLE_CORNER_SIZE = 25; //in units
    private Color color;

    public Particle() {
    }

    public Particle(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void fall() {
    }

    public void draw(GraphicsContext gc) {
        fall();
        gc.setFill(this.color);
        gc.fillRect(this.x, this.y, PARTICLE_CORNER_SIZE, PARTICLE_CORNER_SIZE);
    }
}
