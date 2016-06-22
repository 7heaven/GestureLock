package com.example.gesturelock;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.example.gesturelock.widget.MyStyleLockView;
import com.sevenheaven.gesturelock.GestureLock;
import com.sevenheaven.gesturelock.GestureLockView;

public class MainActivity extends Activity {

	private GestureLock gestureView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		gestureView = (GestureLock) findViewById(R.id.gesture_lock);
		gestureView.setAdapter(new GestureLock.GestureLockAdapter() {
			@Override
			public int getDepth() {
				return 3;
			}

			@Override
			public int[] getCorrectGestures() {
				return new int[]{0, 1,2};
			}

			@Override
			public int getUnmatchedBoundary() {
				return 5;
			}

			@Override
			public int getBlockGapSize(){
				return 100;
			}

			@Override
			public GestureLockView getGestureLockViewInstance(Context context, int position) {
                return new MyStyleLockView(context);
				/*if(position % 2 == 0){
					return new MyStyleLockView(context);
				}else{
					return new NexusStyleLockView(context);
				}*/
			}
		});

		gestureView.setOnGestureEventListener(new GestureLock.OnGestureEventListener(){

			@Override
			public void onGestureEvent(boolean matched) {
				Toast.makeText(MainActivity.this, "Match:" + matched, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onUnmatchedExceedBoundary() {
				Toast.makeText(MainActivity.this, "输入5次错误!30秒后才能输入", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onBlockSelected(int position) {
				Log.d("position", position + "");
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
