package com.jude.lifegame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.jude.lifegame.life.Archangel;
import com.jude.lifegame.life.Law;
import com.jude.lifegame.life.Space;
import com.jude.lifegame.life.Time;
import com.jude.lifegame.life.YHWH;
import com.jude.lifegame.lifeview.LifeView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final int Width = 120;
    private static final int Height = 120;
    private static final int TimeUnit = 100;
    private static final int SizeUnit = 2;
    private int unit = 20;

    LifeView mLifeView;
    Time mTime;
    Space mSpace;
    Button btnPause;
    Button btnStart;
    Button btnStep;
    Button btnRePlay;
    Button btnBigger;
    Button btnSmaller;
    SeekBar seekBarSpeed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLifeView = (LifeView) findViewById(R.id.life);
        btnPause = (Button) findViewById(R.id.pause);
        btnStart = (Button) findViewById(R.id.start);
        btnStep = (Button) findViewById(R.id.step);
        btnRePlay = (Button) findViewById(R.id.replay);
        btnBigger = (Button) findViewById(R.id.bigger);
        btnSmaller = (Button) findViewById(R.id.smaller);
        seekBarSpeed = (SeekBar) findViewById(R.id.speed);

        initSpace(mSpace = new Space(Width,Height));

        mLifeView.setMap(mSpace.space);

        mTime = new Time(TimeUnit);
        mTime.pause();

        mLifeView.setUnit(unit);
        Archangel angle = YHWH.CreateWorld(mSpace, mTime, Law.B3_S23);
        angle.registerObserver(mLifeView);

        btnBigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLifeView.setUnit(unit = SizeUnit * (unit / SizeUnit + 1));
            }
        });
        btnSmaller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLifeView.setUnit(unit = SizeUnit * (unit / SizeUnit - 1));
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTime.pause();
            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTime.resume();
            }
        });
        btnStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTime.next();
            }
        });
        btnRePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSpace(mSpace);
                mLifeView.setMap(mSpace.space);
            }
        });
        seekBarSpeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mTime.setUnit((progress * progress / 10 + 50) * TimeUnit / 100);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void initSpace(Space space){
        space.clear();
        Random random = new Random();
        for (int i = 0; i < 1; i++) {
            draw1(space.space,random.nextInt(Width - 20) + 10, random.nextInt(Height - 20) + 10);
            draw2(space.space, random.nextInt(Width - 20) + 10, random.nextInt(Height - 20) + 10);
            draw3(space.space, random.nextInt(Width - 20) + 10, random.nextInt(Height - 20) + 10);
            draw4(space.space, random.nextInt(space.width - 20) + 10, random.nextInt(space.height - 20) + 10);
            draw5(space.space, random.nextInt(space.width - 20) + 10, random.nextInt(space.height - 20) + 10);
        }
    }


    /**
     *   *
     *  * *
     *  * *
     *   *
     */
    public void draw1(boolean[][] space,int beginX,int beginY){
        space[beginX+1][beginY] =true;
        space[beginX][beginY+1] =true;
        space[beginX][beginY+2] =true;
        space[beginX][beginY+3] =true;
        space[beginX+1][beginY+3] =true;
        space[beginX+2][beginY+1] =true;
        space[beginX+2][beginY+2] =true;
        space[beginX+2][beginY+3] =true;
    }

    /**
     *   ***
     *    *
     *    *
     */
    public void draw2(boolean[][] space,int beginX,int beginY){
        space[beginX][beginY] =true;
        space[beginX+1][beginY] =true;
        space[beginX+2][beginY] =true;
        space[beginX+1][beginY+1] =true;
        space[beginX+1][beginY+2] =true;
    }

    /**
     *   *
     *  * *
     *  * *
     *   *
     */
    public void draw3(boolean[][] space,int beginX,int beginY){
        space[beginX][beginY] =true;
        space[beginX][beginY+1] =true;
        space[beginX][beginY+2] =true;
        space[beginX+1][beginY] =true;
        space[beginX+1][beginY+2] =true;
        space[beginX+2][beginY] =true;
        space[beginX+2][beginY+1] =true;
        space[beginX+2][beginY+2] =true;
    }

    /**
     *   *
     *  * *
     *  * *
     *   *
     */
    public void draw4(boolean[][] space,int beginX,int beginY){
        space[beginX][beginY] =true;
        space[beginX+1][beginY] =true;
        space[beginX+2][beginY] =true;
        space[beginX+3][beginY] =true;
        space[beginX+4][beginY] =true;
        space[beginX+5][beginY] =true;
        space[beginX+6][beginY] =true;

        space[beginX][beginY+1] =true;
        space[beginX+8][beginY+1] =true;


        space[beginX+2][beginY+2] =true;
        space[beginX+3][beginY+2] =true;
        space[beginX+4][beginY+2] =true;
        space[beginX+5][beginY+2] =true;
        space[beginX+6][beginY+2] =true;
        space[beginX+7][beginY+2] =true;
        space[beginX+8][beginY+2] =true;
    }
    /**
     *   ***
     */
    public void draw5(boolean[][] space,int beginX,int beginY){
        space[beginX][beginY] =true;
        space[beginX][beginY+1] =true;
        space[beginX][beginY+2] =true;
    }
}
