package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 60;
    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    private static class Position {
        public int x;
        public int y;
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // It looks too complicated, try to split this task to some small subtask
    public static void addHexagon(TETile[][] world, Position p, int size, TETile tile) {
        for (int i = 0; i < size; i += 1) {
            for (int j = 0; j < size + 2 * (i % size); j += 1) {
                world[p.x - (i % size) + j][p.y + i] = TETile.colorVariant(tile, 50, 50, 50, new Random());
            }
        }
        for (int i = 0; i < size; i += 1) {
            for (int j = 0; j < size + 2 * (i % size); j += 1) {
                world[p.x - (i % size) + j][p.y +size * 2 - 1 - i] = TETile.colorVariant(tile, 50, 50, 50, new Random());
            }
        }
    }

    /**
     * This method is to calculate first hexagon's position of given row
     * @param rowNum: the row number of big hexagon you want to calculate.
     * @param firstPos: first hexagon's position.
     * @param hexagonSize: size of small hexagon
     * @return x position of first hexagon for given row.
     * */
    public static int calRowFirstPosX(int rowNum, Position firstPos, int hexagonSize) {
        return (firstPos.x + hexagonSize * 2 * rowNum - rowNum);
    }

    /**
     * @return x position of first hexagon for given row.
     * */
    public static int calRowFirstPosY(int rowNum, Position firstPos, int hexagonSize) {
        if (rowNum > 2) {
            return calRowFirstPosY(4 - rowNum, firstPos, hexagonSize);
        }
        return firstPos.y - hexagonSize * rowNum;
    }

    /**
     * @return position of first hexagon for given row.
     * */
    public static Position calRowFirstPos(int rowNum, Position firstPos, int hexagonSize) {
        int x = calRowFirstPosX(rowNum, firstPos, hexagonSize);
        int y = calRowFirstPosY(rowNum, firstPos, hexagonSize);
        Position p = new Position(x, y);
        return p;
    }

    /**
     * @return position of first hexagon for every row.
     * */
    public static Position[] calRowPos(int rowNum, int num, Position firstPos, int hexagonSize) {
        Position[] p = new Position[num];
        Position rowFirst = calRowFirstPos(rowNum, firstPos, hexagonSize);
        for (int j = 0; j < num; j += 1) {
            p[j] = new Position(rowFirst.x, rowFirst.y + hexagonSize * 2 * j);
        }
        return p;
    }

    /**
     * @return the number of small hexagon for given row.
     * */
    public static int hexNum(int i) {
        if (i > 2) {
            return hexNum(4 - i);
        }
        return 3 + i;
    }

    /**
     * @return an random TETile with different color and graph.
     * */
    public static TETile randomTETile() {
        int tileNum = RANDOM.nextInt(4);
        switch (tileNum) {
            case 0: return TETile.colorVariant(Tileset.MOUNTAIN, 50, 50, 50, new Random());
            case 1: return TETile.colorVariant(Tileset.FLOWER, 50, 50, 50, new Random());
            case 2: return TETile.colorVariant(Tileset.GRASS, 50, 50, 50, new Random());
            case 3: return TETile.colorVariant(Tileset.TREE, 50, 50, 50, new Random());
            default: return TETile.colorVariant(Tileset.MOUNTAIN, 50, 50, 50, new Random());
        }
    }

    public static void drawRow(TETile[][] world, int hexagonSize, int rowNum, Position firstPos) {
        int num = hexNum(rowNum);
        Position[] p;
        p = calRowPos(rowNum, num, firstPos, hexagonSize);
        for (int j = 0; j < num; j += 1) {
            addHexagon(world, p[j], hexagonSize, randomTETile());
        }
    }

    public static void drawWorld(TETile[][] world, int hexagonSize, Position firstPos) {
        for (int rowNum = 0; rowNum < 5; rowNum += 1) {
            drawRow(world, hexagonSize, rowNum, firstPos);
        }
    }

    private static void initialize(TETile[][] world) {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        initialize(world);

        drawWorld(world, 4, new Position(10, 20));
        ter.renderFrame(world);

    }
}
