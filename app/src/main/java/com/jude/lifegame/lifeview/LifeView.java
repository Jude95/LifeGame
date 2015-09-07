package com.jude.lifegame.lifeview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.jude.lifegame.life.WorldObserver;

/**
 * Created by Mr.Jude on 2015/9/4.
 */
public class LifeView extends SurfaceView implements WorldObserver,SurfaceHolder.Callback {
    SurfaceHolder mHolder;
    Paint mPaintLive;
    Paint mPaintDie;

    int widthCount;
    int heightCount;
    int unit;

    boolean[][] cache;

    private boolean isLaunched = false;

    public LifeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LifeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public LifeView(Context context) {
        super(context);
        init();
    }

    public void setUnit(int unit){
        this.unit = unit;
        setMap(cache);
    }

    void init(){
        mPaintLive = new Paint();
        mPaintDie = new Paint();
        mPaintLive.setColor(Color.BLUE);
        mPaintDie.setColor(Color.WHITE);
        mHolder = this.getHolder();
        mHolder.addCallback(this);
        unit = 20;
    }


    public void setMap(final boolean[][] world){
        widthCount = world.length;
        heightCount = world[0].length;
        cache = world;
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(unit * widthCount, unit * heightCount);
        Log.i("Life", "onMeasure");
    }

    @Override
    public void onBirth(Integer[] x, Integer[] y) {

    }

    @Override
    public void onDie(Integer[] x, Integer[] y) {

    }

    @Override
    public void onChange(boolean[][] world) {
        if(world==null)return;
        int width = world.length,height = world[0].length;
        cache = world;
        Canvas c = mHolder.lockCanvas();
        if (c!=null)
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                c.drawRect(
                        x * unit,
                        y * unit,
                        (x + 1) * unit,
                        (y + 1) * unit,
                        world[x][y] ? mPaintLive : mPaintDie);
            }
        }
        mHolder.unlockCanvasAndPost(c);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i("Life","surfaceCreated");
        isLaunched = true;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.i("Life","surfaceChanged");
        onChange(cache);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
