package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Area {
    private  ArrayList <ArrayList<Particle>> areaMatrix;
    final static int AREA_CORNER_SIZE = 20; // in particles
    private HashMap<Point, Point> matrixInfo; // particle coordinates on area to particle coordinates in matrix array

    public Area() {
        this.areaMatrix = new ArrayList<>();
        matrixInfo = new HashMap<Point, Point>();

        for (int i = 0; i < AREA_CORNER_SIZE; ++i)
            areaMatrix.add(new ArrayList<Particle>());

        for (int i = 0; i < AREA_CORNER_SIZE; ++i) {
            for (int j = 0; j < AREA_CORNER_SIZE; ++j) {
//               x and y coordinates are being moved by PARTICLE_CORNER_SIZE offset so e.g. 1st particle will be (0*25, 0*25), but second (below) will be (0*25, 1*25) and so on
                this.areaMatrix.get(i).add(new Void(i * Particle.PARTICLE_CORNER_SIZE, j * Particle.PARTICLE_CORNER_SIZE));

//                ToDo: might be a good idea, instead of creating a new point, referring to an existing point §                 in a particle
                matrixInfo.put(new Point(i * Particle.PARTICLE_CORNER_SIZE, j * Particle.PARTICLE_CORNER_SIZE), new Point(i, j));
            }
        }
    }

    public Area(ArrayList<ArrayList<Particle>> areaMatrix) {
        this.areaMatrix = areaMatrix;
    }

    public void draw(GraphicsContext gc) {
        for (ArrayList<Particle> pArray : this.areaMatrix) {
            for (int i = 0; i < AREA_CORNER_SIZE; ++i) {
                if (i < AREA_CORNER_SIZE - 1) {
                    if (isAbleToFall(pArray.get(i), pArray.get(i + 1)))
                        Collections.swap(pArray, i, i + 1);
                }
                pArray.get(i).draw(gc);
            }
        }
        resetHasFallenFlags();
    }

    public boolean isAbleToFall(Particle current, Particle below) {
        if (current instanceof Sand)
            if (!current.getHasFallen()) {
                current.fall(below);
                return true;
            }
        return false;
    }

    public void resetHasFallenFlags() {
        for (ArrayList<Particle> pArray : this.areaMatrix)
            for (int i = 0; i < AREA_CORNER_SIZE; ++i)
                pArray.get(i).setHasFallen(false);
    }

//    very alpha version, ToDo: clean it and format it
    public void generateSand(int x, int y) {
        int tmpX = x % Particle.PARTICLE_CORNER_SIZE;
        int formattedX = x - tmpX;
        int tmpY = y % Particle.PARTICLE_CORNER_SIZE;
        int formattedY = y - tmpY;

        Point p = new Point(formattedX, formattedY);
        Point coordinates = matrixInfo.get(p);
        areaMatrix.get(coordinates.getX()).set(coordinates.getY(), new Sand(formattedX, formattedY));
    }
}
