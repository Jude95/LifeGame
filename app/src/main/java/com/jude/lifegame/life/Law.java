package com.jude.lifegame.life;

import java.io.Serializable;

/**
 * Created by Mr.Jude on 2015/9/4.
 */
public class Law  implements Serializable {
    int[] S = {2,3};//存货水平
    int[] B = {3};//出生水平

    public Law(int[] b,int[] s) {
        S = s;
        B = b;
    }

    public static final Law B3_S23 = new Law(new int[]{3},new int[]{2,3});
    public static final Law B234_S234 = new Law(new int[]{2,3,4},new int[]{2,3,4});
    public static final Law B1_S12345678 = new Law(new int[]{1},new int[]{1,2,3,4,5,6,7,8});
    public static final Law B1234_S5678 = new Law(new int[]{1,2,3,4},new int[]{5,6,7,8});
    public static final Law B1234_S1234 = new Law(new int[]{1,2,3,4},new int[]{1,2,3,4});
}
