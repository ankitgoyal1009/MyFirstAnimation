package com.example.myfirstanimation;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;

public class MainActivity extends Activity {
	WebView questionImage, answerImage;
	Animation zoomInAnimation;
	Animation zoomOutAnimation;
	GestureDetector gesture;
	SimpleOnGestureListener simpleOnGestureListener;
	private boolean isAnswerVisible;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		questionImage = (WebView) findViewById(R.id.first_image);
		answerImage = (WebView) findViewById(R.id.second_image);
		onImageClick imageClickListner = new onImageClick();
		onWebTouchListner touchListner = new onWebTouchListner();
		FlipAnimationListner flipAnimation = new FlipAnimationListner();
		questionImage.setOnClickListener(imageClickListner);
		answerImage.setOnClickListener(imageClickListner);
		questionImage.loadUrl("google.com");
		answerImage.loadUrl("yahoo.com");
		questionImage.setOnTouchListener(touchListner);
		//answerImage.setOnTouchListener(touchListner);
		
		zoomInAnimation = AnimationUtils.loadAnimation(MainActivity.this,
				R.anim.zoom_in);
		zoomOutAnimation = AnimationUtils.loadAnimation(MainActivity.this,
				R.anim.zoom_in);
		zoomInAnimation.setAnimationListener(flipAnimation);
		simpleOnGestureListener = new SimpleOnGestureListener() {
			public boolean onDoubleTap(MotionEvent e) {

				flipCard();
				return false;

			};

		};
		gesture = new GestureDetector(this, simpleOnGestureListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	class onWebTouchListner implements OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			return gesture.onTouchEvent(event);
		}

	}

	void flipCard() {
		if (!isAnswerVisible) {
			//questionImage.setVisibility(View.VISIBLE);
			questionImage.startAnimation(zoomInAnimation);
			//secondImage.setVisibility(View.INVISIBLE);
			//secondImage.startAnimation(zoomOutAnimation);
			//isAnswerVisible = true;
		} else {
			//answerImage.setVisibility(View.VISIBLE);
			answerImage.startAnimation(zoomInAnimation);
			//firstImage.setVisibility(View.INVISIBLE);
			//firstImage.startAnimation(zoomOutAnimation);
			//isAnswerVisible = false;
		}
	}
class FlipAnimationListner implements AnimationListener{

	@Override
	public void onAnimationEnd(Animation animation) {
		Log.e("Animation", "onAnimationEnd::"+isAnswerVisible);
		if (!isAnswerVisible) {
			/*questionImage.setVisibility(View.VISIBLE);
			questionImage.startAnimation(zoomInAnimation);*/
			questionImage.setVisibility(View.INVISIBLE);
			answerImage.setVisibility(View.VISIBLE);
			answerImage.startAnimation(zoomOutAnimation);
			isAnswerVisible = true;
		} else {
			/*answerImage.setVisibility(View.VISIBLE);
			answerImage.startAnimation(zoomInAnimation);*/
			answerImage.setVisibility(View.INVISIBLE);
			questionImage.setVisibility(View.VISIBLE);
			questionImage.startAnimation(zoomOutAnimation);
			isAnswerVisible = false;
		}
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		Log.e("Animation", "onAnimationRepeat");
		
	}

	@Override
	public void onAnimationStart(Animation animation) {
		Log.e("Animation", "onAnimationStart");
		
	}
	
}
	class onImageClick implements OnClickListener {

		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.second_image) {
				questionImage.setVisibility(View.VISIBLE);
				questionImage.startAnimation(zoomInAnimation);
				// secondImage.startAnimation(zoomInAnimation);
				answerImage.setVisibility(View.INVISIBLE);
			} else {
				answerImage.setVisibility(View.VISIBLE);
				answerImage.startAnimation(zoomInAnimation);
				// secondImage.startAnimation(zoomInAnimation);
				// firstImage.startAnimation(zoomInAnimation);
				questionImage.setVisibility(View.INVISIBLE);
			}
		}

	}
}
