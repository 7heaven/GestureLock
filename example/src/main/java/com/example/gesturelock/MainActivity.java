package com.example.gesturelock;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.example.gesturelock.widget.NexusStyleLockView;
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
