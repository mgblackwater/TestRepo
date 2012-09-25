package com.androidmagazine.shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class Line extends Shape {

	private int x1,y1,x2,y2;
	
	public int getX1() {
		return x1;
	}


	public void setX1(int x1) {
		this.x1 = x1;
	}


	public int getY1() {
		return y1;
	}


	public void setY1(int y1) {
		this.y1 = y1;
	}


	public int getX2() {
		return x2;
	}


	public void setX2(int x2) {
		this.x2 = x2;
	}


	public int getY2() {
		return y2;
	}


	public void setY2(int y2) {
		this.y2 = y2;
	}


	public Line(Context context,Frame frame,Stroke stroke) {
		
		super(context,frame,stroke);
		
	}


	@Override
	protected void drawShape(Canvas canvas) {
		// TODO Auto-generated method stub

		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(stroke.getLineColor());
		paint.setStrokeWidth(stroke.getLineWidth());
		
		paint.setStyle(Paint.Style.STROKE);
		
		Path path = new Path();
		path.moveTo(x1,y1);
		path.lineTo(x2, y2);
			
	    canvas.drawPath(path, paint);
	     	
	}

	

	
}
