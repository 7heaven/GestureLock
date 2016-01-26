package com.example.gesturelock.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class GestureLockView extends View{
	
	public static final int MODE_NORMAL = 0x100;
	public static final int MODE_SELECTED = 0x200;
	public static final int MODE_ERROR = 0x400;
	
	public static final int ARROW_TOP = 0x1;
	public static final int ARROW_TOP_RIGHT = 0x2;
	public static final int ARROW_RIGHT = 0x4;
	public static final int ARROW_RIGHT_BOTTOM = 0x8;
	public static final int ARROW_BOTTOM = 0x10;
	public static final int ARROW_BOTTOM_LEFT = 0x20;
	public static final int ARROW_LEFT = 0x40;
	public static final int ARROW_LEFT_TOP = 0x80;
	
	private int mode = MODE_NORMAL;
	
	private int width;
	private int height;
	
	private int centerX;
	private int centerY;
	
	private Paint paint;
	
	private static final int COLOR_NORMAL = 0xFFFFFFFF;
	private static final int COLOR_ERROR = 0xFFFF0000;
	
	private int radius;
	private float innerRate = 0.2F;
	private float outerWidthRate = 0.15F;
	private float outerRate = 0.91F;
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
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		width = MeasureSpec.getSize(widthMeasureSpec);
		height = MeasureSpec.getSize(heightMeasureSpec);
		
		centerX = width / 2;
		centerY = height / 2;
		
		radius = width > height ? height : width;
		radius /= 2;
		
		if(arrow == null){
			arrowDistance = (int) (radius * arrowDistanceRate);
			
			int length = (int) (radius * arrowRate);
			arrow = new Path();
			arrow.moveTo(-length + centerX, length + centerY - arrowDistance);
			arrow.lineTo(centerX, centerY - arrowDistance);
			arrow.lineTo(length + centerX, length + centerY - arrowDistance);
			arrow.close();
			
		}
	}
	
	public void setMode(int mode){
		this.mode = mode;
		invalidate();
	}
	
	public int getMode(){
		return mode;
	}
	
	@Override
	public void onDraw(Canvas canvas){
		switch(mode & 0xF00){
		case MODE_NORMAL:
			paint.setStyle(Paint.Style.FILL);
			paint.setColor(COLOR_NORMAL);
			canvas.drawCircle(centerX, centerY, radius * innerRate, paint);
			break;
		case MODE_SELECTED:
			paint.setStyle(Paint.Style.STROKE);
			paint.setColor(COLOR_NORMAL);
			paint.setStrokeWidth(radius * outerWidthRate);
			canvas.drawCircle(centerX, centerY, radius * outerRate, paint);
			paint.setStrokeWidth(2);
			canvas.drawCircle(centerX, centerY, radius * innerRate, paint);
			break;
		case MODE_ERROR:
			paint.setStyle(Paint.Style.STROKE);
			paint.setColor(COLOR_ERROR);
			paint.setStrokeWidth(radius * outerWidthRate);
			canvas.drawCircle(centerX, centerY, radius * outerRate, paint);
			paint.setStrokeWidth(2);
			canvas.drawCircle(centerX, centerY, radius * innerRate, paint);
			break;
		}
		
		if((mode & 0xFF) > 0){
			paint.setStyle(Paint.Style.FILL);
			//paint.setColor(COLOR_ERROR);
			
			canvas.save();
			//canvas.translate(-centerX, -arrowDistance);
			switch(mode & 0xFF){
			case ARROW_TOP:
				break;
			case ARROW_TOP_RIGHT:
				canvas.rotate(45, centerX, centerY);
				break;
			case ARROW_RIGHT:
				canvas.rotate(90, centerX, centerY);
				break;
			case ARROW_RIGHT_BOTTOM:
				canvas.rotate(135, centerX, centerY);
				break;
			case ARROW_BOTTOM:
				canvas.rotate(180, centerX, centerY);
				break;
			case ARROW_BOTTOM_LEFT:
				canvas.rotate(-135, centerX, centerY);
				break;
			case ARROW_LEFT:
				canvas.rotate(-90, centerX, centerY);
				break;
			case ARROW_LEFT_TOP:
				canvas.rotate(-45, centerX, centerY);
				break;
			}
			
			canvas.drawPath(arrow, paint);
			
			canvas.restore();
		}
		
	}
	
}
