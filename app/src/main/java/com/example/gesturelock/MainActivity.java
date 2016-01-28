package com.example.gesturelock;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.example.gesturelock.widget.GestureLock;
import com.example.gesturelock.widget.GestureLock.OnGestureEventListener;
import com.example.gesturelock.widget.GestureLockView;
import com.example.gesturelock.widget.NexusStyleLockView;

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
				return 5;
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
			public GestureLockView getGestureLockViewInstance(Context context) {
				return new NexusStyleLockView(context);
			}
		});
//		gestureView.setCorrectGesture(new int[]{0, 3, 6, 7, 8, 5, 2, 1, 4});
		//gestureView.setMode(GestureLock.MODE_EDIT);
		//gestureView.setMode(GestureLockView.MODE_ERROR | GestureLockView.ARROW_BOTTOM_LEFT);
		gestureView.setOnGestureEventListener(new OnGestureEventListener(){

			@Override
			public void onGestureEvent(boolean matched) {
				Toast.makeText(MainActivity.this, "Match:" + matched, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onUnmatchedExceedBoundary() {
				Toast.makeText(MainActivity.this, "输入5次错误!30秒后才能输入", Toast.LENGTH_SHORT).show();
				/*
				gestureView.setTouchable(false);
				new Handler().postDelayed(new Runnable(){
					@Override
					public void run(){
						gestureView.setTouchable(true);
					}
				}, 30000);
				 */
			}

			@Override
			public void onBlockSelected(int position) {
				Log.d("position", position + "");
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
