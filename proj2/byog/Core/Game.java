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

    private static class Position {
        public int x;
        public int y;
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
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
        long seed = findSeed(input);
        RANDOM = new Random(seed);

        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        initializeTiles(finalWorldFrame);

        generateFloor(finalWorldFrame);

        removeWall(finalWorldFrame);

//        ter.renderFrame(finalWorldFrame);

        return finalWorldFrame;
    }

    private void removeWall(TETile[][] world) {
        Stack<Position> s = new Stack<Position>();
        for (int i = 1; i < WIDTH - 1; i += 1) {
            for (int j = 1; j < HEIGHT - 1; j++) {
                if (world[i][j].equals(Tileset.WALL) & countNeighbor(world, new Position(i, j)) >= 8) {
                    s.push(new Position(i, j));
                }
            }
        }
        while (!s.empty()) {
            Position p = s.pop();
            world[p.x][p.y] = Tileset.NOTHING;
        }
    }

    private void generateFloor(TETile[][] world) {
        RandomTheWorld(world);
        int i = 0;
        while(i < 5) {
            improveWorld(world);
            i += 1;
        }
    }

    private void improveWorld(TETile[][] world) {
        for (int i = 1; i < WIDTH - 1; i += 1) {
            for (int j = 1; j < HEIGHT - 1; j ++) {
                if (!world[i][j].equals(Tileset.WALL) & (countNeighbor(world, new Position(i, j)) > 5)) {
                    world[i][j] = Tileset.WALL;
                } else if (world[i][j].equals(Tileset.WALL) & (countNeighbor(world, new Position(i, j)) < 4)) {
                    world[i][j] = Tileset.FLOOR;
                }
            }
        }
    }

    private int countNeighbor2(TETile[][] world, Position position) {
        int count = 0;
        for (int i = -2; i < 3; i += 1) {
            for (int j = -2; j < 3; j += 1) {
                if (position.x + i <= 0 | position.y + j <= 0 | position.x + i >= WIDTH | position.y + j >= HEIGHT) {
                    continue;
                }
                if (world[position.x + i][position.y + j].equals(Tileset.WALL)) {
                    count += 1;
                }
            }
        }
        return count;
    }

    private int countNeighbor(TETile[][] world, Position position) {
        int count = 0;
        if (world[position.x + 1][position.y] == Tileset.WALL) count += 1;
        if (world[position.x - 1][position.y] == Tileset.WALL) count += 1;
        if (world[position.x + 1][position.y + 1] == Tileset.WALL) count += 1;
        if (world[position.x - 1][position.y + 1] == Tileset.WALL) count += 1;
        if (world[position.x + 1][position.y - 1] == Tileset.WALL) count += 1;
        if (world[position.x - 1][position.y - 1] == Tileset.WALL) count += 1;
        if (world[position.x][position.y + 1] == Tileset.WALL) count += 1;
        if (world[position.x][position.y - 1] == Tileset.WALL) count += 1;
        return count;
    }

    private void RandomTheWorld(TETile[][] world) {
        for (int i = 1; i < WIDTH - 1; i += 1) {
            for (int j = 1; j < HEIGHT - 1; j ++) {
                double r = RandomUtils.uniform(RANDOM);
                if (r < 0.4) {
                    world[i][j] = Tileset.FLOOR;
                }
            }
        }
    }

    private long findSeed(String input) {
        return 8795418;         // TODO:
    }

    private char findOption(String input) {
        return 'N';           // TODO:
    }

    private void initializeTiles(TETile[][] finalWorldFrame) {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                finalWorldFrame[x][y] = Tileset.WALL;
            }
        }
    }
}
