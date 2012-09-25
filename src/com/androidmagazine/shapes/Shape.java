package com.androidmagazine.shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public abstract class Shape extends View {
	
	
	protected Frame frame;
	protected Stroke stroke;
	protected int fillColor;
	
	public Frame getFrame() {
		return frame;
	}



	public void setFrame(Frame frame) {
		this.frame = frame;
	}



	public Stroke getStroke() {
		return stroke;
	}



	public void setStroke(Stroke stroke) {
		this.stroke = stroke;
	}



	public int getFillColor() {
		return fillColor;
	}



	public void setFillColor(int fillColor) {
		this.fillColor = fillColor;
	}



	public Shape(Context context,Frame frame,Stroke stroke) {
		super(context);
	
		this.frame  = frame;
		this.stroke = stroke;
		
		LayoutParams layoutForFrame = new LayoutParams(frame.getWidth(),frame.getHeight());
		layoutForFrame.leftMargin = frame.getX();
		layoutForFrame.topMargin = frame.getY();
		
		
		
		this.setLayoutParams(layoutForFrame);
		
	}

	
	@Override
	protected final void onDraw(Canvas canvas) {
		drawFrame(canvas);
		drawShape(canvas);
		
		
		
	}
	
	protected abstract void drawShape(Canvas canvas);
	
	private void drawFrame(Canvas canvas)
	{
	
		
		this.setRotation(frame.getRotation());
		this.setVisibility(frame.getVisibility());
		canvas.drawColor(frame.getBackgroundColor());
		
		
		
		
	}
	
	
}
