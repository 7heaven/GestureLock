#GestureLock
===========

###GestureLock是一个可以自定义数量、自定义样式的手势解锁控件

##用法
####使用GestureLock类提供的Adapter来定制样式

```java
public interface GestureLockAdapter{
        int getDepth();
        int[] getCorrectGestures();
        int getUnmatchedBoundary();
        int getBlockGapSize();
        GestureLockView getGestureLockViewInstance(Context context, int position);
  }```

<!--![](https://raw.githubusercontent.com/7heaven/GestureLock/master/art/art1.png)
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
![](https://raw.githubusercontent.com/7heaven/GestureLock/master/art/art12.png)-->