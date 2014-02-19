package com.vjaya.sampleec;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.Toast;

@SuppressLint("DrawAllocation")
public class DrawImage extends RelativeLayout {

	private static final String TAG = "DrawImage";
	
	private float startX;
	private float startY;
	private float stopX;
	private float stopY;
	
	private Context mContext;
	private RectF mHitPt = new RectF();
	private int mSlop = 5;
	
	public DrawImage(Context context) {
		super(context);
		this.setWillNotDraw(false);
		mContext = context;
	}

	public DrawImage(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setWillNotDraw(false);
		mContext = context;
	}

	public DrawImage(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.setWillNotDraw(false);
		mContext = context;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint paint = new Paint();

		startX = this.getWidth() * 0.6f;
		startY = this.getHeight() * 0.449f;
		stopX = this.getWidth() * 0.67f;
		stopY = this.getHeight() * 0.45f;
		
		mHitPt.set(startX, startY, stopX, stopY);
		Log.w(TAG, "onDraw mHitPt:" + mHitPt);
		
		

		Resources res = getResources();
		Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.ec);
		canvas.drawBitmap(bitmap, 0, 0, paint);
		
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(Color.BLUE);
		paint.setStrokeWidth(4);
		
		canvas.drawLine(startX, startY, stopX, stopY, paint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		invalidate();
		int x = (int) e.getX();
		int y = (int) e.getY();
		
		if(perfectPt(mHitPt, x, y)){
			Toast.makeText(mContext, "Wow...", Toast.LENGTH_LONG).show();
		}
		return super.onTouchEvent(e);
	}

	
	private boolean perfectPt(RectF r, int x, int y) {
		RectF overSizeRect = new RectF(r);
		overSizeRect.inset(-mSlop , -mSlop);
		return overSizeRect.contains(x, y);
	}
}
