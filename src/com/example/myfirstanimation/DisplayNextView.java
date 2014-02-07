package com.example.myfirstanimation;


import android.view.animation.Animation;
import android.webkit.WebView;
import android.widget.ImageView;

public final class DisplayNextView implements Animation.AnimationListener {
private boolean mCurrentView;
WebView web1;
WebView web2;

public DisplayNextView(boolean currentView, WebView image1, WebView image2) {
mCurrentView = currentView;
this.web1 = image1;
this.web2 = image2;
}

public void onAnimationStart(Animation animation) {
}

public void onAnimationEnd(Animation animation) {
web1.post(new SwapViews(mCurrentView, web1, web2));
}

public void onAnimationRepeat(Animation animation) {
}
}