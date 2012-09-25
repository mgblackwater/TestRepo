package com.androidmagazine.shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.FillType;

public class Square extends Shape {

	public Square(Context context, Frame frame, Stroke stroke) {
		super(context, frame, stroke);
		
	}

	@Override
	protected void drawShape(Canvas canvas) {
	
		int x=0;
		int y= 0;
		
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
     	
     	Path path = new Path();
		
		paint.setColor(this.getFillColor());
     	paint.setStyle(Paint.Style.FILL);
     	
		path.moveTo(x,y);
		path.lineTo(frame.getWidth(), y);
		path.lineTo(frame.getWidth(), y+frame.getHeight());
		path.lineTo(x, frame.getHeight());
		path.close();
		
		canvas.drawPath(path, paint);
		
		int strokeWidth = stroke.getLineWidth();

		paint.setColor(Color.GREEN);
     	paint.setStyle(Paint.Style.STROKE);
     	paint.setStrokeWidth(frame.getWidth());
     	
		path.moveTo(x+strokeWidth,y+strokeWidth);
		path.lineTo(x+frame.getWidth()-strokeWidth, y+strokeWidth);
		path.lineTo(x+frame.getWidth()-strokeWidth, y+frame.getHeight()-strokeWidth);
		path.lineTo(x+strokeWidth, y+frame.getHeight()-strokeWidth);
		path.close();
		canvas.drawPath(path, paint);

		
		
	
	}

	
	
}
