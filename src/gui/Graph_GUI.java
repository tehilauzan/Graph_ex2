package gui;
import utils.Point3D;
import utils.Range;
import utils.StdDraw;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JFrame;

import dataStructure.*;

	public class Graph_GUI extends JFrame implements ActionListener
	{
		public DGraph currGraph;
		public static int width=1000;
		public static int height=1000;
		public static int minX=-100;
		public static int maxX=100;
		public static int minY=-100;
		public static int maxY=100;
		
		public Graph_GUI(DGraph gra) 
		{
			this.currGraph = gra;
			init();
		}
		
		public Graph_GUI() 
		{
			init();
		}

		public void init() 
		{
			this.setSize(5000, 50000);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(true);
			
			MenuBar menuBar = new MenuBar();
			
			Menu menu = new Menu("File");
			menuBar.add(menu);
			
			Menu graphGUI = new Menu("Algorithms");
			menuBar.add(graphGUI);
			
			Menu drawGraph = new Menu("Draw");
			menuBar.add(drawGraph);
			
			this.setMenuBar(menuBar);
			
			MenuItem saveGraph = new MenuItem("Save");
			saveGraph.addActionListener(this);
			
			MenuItem loadGraph = new MenuItem("load");
			loadGraph.addActionListener(this);
			
			menu.add(saveGraph);
			menu.add(loadGraph);
    		
			//this.addMouseListener(this);
			MenuItem shortestPath = new MenuItem("Shortest Path");
			shortestPath.addActionListener(this);
			graphGUI.add(shortestPath);
			
			MenuItem shortestPathDist = new MenuItem("Shortest Path Dist");
			shortestPathDist.addActionListener(this);
			graphGUI.add(shortestPathDist);
			
			MenuItem tsp = new MenuItem("TSP");
			tsp.addActionListener(this);
			graphGUI.add(tsp);
		} 
		
	    public void paint(Graphics graph)
	    {
	    	super.paint(graph);
	    	if(this.currGraph!=null)
	    	{
	    		
			/**
			Point3D prev = null;
			for (Point3D p : points) 
			{
				g.setColor(Color.BLUE);
				g.fillOval((int)p.x(), (int)p.y(), 10, 10);
				
				if(prev != null)
				{
					g.setColor(Color.RED);
					g.drawLine((int)p.x(), (int)p.y(), 
							(int)prev.x(), (int)prev.y());
					
					g.drawString("5", (int)((p.x()+prev.x())/2),(int)((p.y()+prev.y())/2));
				}
				
				prev = p;
			**/		
	        StdDraw.setCanvasSize(width,height);
	        StdDraw.setXscale(minX,maxX);
	        StdDraw.setYscale(minY,maxY);
	        Iterator itOnEdge =currGraph.getEdgeHash().entrySet().iterator();
        	while(itOnEdge.hasNext())
        	{
        		Map.Entry currMainEntry= (Map.Entry) itOnEdge.next();
        		int src=(int) currMainEntry.getKey();
    			double srcX=((dataStructure.graph) graph).getNode(src).getLocation().x();
	        	double srcY=((dataStructure.graph) graph).getNode(src).getLocation().y();
        		for(edge_data currSpcEntry:((dataStructure.graph) graph).getE(src))
        		{
        			int dest=(int) currSpcEntry.getDest();
        			double weight=currSpcEntry.getWeight();

    	        	double destX=((dataStructure.graph) graph).getNode(dest).getLocation().x();
    	        	double destY=((dataStructure.graph) graph).getNode(dest).getLocation().y();
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
	    
	    @Override
	    public void actionPerformed (ActionEvent event) 
	    {
	    	String action = event.getActionCommand();
	    	switch(action) 
	    	{
	    	case "Draw" : repaint();
	    	break;
	    	case "Save" : saveGraph();
	    	break;
	    	case "Load" : loadGraph();
	    	break;
	    	case "Shortest Path" : shortestPath();
	    	break;
	    	case "Shortest Path Dist" : shortestPathDist();
	    	break;
	    	case "TSP" : tsp();
	    	break;
	    	default:
	    		break;
	    	}
	    }
		private void tsp() 
		{
			// TODO Auto-generated method stub
			
		}

		private void shortestPathDist() 
		{
			// TODO Auto-generated method stub
			
		}

		private void shortestPath() 
		{
			// TODO Auto-generated method stub
			
		}

		private void loadGraph() 
		{
			// TODO Auto-generated method stub
			
		}

		private void saveGraph() 
		{
			// TODO Auto-generated method stub
			
		}
		public static void main (String [] args) 
		{
			Graph_GUI graph = new Graph_GUI();
			graph.init();
			
		}
	}
	
	