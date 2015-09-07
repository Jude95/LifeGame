package com.jude.lifegame.lifeview;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by Mr.Jude on 2015/9/6.
 */
public class LifeViewLayout extends FrameLayout {
    private ViewDragHelper mDragger;

    public LifeViewLayout(Context context) {
        super(context);
        init();
    }

    public LifeViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LifeViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mDragger = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback()
        {
            @Override
            public boolean tryCaptureView(View child, int pointerId){
                return true;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx){
                if (child.getWidth()<getWidth()){
                    if (left<0)return 0;
                    if (left+child.getWidth()>getWidth())return getWidth()-child.getWidth();
                } else{
                    if (left>0)return 0;
                    if (left+child.getWidth()<getWidth())return -(child.getWidth()-getWidth());
                }
                return left;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy){
                if (child.getHeight()<getHeight()){
                    if (top<0)return 0;
                    if (top+child.getHeight()>getHeight())return getHeight()-child.getHeight();
                }else{
                    if (top>0)return 0;
                    if (top+child.getHeight()<getHeight())return -(child.getHeight()-getHeight());
                }
                return top;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event)
    {
        return mDragger.shouldInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        mDragger.processTouchEvent(event);
        return true;
    }

}
