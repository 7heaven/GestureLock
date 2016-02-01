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
				return new int[]{0, 3, 6, 7, 8, 5, 2, 1, 4};
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
				if(position % 2 == 0){
					return new MyStyleLockView(context);
				}else{
					return new NexusStyleLockView(context);
				}
			}
		});	

```
  

####getDepth() 手势解锁的宽高数量
####getCorrectGestures() 正确的解锁手势
####getUnmatchedBoundary() 最大可重试次数
####getBlockGapSize() block之前的间隔大小
####getGestureLockViewInstance(Context context, int position) block的样式


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