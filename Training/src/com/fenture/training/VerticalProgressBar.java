package com.fenture.training;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

/**
 * 
 * @author Constantin
 * {@link http://www.jayway.com/2012/07/03/creating-custom-android-views-part-2-how-padding-works-and-how-to-draw-your-own-content/}
 */
public class VerticalProgressBar extends View {

	private static final int DEFAULT_STROKE_WIDTH = 3;
	private static final int DEFAULT_COLOR = Color.LTGRAY;
	private static final int PROGRESS_COLOR = Color.WHITE;
	private static final int DEFAULT_PROGRESS = 0;
	private static final int DEFAULT_MAX_PROGRESS = 100;
	
	private Paint mPaint;
	private int mStrokeWidth = DEFAULT_STROKE_WIDTH;
	private int mColor = DEFAULT_COLOR;
	private int mProgress = DEFAULT_PROGRESS;
	private Paint cPaint;

	public VerticalProgressBar(Context context) {
		super(context);
		init(null);
	}

	public VerticalProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
	}

	public VerticalProgressBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(attrs);
	}
	
	@Override
	protected void onMeasure(int widthSpec, int heightSpec) {
		super.onMeasure(widthSpec, heightSpec);
		setMeasuredDimension(getMeasuredHeight() / 10, getMeasuredHeight());
	}
	
	private void init(AttributeSet attrs) {
		final int width = getWidth();
		final int height = getHeight();
		if (attrs != null) {
			String namespace = "http://schemas.android.com/apk/res-auto";
			final String xmlns="http://schemas.android.com/apk/res/android";
			mStrokeWidth = attrs.getAttributeIntValue(namespace, "stroke_width", DEFAULT_STROKE_WIDTH);
			mColor = attrs.getAttributeIntValue(namespace, "color", PROGRESS_COLOR);
			mProgress = attrs.getAttributeIntValue(xmlns, "progress", DEFAULT_PROGRESS);
			if(mProgress > DEFAULT_MAX_PROGRESS)
				mProgress = DEFAULT_MAX_PROGRESS;
		}
		
		//main line
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setColor(mColor);
		Log.d("VPB", String.valueOf(width / 4) );
		mPaint.setStyle(Paint.Style.STROKE);
		//circle
		cPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		cPaint.setColor(Color.BLUE);
		cPaint.setStyle(Paint.Style.FILL);
	}
	
	public void onDraw(Canvas canvas) {
		final int width = getWidth();
		final int height = getHeight();
		canvas.save();
		mPaint.setStrokeWidth(width / 5);
		canvas.drawLine(width/2, 1, width/2, height - 1, mPaint);
		mPaint.setColor(Color.BLUE);
		canvas.drawLine(width/2, height/30, width/2, height * mProgress / DEFAULT_MAX_PROGRESS - 1, mPaint);
		canvas.drawCircle(width/2, height/30 + 1, height/30, cPaint);
		
		super.onDraw(canvas);
		canvas.restore();
	}

	@Override
	public void draw(Canvas canvas) {
		float py = this.getHeight()/2.0f;
		float px = this.getWidth()/2.0f;
		canvas.rotate(-180, px, py);
		super.draw(canvas);
		
	}
	
	
	
}
