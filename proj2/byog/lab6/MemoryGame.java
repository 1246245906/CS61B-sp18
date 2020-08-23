package byog.lab6;

import byog.Core.RandomUtils;
import edu.princeton.cs.introcs.Draw;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private static int seed;
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(40, 40);
        game.startGame();
    }

    public MemoryGame(int width, int height) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        //TODO: Initialize random number generator
    }

    public String generateRandomString(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i += 1) {
            sb.append(CHARACTERS[RandomUtils.uniform(rand, 0, 25)]);
        }
        return sb.toString();
    }

    public void drawFrame(String s) {
        Font font = new Font("a", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.clear(Color.BLACK);
        StdDraw.text((int)(width / 2), (int)(height / 2), s);
        if (!gameOver) {
            setTopInfo();
        }
        setTopInfo();
        StdDraw.show();
    }

    private void setTopInfo() {
        Font font1 = new Font("a", Font.BOLD, 20);
        StdDraw.setFont(font1);
        StdDraw.text((int)(width / 2), height - 1, playerTurn ? "Type!" : "Watch");
        StdDraw.textLeft(0, height - 1, "Round: " + round);
        StdDraw.textRight(width, height - 1, ENCOURAGEMENT[round % ENCOURAGEMENT.length]);
        StdDraw.line(0, height - 2, width, height - 2);
    }

    public void flashSequence(String letters) {
        for (int i = 0; i < letters.length(); i += 1) {
            drawFrame(letters.substring(i, i + 1));
            StdDraw.pause(750);
            drawFrame("");
            StdDraw.pause(750);
        }
    }

    public String solicitNCharsInput(int n) {
        StringBuilder sb = new StringBuilder();
        while (sb.length() < n) {
            while (!StdDraw.hasNextKeyTyped());
            char key = StdDraw.nextKeyTyped();
            sb.append(key);
            drawFrame(sb.toString());
            StdDraw.pause(20);
        }
        StdDraw.pause(500);
        return sb.toString();
    }

    public void startGame() {
        rand = new Random(seed);
        gameOver = false;
        round = 1;
        playerTurn = false;

        for (; round < 26; round += 1) {
            playerTurn = false;
            drawFrame("Round " + round + "! Good Luck!");
            StdDraw.pause(750);
            String randStr = generateRandomString(round);
            flashSequence(randStr);
            playerTurn = true;
            drawFrame("");
            String handStr = solicitNCharsInput(round);
            if (!randStr.equals(handStr)) {
                gameOver = true;
                break;
            }
            drawFrame("Correct! Well done");
            StdDraw.pause(750);
            drawFrame("");
            StdDraw.pause(500);
        }
        drawFrame("Game Over! Final Level: " + round);
    }

}
