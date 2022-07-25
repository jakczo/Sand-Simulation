package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Area {
    private  ArrayList <ArrayList<Particle>> areaMatrix;
    final static int AREA_CORNER_SIZE = 20; // in particles

    public Area() {
        this.areaMatrix = new ArrayList<>();
        for (int i = 0; i < AREA_CORNER_SIZE; ++i)
            areaMatrix.add(new ArrayList<Particle>());

        for (int i = 0; i < AREA_CORNER_SIZE; ++i) {
            for (int j = 0; j < AREA_CORNER_SIZE; ++j) {
//               x and y coordinates are being moved by PARTICLE_CORNER_SIZE offset so e.g. 1st particle will be (0*25, 0*25), but second (below) will be (0*25, 1*25) and so on
                if (i == 9 && j == 0)
                    this.areaMatrix.get(i).add(new Sand(i * Particle.PARTICLE_CORNER_SIZE, j * Particle.PARTICLE_CORNER_SIZE));

                this.areaMatrix.get(i).add(new Void(i * Particle.PARTICLE_CORNER_SIZE, j * Particle.PARTICLE_CORNER_SIZE));
            }
        }
    }

    public Area(ArrayList<ArrayList<Particle>> areaMatrix) {
        this.areaMatrix = areaMatrix;
    }

    public void draw(GraphicsContext gc) {
        for (ArrayList<Particle> pArray : this.areaMatrix) {
            for (Particle p : pArray) {
                p.draw(gc);
            }
        }
    }
}
