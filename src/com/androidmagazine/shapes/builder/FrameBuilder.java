package com.androidmagazine.shapes.builder;

import org.w3c.dom.Element;

import android.R.integer;
import android.graphics.Color;
import android.opengl.Visibility;
import android.view.View;

import com.androidmagazine.shapes.Frame;
import com.androidmagazine.shapes.utilities.ColorConverter;

public class FrameBuilder {

	private static final String LOCATION_ATTR = "location";
	private static final String FILL_COLOR_ATTR = "fillClr";
	private static final String HIDDEN_ATTR = "hidden";
	private static final String splitter = " ";
	
	public static Frame biuldFrameFromXml(Element frameXml)
	{
		Frame frame = new Frame();
			
		setLocationValuesToFrame(frame, frameXml);
		setColor(frame,frameXml);
		setVisibility(frame, frameXml);
		
		return frame;
	}

	private static void setColor(Frame frame,Element frameXml) {
		
		String fillColor = frameXml.getAttribute(FILL_COLOR_ATTR);
		String[] colors = fillColor.split(splitter);
		
		float red= Float.parseFloat(colors[0]);
		float green= Float.parseFloat(colors[1]);
		float blue= Float.parseFloat(colors[2]);
		float alpha= Float.parseFloat(colors[3]);
		
		frame.setBackgroundColor(ColorConverter.convertColor(alpha, red, green, blue));
		
	}

	private static void setLocationValuesToFrame(Frame frame, Element frameXml) {
		
		String location= frameXml.getAttribute(LOCATION_ATTR);
		String[] locationValues = location.split(splitter);
		
		frame.setX(Integer.parseInt(locationValues[0]));
		frame.setY(Integer.parseInt(locationValues[1]));
		frame.setWidth(Integer.parseInt(locationValues[2]));
		frame.setHeight(Integer.parseInt(locationValues[3]));
		frame.setRotation(Integer.parseInt(locationValues[4]));
		
	}
	
	private static void setVisibility(Frame frame,Element frameXml)
	{
	
		String hidden = frameXml.getAttribute(HIDDEN_ATTR);
		if(hidden !=null && hidden != "")
		{
		int hiddenValue = Integer.parseInt(hidden);
		if(hiddenValue==1)
		{
			frame.setVisibility(View.INVISIBLE);
		}
		else
		{
			frame.setVisibility(View.VISIBLE);
		}
		}
	  
	}
	
}
