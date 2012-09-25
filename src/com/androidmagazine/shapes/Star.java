package com.androidmagazine.shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.FloatMath;

public class Star extends Shape {

	private float ratio;
	private int numberOfSpikes;

	public float getRatio() {
		return ratio;
	}

	public void setRatio(float ratio) {
		this.ratio = ratio;
	}

	
	public int getNumberOfSpikes() {
		return numberOfSpikes;
	}

	public void setNumberOfSpikes(int numberOfSpikes) {
		this.numberOfSpikes = numberOfSpikes;
	}

	public Star(Context context, Frame frame, Stroke stroke) {
		super(context, frame, stroke);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	protected void drawShape(Canvas canvas) {
		
		
		
		float centerX =this.frame.getWidth()/2;
		float centerY = this.frame.getHeight()/2;
		
		
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(this.getFillColor());
		paint.setStyle(Paint.Style.FILL);
		Path path = new Path();
		
		path.moveTo(centerX,centerY+(this.frame.getHeight()/2));
		
		float inX= this.frame.getWidth() *  ratio * ((float)Math.sin(Math.PI/numberOfSpikes));
		float inY =this.frame.getHeight() * ratio * ((float)Math.cos(Math.PI/numberOfSpikes));
		path.lineTo(centerX+inX,centerY+inY);
		
		float outX,outY;
		
		for (int i=1; i<8; ++i) 
		{
			//calculate outer points and add line to each points
			outX= (this.frame.getWidth() *0.5f *( FloatMath.sin((float) (i*2.0*Math.PI/numberOfSpikes))));
			outY= (this.frame.getHeight() * 0.5f* (FloatMath.cos((float) (i*2.0*Math.PI/numberOfSpikes))));
			path.lineTo(centerX+outX, centerY+outY);
			
			//calculate inner points and add line to each points
			inX=  this.frame.getWidth() * ratio *( FloatMath.sin((float) ((i*2.0*Math.PI/numberOfSpikes)+Math.PI/numberOfSpikes)));
			inY= this.frame.getHeight() * ratio *( FloatMath.cos((float) ((i*2.0*Math.PI/numberOfSpikes)+Math.PI/numberOfSpikes)));
			
			path.lineTo(centerX+inX, centerX+inY);
			
		}
	
		path.close();
		
		
		canvas.drawPath(path, paint);
		
	}

}
