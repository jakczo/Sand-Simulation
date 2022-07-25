package sample;

import javafx.scene.paint.Color;

public class Sand extends Particle {
    public Sand(int x, int y) {
        super(x, y, Color.DARKORANGE);
    }

    @Override
    public void fall() {
        this.setY(this.getY() + 1);
    }
}
