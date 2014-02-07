package com.example.myfirstanimation;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebView;

public final class SwapViews implements Runnable {
private boolean mIsFirstView;
WebView web1;
WebView web2;

public SwapViews(boolean isFirstView, WebView image1, WebView image2) {
 mIsFirstView = isFirstView;
 this.web1 = image1;
 this.web2 = image2;
}

public void run() {
 final float centerX = web1.getWidth() / 2.0f;
 final float centerY = web1.getHeight() / 2.0f;
 Flip3dAnimation rotation;

 if (mIsFirstView) {
  web1.setVisibility(View.GONE);
  web2.setVisibility(View.VISIBLE);
  web2.requestFocus();

     rotation = new Flip3dAnimation(-90, 0, centerX, centerY);
 } else {
  web2.setVisibility(View.GONE);
  web1.setVisibility(View.VISIBLE);
  web1.requestFocus();

     rotation = new Flip3dAnimation(90, 0, centerX, centerY);
 }

 rotation.setDuration(500);
 rotation.setFillAfter(true);
 rotation.setInterpolator(new DecelerateInterpolator());

 /*if (mIsFirstView) {
  web2.startAnimation(rotation);
 } else {
  web1.startAnimation(rotation);
 }*/
}
}