package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;
import java.util.Stack;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    private Random RANDOM;
    TETile[][] world;
    Stack<Room> rooms;

    private class Room {
        Position p1;
        Position p2;
        int doorNum;
        Position[] doorPs;

        public Room(Position pBase) {
            p1 = new Position(pBase.x, pBase.y);
            initiateRoom();
        }

        private void initiateRoom() {
            int x = Math.min(p1.x + RandomUtils.uniform(RANDOM, 2,6), WIDTH - 2);
            int y = Math.min(p1.y + RandomUtils.uniform(RANDOM, 2,6), HEIGHT - 2);
            p2 = new Position(x, y);
            doorNum = RandomUtils.uniform(RANDOM, 1, 3);
            randomDoorPosition();
        }

//        private void randomDoorPosition() {
//            doorPs = new Position[doorNum];
//            for (int i = 0; i < doorNum; i += 1) {
//                switch (chooseDoorDir()) {
//                    case 'D' -> doorPs[i] = new Position(RandomUtils.uniform(RANDOM, p1.x,p2.x), p1.y);
//                    case 'L' -> doorPs[i] = new Position(p1.x, RandomUtils.uniform(RANDOM, p1.y,p2.y));
//                    case 'R' -> doorPs[i] = new Position(p2.x, RandomUtils.uniform(RANDOM, p1.y,p2.y));
//                    default -> doorPs[i] = new Position(RandomUtils.uniform(RANDOM, p1.x,p2.x), p2.y);
//                }
//            }
//        }
//
//        private char chooseDoorDir() {
//            int r = RandomUtils.uniform(RANDOM, 4);
//            return switch (r) {
//                case 1 -> 'D';
//                case 2 -> 'L';
//                case 3 -> 'R';
//                default -> 'U';
//            };
//        }

        /**above can't passed in auto grader*/
        private void randomDoorPosition() {
            doorPs = new Position[doorNum];
            for (int i = 0; i < doorNum; i += 1) {
                switch (chooseDoorDir()) {
                    case 'D':{
                        doorPs[i] = new Position(RandomUtils.uniform(RANDOM, p1.x,p2.x), p1.y);
                        continue;
                    }
                    case 'L' :{
                        doorPs[i] = new Position(p1.x, RandomUtils.uniform(RANDOM, p1.y,p2.y));
                        continue;
                    }
                    case 'R':{
                        doorPs[i] = new Position(p2.x, RandomUtils.uniform(RANDOM, p1.y,p2.y));
                        continue;
                    }
                    case 'U':{
                        doorPs[i] = new Position(RandomUtils.uniform(RANDOM, p1.x,p2.x), p2.y);
                    }
                }
            }
        }

        private char chooseDoorDir() {
            int r = RandomUtils.uniform(RANDOM, 4);
            switch (r) {
                case 1:{
                    return 'D';
                }
                case 2: {
                    return 'L';
                }
                case 3:{
                    return 'R';
                }
                case 0:{
                    return 'U';
                }
            };
            return 'U';
        }

        public void addRoom() {
            for (int i = p1.x; i <= p2.x; i += 1) {
                for (int j = p1.y; j <= p2.y; j += 1) {
                    world[i][j] = randomFloor();
                }
            }
        }
    }

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().
        char option = findOption(input);
        play(option, input);

        TETile[][] finalWorldFrame = world;
        return finalWorldFrame;
    }

    private void play(char op, String input) {
        if (op != 'N') {
            throw new IllegalArgumentException("invalid argument: press N to begin.");
        }
        initialize(input);
        initializeTiles(world);
        generateWorld();
        ter.renderFrame(world);
    }

    private void generateWorld() {
        generateRoom();
        addCorridors();
        removeWall(world);
    }

    private void initialize(String input) {
        long seed = findSeed(input);
        RANDOM = new Random(seed);
        rooms = new Stack<>();
        ter.initialize(WIDTH, HEIGHT);

        world = new TETile[WIDTH][HEIGHT];
    }

    private void addCorridors() {
        while (rooms.size() >= 2){
            addCorridor(rooms.pop(), rooms.peek());
        }
    }

    private void addCorridor(Room r1, Room r2) {
//        double d = RandomUtils.uniform(RANDOM);
        if(r1.doorPs[0].x >= r2.doorPs[0].x) {
            for (int i = r1.doorPs[0].x; i >= r2.doorPs[0].x; i -= 1) {
                world[i][r1.doorPs[0].y] = randomFloor();
            }
        }
        if(r1.doorPs[0].x <= r2.doorPs[0].x) {
            for (int i = r1.doorPs[0].x; i <= r2.doorPs[0].x; i += 1) {
                world[i][r1.doorPs[0].y] = randomFloor();
            }
        }
        if (r1.doorPs[0].y <= r2.doorPs[0].y) {
            for (int i = r1.doorPs[0].y; i <= r2.doorPs[0].y; i += 1) {
                world[r2.doorPs[0].x][i] = randomFloor();
            }
        }
        if (r1.doorPs[0].y > r2.doorPs[0].y) {
            for (int i = r1.doorPs[0].y; i >= r2.doorPs[0].y; i -= 1) {
                world[r2.doorPs[0].x][i] = randomFloor();
            }
        }
    }

    private void generateRoom() {
        for (int i = 1; i <= WIDTH - 3; i += 2) {
            for (int j = 1; j <= HEIGHT - 3; j += 2) {
                double random = RandomUtils.uniform(RANDOM);
                if (random > 0.92) {
                    Room r = new Room(new Position(i, j));
                    if (nearPre(r)) {
                        continue;
                    }
                    r.addRoom();
                    rooms.push(r);
                    j = j + r.p2.y - r.p1.y;
                }
            }
        }
    }

    private boolean nearPre(Room r) {
        for (int i = r.p1.y - 1; i <= r.p2.y + 1; i += 1) {
            if (world[r.p1.x][i].equals(Tileset.FLOOR)) {
                return true;
            } else if (world[r.p1.x-1][i].equals(Tileset.FLOOR)) {
                return true;
            }
        }
        return false;
//        return world[i][j].equals(Tileset.FLOOR);
    }

    private void removeWall(TETile[][] world) {
        Stack<Position> s = new Stack<Position>();
        for (int i = 0; i < WIDTH; i += 1) {
            for (int j = 0; j < HEIGHT; j++) {
                if (world[i][j].equals(Tileset.WALL) & countNeighbor(world, new Position(i, j)) == 8) {
                    s.push(new Position(i, j));
                }
            }
        }
        while (!s.empty()) {
            Position p = s.pop();
            world[p.x][p.y] = Tileset.NOTHING;
        }
    }

    private int countNeighbor(TETile[][] world, Position position) {
        int count = 0;
        for (int i = -1; i < 2; i += 1) {
            for (int j = -1; j < 2; j += 1) {
                if(i == 0 & j == 0) {
                    continue;
                }
                if (position.x + i < 0 | position.y + j < 0 | position.x + i >= WIDTH | position.y + j >= HEIGHT) {
                    count += 1;
                    continue;
                }
                if (world[position.x + i][position.y + j].equals(Tileset.WALL)) {
                    count += 1;
                }
            }
        }
        return count;
    }

    /**
     * @source: https://stackoverflow.com/questions/3481828/how-to-split-a-string-in-java
     * @source: https://stackoverflow.com/questions/5585779/how-do-i-convert-a-string-to-an-int-in-java
     * */
    private long findSeed(String input) {
        String[] parts = input.split("N");
        String[] op = parts[1].split("L");
        return Integer.parseInt(op[0]);
    }

    private char findOption(String input) {
        return input.charAt(0);
    }

    private void initializeTiles(TETile[][] finalWorldFrame) {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                finalWorldFrame[x][y] = randomWall();
            }
        }
    }

    private TETile randomWall() {
        return TETile.colorVariant(Tileset.WALL, 50, 50, 50, new Random());
    }

    private TETile randomFloor() {
        return TETile.colorVariant(Tileset.FLOOR, 50, 50, 50, new Random());
    }

