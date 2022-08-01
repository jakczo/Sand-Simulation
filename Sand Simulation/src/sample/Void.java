package sample;

import javafx.scene.paint.Color;

// ToDo: change class name, void is special name
public class Void extends Particle {

    public Void(int x, int y) {
        super(x, y, Color.BLACK);
    }

    public Void(Void v ) {
        super(v.getX(), v.getY(), v.getColor());
    }

    @Override
    public void fall(Particle below) { }
}
