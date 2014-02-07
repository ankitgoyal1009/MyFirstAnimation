package com.example.myfirstanimation;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AccelerateInterpolator;
import android.webkit.WebView;

public class Flip3d extends Activity implements OnTouchListener {

	private WebView web1;
	private WebView web2;
	SimpleOnGestureListener simpleOnGestureListener;
	GestureDetector gesture;
	private boolean isFirstImage = true;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		web1 = (WebView) findViewById(R.id.WebView01);
		web2 = (WebView) findViewById(R.id.WebView02);
		web2.setVisibility(View.GONE);
		web1.loadUrl("file:///android_asset/index.html");
		web2.loadUrl("http://yahoo.com");
		web1.setOnTouchListener(this);
		web2.setOnTouchListener(this);

		/*
		 * web1.setOnClickListener(new View.OnClickListener() { public void
		 * onClick(View view) { if (isFirstImage) { applyRotation(0, 90);
		 * isFirstImage = !isFirstImage;
		 * 
		 * } else { applyRotation(0, -90); isFirstImage = !isFirstImage; } } });
		 */simpleOnGestureListener = new SimpleOnGestureListener() {
			public boolean onDoubleTap(MotionEvent e) {
				flipCard();
				return false;
			};
		};
		gesture = new GestureDetector(this, simpleOnGestureListener);
	}

	void flipCard() {

		if (isFirstImage) {
			applyRotation(0, 90);
			isFirstImage = !isFirstImage;

		} else {
			applyRotation(0, -90);
			isFirstImage = !isFirstImage;
		}

	}

	private void applyRotation(float start, float end) {
		// Find the center of image
		final float centerX = web1.getWidth() / 2.0f;
		final float centerY = web1.getHeight() / 2.0f;

		// Create a new 3D rotation with the supplied parameter
		// The animation listener is used to trigger the next animation
		final Flip3dAnimation rotation = new Flip3dAnimation(start, end,
				centerX, centerY);
		rotation.setDuration(3000);
		rotation.setFillAfter(true);
		rotation.setInterpolator(new AccelerateInterpolator());
		rotation.setAnimationListener(new DisplayNextView(isFirstImage, web1,web2));

		if (isFirstImage) {
			web1.startAnimation(rotation);
		} else {
			web2.startAnimation(rotation);
		}

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return gesture.onTouchEvent(event);
	}
}