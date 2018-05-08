package app.lz.com.webviewflingscrolldemo;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * Created by Zhen Li on 5/8/18.
 */
public class MyWebView extends WebView {

	interface ScrollChangeListener {
		void onScrollYChanged(int scrollY);
	}

	ScrollChangeListener scrollChangeListener;

	/**
	 * Constructs a new WebView with a Context object.
	 *
	 * @param context a Context object used to access application assets
	 */
	public MyWebView(Context context) {
		super(context);
	}

	/**
	 * Constructs a new WebView with layout parameters.
	 *  @param context a Context object used to access application assets
	 * @param attrs an AttributeSet passed to our parent
	 */
	public MyWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if (scrollChangeListener != null) {
			scrollChangeListener.onScrollYChanged(t);
		}
	}

	public void setScrollChangeListener(ScrollChangeListener scrollChangeListener) {
		this.scrollChangeListener = scrollChangeListener;
	}
}