///**old code, using cellular automata*/
//    private void generateFloor(TETile[][] finalWorldFrame) {
//        initializeTiles(finalWorldFrame);
//    }
//    private void generateFloor1(TETile[][] world) {
//        RandomTheWorld(world);
//        int i = 0;
//        while(i < 5) {
//            improveWorld(world);
//            i += 1;
//        }
//    }
//
//    private void improveWorld(TETile[][] world) {
//        for (int i = 1; i < WIDTH - 1; i += 1) {
//            for (int j = 1; j < HEIGHT - 1; j ++) {
//                if (!world[i][j].equals(Tileset.WALL) & (countNeighbor(world, new Position(i, j)) > 5)) {
//                    world[i][j] = Tileset.WALL;
//                } else if (world[i][j].equals(Tileset.WALL) & (countNeighbor(world, new Position(i, j)) < 4)) {
//                    world[i][j] = Tileset.FLOOR;
//                } else if (world[i][j].equals(Tileset.FLOOR) & (countNeighbor2(world, new Position(i, j)) > 18)) {
//                    world[i][j] = Tileset.WALL;
//                }
//            }
//        }
//    }
//
//    private int countNeighbor2(TETile[][] world, Position position) {
//        int count = 0;
//        for (int i = -2; i < 3; i += 1) {
//            for (int j = -2; j < 3; j += 1) {
//                if (position.x + i <= 0 | position.y + j <= 0 | position.x + i >= WIDTH | position.y + j >= HEIGHT) {
//                    count += 1;
//                    continue;
//                }
//                if (world[position.x + i][position.y + j].equals(Tileset.WALL)) {
//                    count += 1;
//                }
//            }
//        }
//        return count;
//    }
//private void RandomTheWorld(TETile[][] world) {
//    for (int i = 1; i < WIDTH - 1; i += 1) {
//        for (int j = 1; j < HEIGHT - 1; j ++) {
//            double r = RandomUtils.uniform(RANDOM);
//            if (r < 0.4) {
//                world[i][j] = Tileset.FLOOR;
//            }
//        }
//    }
//}
}
