package ru.anani.lesson15.refactoring;

public class Tractor {
    int[] position = new int[] { 0, 0 };
    int[] field = new int[] { 5, 5 };
    Orientation orientation = Orientation.NORTH;

    public void move(String command) {
        if (command == "F") {
            moveForwards();
        } else if (command == "T") {
            turnClockwise();
        }
    }

    public void moveForwards() {
        position = orientation.moveForwards(position);
        if (Math.abs(position[0]) > field[0] || Math.abs(position[1]) > field[1]) {
            throw new TractorInDitchException();
        }
    }

    public void turnClockwise() {
        orientation = orientation.turnClockwise();
    }

    public int getPositionX() {
        return position[0];
    }

    public int getPositionY() {
        return position[1];
    }

    public Orientation getOrientation() {
        return orientation;
    }
}
