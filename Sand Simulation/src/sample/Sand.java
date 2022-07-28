package sample;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;

public class Sand extends Particle {

    public Sand(int x, int y) {
        super(x, y, Color.ORANGE);
    }

    @Override
    public void fall(Particle below) {
        int tmpX = below.getX();
        int tmpY = below.getY();
        below.setX(this.getX());
        below.setY(this.getY());
        this.setX(tmpX);
        this.setY(tmpY);
        this.setHasFallen(true);
    }
}
