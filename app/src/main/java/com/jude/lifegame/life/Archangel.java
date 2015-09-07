package com.jude.lifegame.life;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Mr.Jude on 2015/9/4.
 */
public class Archangel {
    private Space mSpace;
    private ArrayList<WorldObserver> mListeners = new ArrayList();
    private LinkedList<Integer> XBChange = new LinkedList<>();
    private LinkedList<Integer> YBChange = new LinkedList<>();
    private LinkedList<Integer> XDChange = new LinkedList<>();
    private LinkedList<Integer> YDChange = new LinkedList<>();


    Integer[] model = new Integer[0];

    public Archangel(Space mSpace) {
        this.mSpace = mSpace;
    }
    void born(int x,int y){
        if (!mSpace.space[x][y]){
            XBChange.add(x);
            YBChange.add(y);
        }
    }
    void die(int x,int y){
        if (mSpace.space[x][y]){
            XDChange.add(x);
            YDChange.add(y);
        }
    }

    void publish(){
        Integer[] XB = XBChange.toArray(model);
        Integer[] YB = YBChange.toArray(model);
        Integer[] XD = XDChange.toArray(model);
        Integer[] YD = YDChange.toArray(model);

        for (int i = 0; i < XB.length; i++) {
            mSpace.space[XB[i]][YB[i]] = true;
        }
        for (int i = 0; i < XD.length; i++) {
            mSpace.space[XD[i]][YD[i]] = false;
        }

        for (WorldObserver mListener : mListeners) {
            mListener.onBirth(XB, YB);
            mListener.onDie(XD, YD);
            mListener.onChange(mSpace.space);
        }

        XBChange.clear();
        YBChange.clear();
        XDChange.clear();
        YDChange.clear();
    }

    public void registerObserver(WorldObserver observer){
        mListeners.add(observer);
    }
}
