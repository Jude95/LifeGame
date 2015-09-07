package com.jude.lifegame.life;

/**
 * Created by Mr.Jude on 2015/9/5.
 */
public interface WorldObserver {
    void onBirth(Integer[] x, Integer[] y);
    void onDie(Integer[] x, Integer[] y);
    void onChange(boolean[][] world);
}
