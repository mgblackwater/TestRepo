package com.androidmagazine.shapes;

import org.w3c.dom.Element;

import android.content.Context;
import android.graphics.Point;

import com.androidmagazine.shapes.builder.FrameBuilder;
import com.androidmagazine.shapes.builder.StrokeBuilder;
import com.androidmagazine.shapes.utilities.ColorConverter;

public class ShapeFactory {

	private Context context;
	public ShapeFactory(Context context) {
		
		this.context = context;
		
	}
	
	public Shape createShape(Element shapeElement)
	{
		Frame frame =FrameBuilder.biuldFrameFromXml((Element)shapeElement.getElementsByTagName("frame").item(0));
		Stroke stroke = StrokeBuilder.buildStrokeFromXml((Element)shapeElement.getElementsByTagName("shape").item(0));
		
		Element shapeNode = (Element)shapeElement.getElementsByTagName("shape").item(0);
		String[] colors = shapeNode.getAttribute("fillClr").split(" ");
		float red= Float.parseFloat(colors[0]);
		float green= Float.parseFloat(colors[1]);
		float blue= Float.parseFloat(colors[2]);
		float alpha= Float.parseFloat(colors[3]);
		
		Element valuesNode = (Element) shapeElement.getElementsByTagName("values").item(0); 
		
		int fillClr= ColorConverter.convertColor(alpha, red, green, blue);
		
		int shapeTypeID = Integer.parseInt(shapeElement.getAttribute("type"));
		 	 
		switch(shapeTypeID)
		{
			case 100:Line line = new Line(this.context,frame,stroke);
	 
	   			
				setPointsForLine(valuesNode, line);
				return line;
				
			case 1:	Triangle triangle = new Triangle(this.context,frame,stroke);
	 
	    	
	    		setPointsForTriangle(valuesNode, triangle);
			  
	    		shapeNode = (Element)shapeElement.getElementsByTagName("shape").item(0);
	    		triangle.setFillColor(fillClr);
	    		return triangle;
			
			case 2: Square square = new Square(context, frame, stroke);
				square.setFillColor(fillClr);
				return square;
		
			case 3: Ellipse ellipse = new Ellipse(context, frame, stroke);
				ellipse.setFillColor(fillClr);
				return ellipse;

			case 4: Diamond diamond = new Diamond(context, frame, stroke);
	    		float len1 = Float.parseFloat(valuesNode.getAttribute("len1"));
	    		float len2 = Float.parseFloat(valuesNode.getAttribute("len2"));
				diamond.setLen1(len1);
				diamond.setLen2(len2);
	    		diamond.setFillColor(fillClr);
				return diamond;
				
			case 5: Polygon polygon  = new Polygon(context, frame, stroke);
	    		int numSides = Integer.parseInt(valuesNode.getAttribute("numSides"));
	    		
	    		polygon.setNumberOfSides(numSides);
				polygon.setFillColor(fillClr);
				return polygon;
				
			case 6: Star star = new Star(context, frame, stroke);
	            int numberOfSpikes = Integer.parseInt(valuesNode.getAttribute("numSpikes"));
	            float ratio = Float.parseFloat(valuesNode.getAttribute("ratio"));
			 	star.setNumberOfSpikes(numberOfSpikes);
			 	star.setRatio(ratio);
			 	star.setFillColor(fillClr);
			 	return star;
			 	
			default:return null;
	
		}

		
	}

	private void setPointsForLine(Element valuesNode, Line line) {
		String[] pointsForLine = valuesNode.getAttribute("pts").split(" ");
		int x1 = (int) Float.parseFloat(pointsForLine[0]);
		int y1 = (int) Float.parseFloat(pointsForLine[1]);
		int x2 = (int) Float.parseFloat(pointsForLine[2]);
		int y2 =(int) Float.parseFloat(pointsForLine[3]);
		line.setX1(x1);
		line.setY1(y1);
		line.setX2(x2);
		line.setY2(y2);
	}

	private void setPointsForTriangle(Element valuesNode, Triangle triangle) {
		String[] pointsForTraingle = valuesNode.getAttribute("pts").split(" ");
		int point1X = (int)Float.parseFloat(pointsForTraingle[0]);
		int point1Y = (int)Float.parseFloat(pointsForTraingle[1]);
		int point2X = (int)Float.parseFloat(pointsForTraingle[2]);
		int point2Y = (int)Float.parseFloat(pointsForTraingle[3]);
		int point3X = (int)Float.parseFloat(pointsForTraingle[4]);
		int point3Y = (int)Float.parseFloat(pointsForTraingle[5]);
		
		triangle.setPoint1(new Point(point1X, point1Y));
		triangle.setPoint2(new Point(point2X,point2Y));
		triangle.setPoint3(new Point(point3X,point3Y));
	}
	
}
