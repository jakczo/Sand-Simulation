package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Area {
    private  ArrayList <ArrayList<Particle>> areaMatrix;
    final static int AREA_CORNER_SIZE = 20; // in particles
    private HashMap<Point, Integer> matrixInfo; // need to find specified particle in matrix based on coordinates

    public Area() {
        this.areaMatrix = new ArrayList<>();
        for (int i = 0; i < AREA_CORNER_SIZE; ++i)
            areaMatrix.add(new ArrayList<Particle>());

        for (int i = 0; i < AREA_CORNER_SIZE; ++i) {
            for (int j = 0; j < AREA_CORNER_SIZE; ++j) {
//               x and y coordinates are being moved by PARTICLE_CORNER_SIZE offset so e.g. 1st particle will be (0*25, 0*25), but second (below) will be (0*25, 1*25) and so on
                if (i == 0 && j == 0) {
                    this.areaMatrix.get(i).add(new Sand(i * Particle.PARTICLE_CORNER_SIZE, j * Particle.PARTICLE_CORNER_SIZE));
                } else {
                    this.areaMatrix.get(i).add(new Void(i * Particle.PARTICLE_CORNER_SIZE, j * Particle.PARTICLE_CORNER_SIZE));
                }
//                ToDo: might be a good idea, instead of creating a new point, referring to an existing point in a particle
                matrixInfo = new HashMap<Point, Integer>();
                matrixInfo.put(new Point(i * Particle.PARTICLE_CORNER_SIZE, i * Particle.PARTICLE_CORNER_SIZE), Integer.valueOf(i));
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

    public void addParticle(Particle newParticle) {
        for (ArrayList<Particle> pArray : this.areaMatrix) {
//            use of hashmap. Hashmap with key object coordinates like point (x,y) and value of particle's index in the matrix

        }
    }

//    very alpha version, ToDo: clean it and format it
    public void generateSand(int x, int y) {

        int tmpX = x % Particle.PARTICLE_CORNER_SIZE;
        int formattedX = x - tmpX;

        int tmpY = y % Particle.PARTICLE_CORNER_SIZE;
        int formattedY = y - tmpY;

        System.out.println("Before: (" + x + ", " + y + ")\nAfter: (" + formattedX + ", " + formattedY + ")");

        Point p = new Point(formattedX, formattedY);
//        ToDo: hashmap cant find proper element, it searches for an object reference instead of coordinates inside the object
        matrixInfo.get(p);
    }
}
