package com.androidmagazine.shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class Ellipse extends Shape {

	public Ellipse(Context context, Frame frame, Stroke stroke) {
		super(context, frame, stroke);
		
	}

	@Override
	protected void drawShape(Canvas canvas) {
		
		int x=0;
		int y= 0;
		int width = frame.getWidth();
		int height = frame.getHeight();
		
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(this.getFillColor());
     	paint.setStyle(Paint.Style.FILL);
		
     	RectF rect = new RectF(x,y,x+width,y+height);
		
		canvas.drawOval(rect,paint);
		
		
	}

}
