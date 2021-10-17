package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.Floor;
import com.anish.calabashbros.World;
import com.anish.calabashbros.MazeGenerator;
import com.anish.calabashbros.Wall;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    private Calabash[] bros;
    private MazeGenerator mazegenerator;
    private int[][] maze; 
    String[] sortSteps;

    public WorldScreen() {
        world = new World();
        mazegenerator = new MazeGenerator(30);
        bros = new Calabash[7];

        bros[0] = new Calabash(new Color(78, 154, 6), 4, world);
        mazegenerator.generateMaze();
        maze = mazegenerator.getArraysMaze();
        for (int i = 0; i < 30; ++i)
            for (int j = 0; j < 30; ++j)
                if (maze[i][j] == 0) world.put(new Wall(world), 5 + j, 5 + i);
        world.put(bros[0], 5, 5);

    }

    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }

    int i = 0;

    @Override
    public Screen respondToUserInput(KeyEvent key) {

        return this;
    }

}
