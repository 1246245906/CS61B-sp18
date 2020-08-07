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

    private static class Position {
        public int x;
        public int y;
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

//    private Position currentPos;
//    private int currentSize;
//    private Stack<Position> s;
//
//    public Game() {
//        currentPos = new Position(WIDTH / 2, HEIGHT / 2);
//        currentSize = 0;
//        s = new Stack<Position>();
//    }

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

        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        initializeTiles(finalWorldFrame);

        generateFloor(finalWorldFrame, seed);

        ter.renderFrame(finalWorldFrame);

        return finalWorldFrame;
    }

    private void generateFloor(TETile[][] world, long seed) {
        Position currentPos = new Position(WIDTH / 2, HEIGHT / 2);
        int currentSize = 0;
        Stack<Position> positionStack = new Stack<Position>();
        positionStack.push(currentPos);

        world[currentPos.x][currentPos.y] = Tileset.FLOOR;

        while (currentSize <= (HEIGHT * WIDTH) / 6) {
            currentPos = newPos(world, seed, currentPos, positionStack);
            world[currentPos.x][currentPos.y] = Tileset.FLOOR;
            currentSize += 1;
        }
    }

    private Position newPos(TETile[][] world, long seed, Position currentPos, Stack<Position> positionStack) {
//        int posX = currentPos.x;
//        int posY = currentPos.y;
        Position pos = new Position(currentPos.x, currentPos.y);
        Random RANDOM = new Random();
//        boolean valid = true;
        int dir = RANDOM.nextInt(4);
        //            default: pos.y += 1;             // move above
        switch (dir) {
            case 0 -> pos.x += 1;// move right
            case 1 -> pos.x -= 1;// move left
            case 2 -> pos.y -= 1;
            case 3 -> pos.y += 1;
        }

        if (!isValid(world, pos)) {
            pos = newPos(world, seed, prePosition(positionStack, RANDOM), positionStack);      // todo: too many times.
        }

        positionStack.push(pos);
        return pos;
    }

    private boolean isValid(TETile[][] world, Position pos) {
        return pos.x > 0 & pos.x < WIDTH - 1 & pos.y > 0 & pos.y < HEIGHT - 1
                & world[pos.x][pos.y].equals(Tileset.NOTHING);
    }

    private Position prePosition(Stack<Position> positionStack, Random random) {
//        int a = positionStack.size();       // todo:
//        int randomNum = random.nextInt(positionStack.size());
//        int i = 0;
//        while (i < randomNum - 1) {
//            positionStack.pop();
//            i += 1;
//        }
        return positionStack.pop();
    }


    private long findSeed(String input) {
        return 1231254;         // TODO:
    }

    private char findOption(String input) {
        return 'N';           // TODO:
    }

    private void initializeTiles(TETile[][] finalWorldFrame) {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                finalWorldFrame[x][y] = Tileset.NOTHING;
            }
        }
    }
}
