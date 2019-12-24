package gui;
import utils.Point3D;
import utils.Range;
import utils.StdDraw;
import java.awt.*;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import dataStructure.*;

	public class Graph_GUI 
	{
		public static int width=1000;
		public static int height=1000;
		public static int minX=-100;
		public static int maxX=100;
		public static int minY=-100;
		public static int maxY=100;
		
	    public static void printGraph(DGraph graph)
	    {
	        StdDraw.setCanvasSize(width,height);
	        StdDraw.setXscale(minX,maxX);
	        StdDraw.setYscale(minY,maxY);
	        Iterator itOnEdge =graph.getEdgeHash().entrySet().iterator();
        	while(itOnEdge.hasNext())
        	{
        		Map.Entry currMainEntry= (Map.Entry) itOnEdge.next();
        		int src=(int) currMainEntry.getKey();
    			double srcX=graph.getNode(src).getLocation().x();
	        	double srcY=graph.getNode(src).getLocation().y();
        		for(edge_data currSpcEntry:graph.getE(src))
        		{
        			int dest=(int) currSpcEntry.getDest();
        			double weight=currSpcEntry.getWeight();

    	        	double destX=graph.getNode(dest).getLocation().x();
    	        	double destY=graph.getNode(dest).getLocation().y();
    	        	StdDraw.setPenRadius(0.005);
    	        	
    	        	//draw the edges
    	        	StdDraw.setPenColor(Color.RED);
    	        	StdDraw.line(srcX,srcY,destX,destY);
    	        	
                   
                    //draw the destination point
                    StdDraw.setPenColor(Color.BLUE);
                    StdDraw.point(destX, destY);
                    
                    //draw direction points
                    StdDraw.setPenRadius(0.025);
    				StdDraw.setPenColor(Color.yellow);
    				StdDraw.point(srcX*0.1+destX*0.9, srcY*0.1+destY*0.9);
    				
    				//draw the weight of the node
    				StdDraw.setPenColor(Color.BLUE);
    				StdDraw.textRight(srcX+3, srcY+3, (int)weight+"");
    				
    				 
                     //draw the weight of each edge
    				 StdDraw.setPenColor(Color.BLACK);
                     StdDraw.textLeft((srcX+destX)/2,(srcY+destY)/2,"" + weight);
        		}
                     //draw the source point
                StdDraw.setPenColor(Color.BLUE);
                StdDraw.point(srcX, srcY);
        		
        	}
	    }
	}