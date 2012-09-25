package com.androidmagazine.shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class Polygon extends Shape {

	int numberOfSides;
	
	public int getNumberOfSides() {
		return numberOfSides;
	}

	public void setNumberOfSides(int numberOfSides) {
		this.numberOfSides = numberOfSides;
	}

	public Polygon(Context context, Frame frame, Stroke stroke) {
		super(context, frame, stroke);
		
	}

	@Override
	protected void drawShape(Canvas canvas) {
		
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(this.getFillColor());
		
		paint.setStyle(Paint.Style.FILL);
		
		float centerX = frame.getWidth()/2;
		float centerY = frame.getHeight()/2;
		
		Path path = new Path();
		
		path.moveTo(centerX,frame.getHeight());
		float offsetX,offsetY;
		
		for (int i=1; i<numberOfSides; ++i) 
		{
			offsetX= frame.getWidth() * 0.5f * ((float)Math.sin(i*2.0*Math.PI/numberOfSides));
			offsetY= frame.getHeight() * 0.5f *((float) Math.cos(i*2.0*Math.PI/numberOfSides));
			path.lineTo(centerX+offsetX,centerY+offsetY);
		}
		path.close();
		
		canvas.drawPath(path, paint);
		
		
	}

}
