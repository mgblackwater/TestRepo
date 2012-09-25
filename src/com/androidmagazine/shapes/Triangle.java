package com.androidmagazine.shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

public class Triangle extends Shape {

	
	Point point1,point2,point3;
	
	public Point getPoint1() {
		return point1;
	}

	public void setPoint1(Point point1) {
		this.point1 = point1;
	}

	public Point getPoint2() {
		return point2;
	}

	public void setPoint2(Point point2) {
		this.point2 = point2;
	}

	public Point getPoint3() {
		return point3;
	}

	public void setPoint3(Point point3) {
		this.point3 = point3;
	}

	public Triangle(Context context, Frame frame, Stroke stroke) {
		super(context, frame, stroke);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void drawShape(Canvas canvas) {
		// TODO Auto-generated method stub
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	    paint.setColor(this.getFillColor());     
	    paint.setStyle(Paint.Style.FILL);
	    paint.setAntiAlias(true);


	    Path path = new Path();
	    path.setFillType(Path.FillType.EVEN_ODD);
	    path.moveTo(point1.x,point1.y);
	    path.lineTo(point2.x,point2.y);
	    path.lineTo(point3.x,point3.y);
	    path.lineTo(point1.x,point1.y);
	    path.close();

	    canvas.drawPath(path, paint);	
	}

}
