package com.androidmagazine.shapes.builder;

import org.w3c.dom.Element;

import com.androidmagazine.shapes.Frame;
import com.androidmagazine.shapes.Stroke;
import com.androidmagazine.shapes.utilities.ColorConverter;

public class StrokeBuilder {

	private static final String LINE_TYPE_ATTR = "lineType";
	private static final String LINE_WIDTH_ATTR = "lineWidth";
	private static final String LINE_COLOR_ATTR = "lineClr";
	private static final String splitter = " ";
	
	public static Stroke buildStrokeFromXml(Element strokeXml)
	{
		Stroke stroke = new Stroke();

	 	setLIneType(strokeXml, stroke);	
		setLineWidth(strokeXml, stroke);	
		setColor(stroke, strokeXml);
		
		return stroke;
	}

	private static void setLineWidth(Element strokeXml, Stroke stroke) {
		
		String lineWidth = strokeXml.getAttribute(LINE_WIDTH_ATTR);
		stroke.setLineWidth(Integer.parseInt(lineWidth));
	}

	private static void setLIneType(Element strokeXml, Stroke stroke) {
		String lineType = strokeXml.getAttribute(LINE_TYPE_ATTR);
		stroke.setLineType(Integer.parseInt(lineType));
	}
	
	private static void setColor(Stroke stroke,Element strokeXml) {
		
		String lineColor = strokeXml.getAttribute(LINE_COLOR_ATTR);
		String[] colors = lineColor.split(splitter);
		
		float red= Float.parseFloat(colors[0]);
		float green= Float.parseFloat(colors[1]);
		float blue= Float.parseFloat(colors[2]);
		float alpha= Float.parseFloat(colors[3]);
		
		stroke.setLineColor(ColorConverter.convertColor(alpha, red, green, blue));
		
	}

}
