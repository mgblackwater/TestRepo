package com.androidmagazine.shapes.utilities;

import android.graphics.Color;

public class ColorConverter {

	private static final int MULTIPLIER = 255;
	
	public static int convertColor(float alpha,float red,float green,float blue)
	{
		int alphaInt = (int)(alpha*MULTIPLIER);
		int redInt = (int)(red*MULTIPLIER);
		int greenInt = (int)(green*MULTIPLIER);
		int blueInt = (int)(blue*MULTIPLIER);
		
		return	Color.argb(alphaInt, redInt, greenInt, blueInt);
		
	}
	
}
