package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Stack;
import javafx.util.Pair;

import javax.sound.midi.SysexMessage;

import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.World;
import com.anish.calabashbros.MazeGenerator;
import com.anish.calabashbros.RecordedFloor;
import com.anish.calabashbros.Wall;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    private Calabash bro;
    private MazeGenerator mazegenerator;
    private int[][] maze;
    private Stack<Pair<Integer,Integer>> st; 
    String[] sortSteps;

    public WorldScreen() {
        world = new World();
        mazegenerator = new MazeGenerator(30);
        bro = new Calabash(new Color(78, 154, 6), 4, world);
        mazegenerator.generateMaze();
        maze = mazegenerator.getArraysMaze();
        st = new Stack<>();
        maze[0][0] = 2;
        for (int i = 0; i < 30; ++i)
            for (int j = 0; j < 30; ++j)
                if (maze[i][j] == 0) world.put(new Wall(world), j, i);
        world.put(bro, 0, 0);

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
        int keycode = key.getKeyCode();
        int x = bro.getX(), y = bro.getY();
        switch(keycode){
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                walk(x, y - 1, x, y);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                walk(x, y + 1, x, y);
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                walk(x - 1, y, x, y);
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                walk(x + 1, y, x, y);
                break;
            case KeyEvent.VK_SPACE:
                search(x, y);
                break;
            default:
                break;
        } 
        return this;
    }

    public void walk(int x, int y, int prex, int prey)
    {
        if (x >=0 && x < World.WIDTH && y >= 0 && y < World.HEIGHT && maze[y][x] != 0)
        {
            bro.moveTo(x, y);
            world.put(new RecordedFloor(world), prex, prey);
        }
    }

    public boolean trywalk(int x, int y, int prex, int prey)
    {
        if (x >=0 && x < World.WIDTH && y >= 0 && y < World.HEIGHT && maze[y][x] == 1)
        {
            bro.moveTo(x, y);
            world.put(new RecordedFloor(world), prex, prey);
            st.push(new Pair(prex, prey));
            maze[y][x] = 2;
            return true;
        }
        return false;
    }

    public void search(int x, int y)
    {
        if (x == world.WIDTH - 1 && y == world.HEIGHT - 1) return;
        int[][] dirs = {{1,0},{0,1},{-1,0},{0,-1}};
        for (int i = 0; i < 4; ++i)
        {
            if (trywalk(x + dirs[i][0], y + dirs[i][1], x, y))
                return;
        }
        Pair<Integer, Integer> pair;
        if (st.isEmpty()) return;
        else pair = st.pop();
        bro.moveTo(pair.getKey(), pair.getValue());
        world.put(new RecordedFloor(world), x, y);
    }
}
