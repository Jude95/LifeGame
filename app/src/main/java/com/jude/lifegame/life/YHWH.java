package com.jude.lifegame.life;

/**
 * Created by Mr.Jude on 2015/9/4.
 */
public class YHWH extends Thread {
    private Space mSpace;
    private Time mTime;
    private Law mLaw;
    private Archangel mArchangel;
    private boolean mPower;

    private void init(){
        mArchangel = new Archangel(mSpace);
        mPower = true;
    }


    public static Archangel CreateWorld(Space space, Time time, Law law){
        YHWH thread = new YHWH();
        thread.mSpace = space;
        thread.mTime = time;
        thread.mLaw = law;
        thread.init();
        thread.start();
        return thread.mArchangel;
    }

    @Override
    public void run() {
        while (mPower){

            if (mTime.unit>0){
                mTime.delay();
            }

            boolean[][] space = mSpace.space;
            int level;

            height:for (int h = 1; h < mSpace.height-1; h++) {
                width:for (int w = 1; w < mSpace.width-1; w++) {
                    int count = 0;
                    if (space[w- 1][ h- 1]) { count++; }
                    if (space[w- 1][ h]) { count++; }
                    if (space[w- 1][ h + 1]) { count++; }
                    if (space[w][ h-1]) { count++; }
                    if (space[w][ h +1]) { count++; }
                    if (space[w+ 1][ h-1]) { count++; }
                    if (space[w+ 1][ h]) { count++; }
                    if (space[w+ 1][ h+1]) { count++; }

                    level = 0;
                    for (int i = 0; i < mLaw.S.length; i++) {
                        if (count == mLaw.S[i]){
                            level = 1;
                            break;
                        }
                    }

                    for (int i = 0; i < mLaw.B.length; i++) {
                        if (count == mLaw.B[i]){
                            level = 2;
                            break;
                        }
                    }

                    switch (level){
                        case 0:mArchangel.die(w,h);break ;
                        case 2:mArchangel.born(w,h);break;
                    }
                }
            }
            mArchangel.publish();
        }
    }


}
