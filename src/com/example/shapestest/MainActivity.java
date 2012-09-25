package com.example.shapestest;

import java.util.ArrayList;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.androidmagazine.shapes.Line;
import com.androidmagazine.shapes.Shape;
import com.androidmagazine.shapes.ShapeFactory;
import com.androidmagazine.shapes.XMLParser;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class MainActivity extends Activity {

	RelativeLayout layout;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    	layout = (RelativeLayout)findViewById(R.id.layoutShape);
        
    	drawLine();
        
       //drawShape(layout);
      
        
    }
    
    private void drawDiamond()
    {
    	DiamondShape diamond = new DiamondShape(this);
    	
    	   RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
           params.leftMargin = 124;
           params.topMargin = 461;
           diamond.setLayoutParams(params);

           layout.addView(diamond);
    }
    
    private void drawLine()
    {
    	LineShape line = new LineShape(this);
    	/*
    	   RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(20,624);
           params.leftMargin = 100;
           params.topMargin = 113;
           //line.setLayoutParams(params);	
          
    	*/
    	
    	
    	layout.addView(line);
    }
    
	private void drawPolygon() {
	
         
        
        PolygonShape polygon = new PolygonShape(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(411,361);
        params.leftMargin = 188;
        params.topMargin = 95;
        polygon.setLayoutParams(params);
        
        layout.addView(polygon);
	}
    
    private void drawEllipse()
    {
    	EllipseShape eclipse = new EllipseShape(this);
    	layout.addView(eclipse);
    	
    }
    
	private void drawStar() {
	 
        StarShape star = new StarShape(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(1000,500);
        params.leftMargin = 20;
        params.topMargin = 20;
        //params.rightMargin = 100;
        star.setLayoutParams(params);
       // star.setTranslationX(100);
       // layout.addView(star);
        drawLine();
	}

	private void drawShape(RelativeLayout layout) {
		
		XMLParser parser = new XMLParser();
        String xml= parser.getXmlFromFile( Environment.getExternalStorageDirectory().getAbsolutePath()+"/2P.xml");
		Document doc= parser.getDomElement(xml);
	    NodeList childs =doc.getElementsByTagName("layout").item(0).getChildNodes();
	    ArrayList<Node> elements = new ArrayList<Node>();
	
	   for(int i=0;i<childs.getLength();i++)
	   {
		   if(childs.item(i).getNodeType()==Node.ELEMENT_NODE)
		   {
			   elements.add(childs.item(i));
		   }
	   }
	    
        ShapeFactory factory = new ShapeFactory(this);
        Shape shape= factory.createShape((Element)elements.get(0));
        
        layout.addView(shape);
      
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    private class LineShape extends View
    {

		@Override
		protected void onDraw(Canvas canvas) {
		
			Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
			paint.setColor(Color.BLUE);
			paint.setStrokeWidth(15);
			paint.setStyle(Paint.Style.STROKE);
			
			int leftMargin = 100;
			int topMargin = 113;
			
			int x1=9,x2=0,y1=0,y2=624;
			/*
			x1+= leftMargin;
			x2+= leftMargin;
			y1+=topMargin;
			y2+= topMargin;
			*/
			Path path = new Path();
			path.moveTo(x1, y1);
			path.lineTo(x2, y2);
			
			int width= this.getLayoutParams().width;
	    	int height = this.getLayoutParams().height;
			
			canvas.drawPath(path, paint);
		
		}

		public LineShape(Context context) {
			
			super(context);
			
		}
    	
    }
     
    
    private class PolygonShape extends View
    {

		@Override
		protected void onDraw(Canvas canvas) {
		
			Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
			paint.setColor(Color.BLUE);
			paint.setStrokeWidth(3);
			paint.setStyle(Paint.Style.STROKE);
			
			int framewidth = 411;
			int frameHeigh = 361;
			
			int numSides = 5;
			
			float centerX = framewidth/2;
			float centerY = frameHeigh/2;
			
			Path path = new Path();
			
			path.moveTo(centerX,frameHeigh);
			float offsetX,offsetY;
			
			for (int i=1; i<numSides; ++i) 
			{
				offsetX= framewidth * 0.5f * ((float)Math.sin(i*2.0*Math.PI/numSides));
				offsetY= frameHeigh * 0.5f *((float) Math.cos(i*2.0*Math.PI/numSides));
				path.lineTo(centerX+offsetX,centerY+offsetY);
			}
			path.close();
			
			canvas.drawPath(path, paint);
		
		}

		public PolygonShape(Context context) {
			
			super(context);
			
		}
    	
    }
     
    
    private class DiamondShape extends View
    {

		@Override
		protected void onDraw(Canvas canvas) {
		
			Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
			paint.setColor(Color.BLUE);
			paint.setStrokeWidth(3);
			paint.setStyle(Paint.Style.STROKE);
			
			int framewidth = 432;
			int frameHeigh = 419;
			
			float len1 = 285.12f; 
			float len2 = 209.5f;
			Path path = new Path();
			
			path.moveTo(len1, 0);
			path.lineTo(framewidth, frameHeigh-len2);
			path.lineTo(framewidth-len1,frameHeigh);
			path.lineTo(0,len2);
			path.close();
			
			canvas.drawPath(path, paint);
		
		}

		public DiamondShape(Context context) {
			
			super(context);
			
		}
    	
    }
      
    private class EllipseShape extends View
    {

		@Override
		protected void onDraw(Canvas canvas) {
		
			Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
			paint.setColor(Color.BLUE);
			paint.setStrokeWidth(3);
			paint.setStyle(Paint.Style.FILL);
			float left = 186;
			float top = 78;
			float right = 290;
			float bottom = 253;
			float strokeWidth = 50;
			RectF rect = new RectF(left,top,right,bottom);
			
			canvas.drawOval(rect,paint);
			
			paint = new Paint(Paint.ANTI_ALIAS_FLAG);
			paint.setColor(Color.GREEN);
			paint.setStrokeWidth(strokeWidth);
			paint.setStyle(Paint.Style.STROKE);
			
			rect = new RectF(left+(strokeWidth/2),top+(strokeWidth/2), right-(strokeWidth/2),bottom+(strokeWidth/2));
			
			canvas.drawOval(rect,paint);
		}

		public EllipseShape(Context context) {
			
			
			
			super(context);
			
		}
    	
    }
    
    private class StarShape extends View
    {

		@Override
		protected void onDraw(Canvas canvas) {
			
			int frameWidth =265;
			int frameHeight = 274;
			//int left=201;
			//int top=133;
			float ratio = 0.25f;
			
			float centerX =frameWidth/2;
			float centerY = frameHeight/2;
			
			
			Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
			paint.setColor(Color.BLUE);
			
			paint.setStyle(Paint.Style.FILL);
			Path path = new Path();
			
			path.moveTo(centerX,centerY+(frameHeight/2));
			
			float inX= frameWidth *  ratio * ((float)Math.sin(Math.PI/8));
			float inY =frameHeight * ratio * ((float)Math.cos(Math.PI/8));
			path.lineTo(centerX+inX,centerY+inY);
			
			float outX,outY;
			
			for (int i=1; i<8; ++i) 
			{
				//calculate outer points and add line to each points
				outX= (frameWidth *0.5f *((float) Math.sin(i*2.0*Math.PI/8)));
				outY= (frameHeight * 0.5f* ((float)Math.cos(i*2.0*Math.PI/8)));
				path.lineTo(centerX+outX, centerY+outY);
				
				//calculate inner points and add line to each points
				inX=  frameWidth * ratio * ((float)Math.sin((i*2.0*Math.PI/8)+Math.PI/8));
				inY= frameHeight * ratio *((float) Math.cos((i*2.0*Math.PI/8)+Math.PI/8));
				
				path.lineTo(centerX+inX, centerX+inY);
				
			}
		
			path.close();
			//canvas.drawColor(Color.GREEN);
			
			canvas.drawPath(path, paint);
			
			paint.setColor(Color.YELLOW);
			paint.setStrokeWidth(10);
			paint.setStyle(Paint.Style.FILL);
		
			Path strokePath = new Path();
			
			frameWidth = frameWidth+10;
			frameHeight = frameHeight+10;
			 centerX =frameWidth/2;
			centerY = frameHeight/2;
			
			strokePath.moveTo(centerX,centerY+(frameHeight/2));
			
			inX= frameWidth *  ratio * ((float)Math.sin(Math.PI/8));
			inY =frameHeight * ratio * ((float)Math.cos(Math.PI/8));
			strokePath.lineTo(centerX+inX,centerY+inY);
				
			for (int i=1; i<8; ++i) 
			{
				//calculate outer points and add line to each points
				outX= (frameWidth *0.5f *((float) Math.sin(i*2.0*Math.PI/8)));
				outY= (frameHeight * 0.5f* ((float)Math.cos(i*2.0*Math.PI/8)));
				strokePath.lineTo(centerX+outX, centerY+outY);
				
				//calculate inner points and add line to each points
				inX=  frameWidth * ratio * ((float)Math.sin((i*2.0*Math.PI/8)+Math.PI/8));
				inY= frameHeight * ratio *((float) Math.cos((i*2.0*Math.PI/8)+Math.PI/8));
				
				strokePath.lineTo(centerX+inX, centerX+inY);
				
			}
		
			strokePath.close();
			//canvas.drawColor(Color.BLACK);
			
			canvas.drawPath(strokePath, paint);
			
		
			
			
			
		}

		public StarShape(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}
    	
    }
    
    
}


