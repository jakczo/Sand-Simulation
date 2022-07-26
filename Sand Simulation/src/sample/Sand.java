package sample;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;

public class Sand extends Particle {
    public Sand(int x, int y) {
        super(x, y, Color.ORANGE);
    }

//    ToDo: that's ugly
    @Override
    public void fall(ArrayList<Particle> pList, int first, int second) {
        Collections.swap(pList, first, second);
//        this.setY(this.getY() + PARTICLE_CORNER_SIZE);
        Particle tmp = new Particle();
        tmp.setX(pList.get(second).getX());
        tmp.setY(pList.get(second).getY());

        pList.get(second).setX(pList.get(first).getX());
        pList.get(second).setY(pList.get(first).getY());

        pList.get(first).setX(tmp.getX());
        pList.get(first).setY(tmp.getY());

//        ToDo: think of other solution. mby every particle should have "fell" attribute?
        Area.fell = true;

    }
}
