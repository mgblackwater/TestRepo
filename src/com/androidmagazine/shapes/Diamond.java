package com.androidmagazine.shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class Diamond extends Shape {

	private float len1; 
	public float getLen1() {
		return len1;
	}

	public void setLen1(float len1) {
		this.len1 = len1;
	}

	public float getLen2() {
		return len2;
	}

	public void setLen2(float len2) {
		this.len2 = len2;
	}

	private float len2;
	
	public Diamond(Context context, Frame frame, Stroke stroke) {
		super(context, frame, stroke);
		
	}

	@Override
	protected void drawShape(Canvas canvas) {
		
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(this.getFillColor());
		paint.setStyle(Paint.Style.FILL);
			
		
		Path path = new Path();
		
		path.moveTo(len1, 0);
		path.lineTo(frame.getWidth(),frame.getHeight()-len2);
		path.lineTo(frame.getWidth()-len1,frame.getHeight());
		path.lineTo(0,len2);
		path.close();
		
		canvas.drawPath(path, paint);
		
		
	}

}
