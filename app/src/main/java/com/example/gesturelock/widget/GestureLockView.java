package com.example.gesturelock.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public abstract class GestureLockView extends View{

	public enum LockerState{
		LOCKER_STATE_NORMAL, LOCKER_STATE_ERROR, LOCKER_STATE_SELECTED
	}

	private LockerState mState = LockerState.LOCKER_STATE_NORMAL;

	private int errorArrow = -1;
	
	private int width;
	private int height;
	
	private int centerX;
	private int centerY;
	
	private Paint paint;
	
	private int radius;
	private float arrowRate = 0.25F;
	private float arrowDistanceRate = 0.0F;
	private int arrowDistance;
	
	private Path arrow;

	public GestureLockView(Context context){
		this(context, null);
	}
	
	public GestureLockView(Context context, AttributeSet attrs){
		this(context, attrs, 0);
	}
	
	public GestureLockView(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle);
		
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh){
		super.onSizeChanged(w, h, oldw, oldh);

		width = w;
		height = h;

		centerX = width / 2;
		centerY = height / 2;

		radius = width > height ? height : width;
		radius /= 2;

		if(arrow == null){
			arrowDistance = (int) (radius * arrowDistanceRate);

			arrow = getArrowShape(centerX, centerY);
			if(arrow != null) arrow.offset(0, -arrowDistance);

		}

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

	protected Path getArrowShape(int centerX, int centerY){

		int length = (int) (radius * arrowRate);
		Path arrowPath = new Path();
		arrowPath.moveTo(-length + centerX, length + centerY);
		arrowPath.lineTo(centerX, centerY);
		arrowPath.lineTo(length + centerX, length + centerY);
		arrowPath.close();

		return arrowPath;
	}

	protected abstract void doDraw(LockerState state, Canvas canvas);
	
	@Override
	public void onDraw(Canvas canvas){

		doDraw(mState, canvas);

		if(errorArrow != -1 && arrow != null){
			paint.setStyle(Paint.Style.FILL);
			paint.setColor(0xFFFF0000);

			canvas.save();
			canvas.rotate(errorArrow, centerX, centerY);
			canvas.drawPath(arrow, paint);

			canvas.restore();
		}
		
	}
	
}
