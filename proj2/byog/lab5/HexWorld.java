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

    public static int calRowFirstPosX(int i, Position firstPos, int hexagonSize) {
        return (firstPos.x + hexagonSize * 2 * i - i);
    }

    public static int calRowFirstPosY(int i, Position firstPos, int hexagonSize) {
        if (i > 2) {
            return calRowFirstPosY(4 - i, firstPos, hexagonSize);
        }
        return firstPos.y - hexagonSize * i;
    }

    public static Position calRowFirstPos(int i, Position firstPos, int hexagonSize) {
        int x = calRowFirstPosX(i, firstPos, hexagonSize);
        int y = calRowFirstPosY(i, firstPos, hexagonSize);
        Position p = new Position(x, y);
        return p;
    }

    public static Position[] calRowPos(int i, int num, Position firstPos, int hexagonSize) {
        Position[] p = new Position[num];
        Position rowFirst = calRowFirstPos(i, firstPos, hexagonSize);
        for (int j = 0; j < num; j += 1) {
//            p[j].x = rowFirst.x;
//            p[j].y = rowFirst.y + 6 * j;
            p[j] = new Position(rowFirst.x, rowFirst.y + hexagonSize * 2 * j);
        }
        return p;
    }

    public static int hexNum(int i) {
        if (i > 2) {
            return hexNum(4 - i);
        }
        return 3 + i;
    }

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

    public static void drawRow(TETile[][] world, int hexagonSize, int i, Position firstPos) {
        int num = hexNum(i);
        Position[] p;
        p = calRowPos(i, num, firstPos, hexagonSize);
        for (int j = 0; j < num; j += 1) {
            addHexagon(world, p[j], hexagonSize, randomTETile());
        }
    }

    public static void drawWorld(TETile[][] world, int hexagonSize, Position firstPos) {
        for (int i = 0; i < 5; i += 1) {
            drawRow(world, hexagonSize, i, firstPos);
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
//        addHexagon(world,new Position(8, 10), 3, Tileset.FLOWER);
//        addHexagon(world,new Position(15, 10),3, Tileset.FLOOR);
//        addHexagon(world,new Position(22, 10), 3, Tileset.GRASS);

//        for (int i = 1; i < 5; i += 1) {
//            drawRow(world, 3, i, new Position(10, 10));
//        }
        drawWorld(world, 2, new Position(10, 20));

        ter.renderFrame(world);

    }
}
