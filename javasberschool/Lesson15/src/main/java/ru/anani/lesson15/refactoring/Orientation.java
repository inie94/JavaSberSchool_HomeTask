package ru.anani.lesson15.refactoring;

public enum Orientation {
    NORTH {
        @Override
        public int[] moveForwards(int[] position) {
            return new int[]{position[0], position[1] + 1};
        }

        @Override
        public Orientation turnClockwise() {
            return EAST;
        }
    },
    WEST {
        @Override
        public int[] moveForwards(int[] position) {
            return new int[]{position[0] - 1, position[1]};
        }

        @Override
        public Orientation turnClockwise() {
            return NORTH;
        }
    },
    SOUTH {
        @Override
        public int[] moveForwards(int[] position) {
            return new int[]{position[0], position[1] - 1};
        }

        @Override
        public Orientation turnClockwise() {
            return WEST;
        }
    },
    EAST {
        @Override
        public int[] moveForwards(int[] position) {
            return new int[]{position[0] + 1, position[1]};
        }

        @Override
        public Orientation turnClockwise() {
            return SOUTH;
        }
    };

    public abstract int[] moveForwards(int[] position);
    public abstract Orientation turnClockwise();
}
