#GestureLock
===========

###GestureLock是一个可以自定义数量、自定义样式的手势解锁控件

##用法

####使用GestureLock类提供的Adapter来定制样式
```java
    gestureView.setAdapter(new GestureLock.GestureLockAdapter() {

			@Override
			public int getDepth() {
				return 7;
			}

			@Override
			public int[] getCorrectGestures() {
				return new int[]{1, 2, 3, 4};
			}

			@Override
			public int getUnmatchedBoundary() {
				return 5;
			}

			@Override
			public int getBlockGapSize(){
				return 10;
			}

			@Override
			public GestureLockView getGestureLockViewInstance(Context context, int position) {
				return new NexusStyleLockView(context);
			}
		});	

```


#####getDepth() 手势解锁的宽高数量
#####getCorrectGestures() 正确的解锁手势
#####getUnmatchedBoundary() 最大可重试次数
#####getBlockGapSize() block之前的间隔大小
#####getGestureLockViewInstance(Context context, int position) block的样式


####继承GestureLockView来实现自定义样式的block

#####重写doArrowDraw绘制箭头(箭头角度0的时候为向上)

#####重写onDraw实现Block内容样式的绘制

Block分为三种状态

* LockerState.LOCKER_STATE_NORMAL 正常状态
* LockerState.LOCKER_STATE_SELECTED 选中状态
* LockerState.LOCKER_STATE_ERROR 错误状态


```java

    @Override
    protected void doArrowDraw(Canvas canvas){
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(COLOR_ERROR);
        canvas.drawPath(arrow, mPaint);
    }

    @Override
    protected void doDraw(LockerState state, Canvas canvas){
        switch(state){
            case LOCKER_STATE_NORMAL:
                mPaint.setStyle(Paint.Style.FILL);
                mPaint.setColor(COLOR_NORMAL);
			    canvas.drawCircle(mCenterX, mCenterY, mRadius * innerRate, mPaint);
			break;
            case LOCKER_STATE_SELECTED:
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setColor(COLOR_NORMAL);
                mPaint.setStrokeWidth(mRadius * outerWidthRate);
			    canvas.drawCircle(mCenterX, mCenterY, mRadius * outerRate, mPaint);
                mPaint.setStrokeWidth(2);
			    canvas.drawCircle(mCenterX, mCenterY, mRadius * innerRate, mPaint);
			break;
            case LOCKER_STATE_ERROR:
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setColor(COLOR_ERROR);
                mPaint.setStrokeWidth(mRadius * outerWidthRate);
			    canvas.drawCircle(mCenterX, mCenterY, mRadius * outerRate, mPaint);
                mPaint.setStrokeWidth(2);
			    canvas.drawCircle(mCenterX, mCenterY, mRadius * innerRate, mPaint);
			break;
		}
    }
    
```


![](https://raw.githubusercontent.com/7heaven/GestureLock/master/art/art1.png)
![](https://raw.githubusercontent.com/7heaven/GestureLock/master/art/art2.png)
![](https://raw.githubusercontent.com/7heaven/GestureLock/master/art/art3.png)
![](https://raw.githubusercontent.com/7heaven/GestureLock/master/art/art4.png)
![](https://raw.githubusercontent.com/7heaven/GestureLock/master/art/art5.png)
![](https://raw.githubusercontent.com/7heaven/GestureLock/master/art/art6.png)
![](https://raw.githubusercontent.com/7heaven/GestureLock/master/art/art7.png)
![](https://raw.githubusercontent.com/7heaven/GestureLock/master/art/art8.png)
![](https://raw.githubusercontent.com/7heaven/GestureLock/master/art/art9.png)
![](https://raw.githubusercontent.com/7heaven/GestureLock/master/art/art10.png)
![](https://raw.githubusercontent.com/7heaven/GestureLock/master/art/art11.png)
![](https://raw.githubusercontent.com/7heaven/GestureLock/master/art/art12.png)