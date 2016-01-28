package com.example.gesturelock.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public abstract class GestureLockView extends View{

	private int mWidth, mHeight;
	private int mCenterX, mCenterY;

	public enum LockerState{
		LOCKER_STATE_NORMAL, LOCKER_STATE_ERROR, LOCKER_STATE_SELECTED
	}

	private LockerState mState = LockerState.LOCKER_STATE_NORMAL;

	private int errorArrow = -1;

	public GestureLockView(Context context){
		this(context, null);
	}
	
	public GestureLockView(Context context, AttributeSet attrs){
		this(context, attrs, 0);
	}
	
	public GestureLockView(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle);

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh){

		mWidth = w;
		mHeight = h;

		mCenterX = mWidth / 2;
		mCenterY = mHeight / 2;
	}
	
	public void setLockerState(LockerState state){
		mState = state;
		invalidate();
	}
	
	public LockerState getLockerState(){
		return mState;
	}

	public void setArrow(int arrow){
		errorArrow = arrow;

		invalidate();
	}

	public int getArrow(){
		return errorArrow;
	}

	protected abstract void doDraw(LockerState state, Canvas canvas);
	protected abstract void doArrowDraw(Canvas canvas);
	
	@Override
	public void onDraw(Canvas canvas){

		doDraw(mState, canvas);

		if(errorArrow != -1){

			canvas.save();
			canvas.rotate(errorArrow, mCenterX, mCenterY);
			doArrowDraw(canvas);

			canvas.restore();
		}
		
	}
	
}
