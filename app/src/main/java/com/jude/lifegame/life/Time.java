package com.jude.lifegame.life;

import java.io.Serializable;

/**
 * Created by Mr.Jude on 2015/9/4.
 */
public class Time  implements Serializable {
    int unit;

    final Object lock = new Object();
    int status = NORMAL;
    private static final int NORMAL = 0;
    private static final int PAUSE = 1;
    private static final int STEP = 2;

    public Time(int unit) {
        this.unit = unit;
    }

    public void setUnit(int unit){
        this.unit = unit;
    }

    void delay(){
        onBeginDelay();
        try {
            Thread.sleep(unit);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onAfterDelay();
    }

    private void onBeginDelay(){
        if (status == STEP || status == PAUSE)
            synchronized (lock){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }

    private void onAfterDelay(){

    }

    public void pause(){
        status = PAUSE;
    }

    public void resume(){
        status = NORMAL;
        synchronized (lock) {
            lock.notify();
        }
    }

    public void next(){
        status = STEP;
        synchronized (lock) {
            lock.notify();
        }
    }
}
