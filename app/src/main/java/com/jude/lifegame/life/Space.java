package com.jude.lifegame.life;

import java.io.Serializable;

/**
 * Created by Mr.Jude on 2015/9/4.
 */
public class Space implements Serializable{
    public boolean[][] space;
    public int width;
    public int height;

    public Space(int width,int height) {
        this.width = width;
        this.height = height;
        this.space = new boolean[width][height];
    }

    public void clear(){
        this.space = new boolean[width][height];
    }
}
