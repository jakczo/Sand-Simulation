package sample;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

// ToDo: implement factory design pattern
public abstract class Particle extends Point {
    private boolean hasFallen;
    final static int PARTICLE_CORNER_SIZE = 25; //in units
    private Color color;

    public Particle() { }

    public Particle(int x, int y, Color color) {
        super(x, y);
        this.color = color;
        this.hasFallen = false;
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

    public boolean getHasFallen() {
        return hasFallen;
    }

    public void setHasFallen(boolean hasFallen) {
        this.hasFallen = hasFallen;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract void fall(Particle below);

    public void draw(GraphicsContext gc) {
        gc.setFill(this.color);
        gc.fillRect(this.x, this.y, PARTICLE_CORNER_SIZE, PARTICLE_CORNER_SIZE);
    }
}
