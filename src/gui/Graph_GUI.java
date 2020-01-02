package gui;
import utils.Point3D;
import utils.Range;
import utils.StdDraw;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import algorithms.Graph_Algo;
import dataStructure.*;

	public class Graph_GUI extends JFrame implements ActionListener
	{
		public DGraph currGraph;
		private Graph_Algo algoGraph;
		public static int width=1000;
		public static int height=1000;
		public static int minX=-100;
		public static int maxX=100;
		public static int minY=-100;
		public static int maxY=100;
		
		public Graph_GUI(DGraph gra) 
		{
			this.currGraph = gra;
			this.algoGraph = new Graph_Algo(gra);
			init(gra);
		}
		
		public Graph_GUI() 
		{
			this.currGraph = null;
			this.algoGraph = null;
			init(this.currGraph);
		}

		public Graph_GUI(graph gra) 
		{
			this.currGraph = new DGraph((DGraph) gra);
			this.algoGraph = new Graph_Algo(gra);
			init(gra);
		}

		public void init(graph g) 
		{
			this.currGraph=(DGraph) g;
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
			
			MenuItem isConnected = new MenuItem("Is The Graph Connected");
			isConnected.addActionListener(this);
			graphGUI.add(isConnected);
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
	    	algoGraph=new Graph_Algo();
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
	    	case "Is the graph connected Connected" : isConnected();
	    	break;
	    	default:
	    		break;
	    	}
	    }
		private void isConnected() 
		{
			Graph_Algo tempAlgo = new Graph_Algo();
			JFrame window = new JFrame();
			tempAlgo.init(this.currGraph);
			if(tempAlgo.isConnected())
			{
				JOptionPane.showMessageDialog(window, "The graph is connected");
			}
			else
			{
				JOptionPane.showMessageDialog(window, "The graph is not connected");
			}
		}

		private void tsp() 
		{
			JFrame input = new JFrame();

			String numNodes = JOptionPane.showInputDialog(input,"How many nodes ?");
			int numNodeParse=0;
			try 
			{
				numNodeParse = Integer.parseInt(numNodes);
				if(numNodeParse>this.currGraph.getV().size())
				{
					JOptionPane.showMessageDialog(input, "Number of nodes is larger than the exisitng ammount .");
				}

				LinkedList<Integer> listInt= new LinkedList<Integer>();
				String keys= JOptionPane.showInputDialog(null, "Please enter your TSP key and sperate them by space ");
				if(keys==null)
				{
					JOptionPane.showMessageDialog(null, "No keys were entered");
				}
				while(!keys.isEmpty())
				{
					listInt.add(Integer.parseInt(keys.substring(0, keys.indexOf(" "))));
					keys=keys.substring(keys.indexOf(" ")+1, keys.length()-1);
				}
				this.algoGraph.TSP(listInt);
			}
			
			catch(Exception e)
			{
				
			}
		}

		private void shortestPathDist() 
		{
			JFrame scan = new JFrame();
			try
			{
				String src = JOptionPane.showInputDialog(scan,"Fill source key:");
				String dest = JOptionPane.showInputDialog(scan,"Fill destination key:");

				int srcParse = Integer.parseInt(src);
				int destParse = Integer.parseInt(dest);
				if(currGraph==null )
				{
					JOptionPane.showMessageDialog(scan, "The graph is empty");
					return;
				}
				else if(this.currGraph.getV().isEmpty())
				{
						JOptionPane.showMessageDialog(scan, "The is no nodes in the graph");
						return;
				}
				
				if(this.currGraph.getNode(srcParse)==null || this.currGraph.getNode(destParse)==null)
				{
					JOptionPane.showMessageDialog(scan, "The source key or the destination key doesn't exists");
				}
				double shortDist=algoGraph.shortestPathDist(srcParse, destParse);
				JOptionPane.showMessageDialog(null, "Shortest path is :" + shortDist);
				
			}	
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(scan, "Error accoure");
			}
			
			
		}

		private void shortestPath() 
		{
			JFrame scan = new JFrame();
			try
			{
				String src = JOptionPane.showInputDialog(scan,"Fill source key:");
				String dest = JOptionPane.showInputDialog(scan,"Fill destination key:");

				int srcParse = Integer.parseInt(src);
				int destParse = Integer.parseInt(dest);
				
				if(currGraph==null )
				{
					JOptionPane.showMessageDialog(scan, "The graph is empty");
					return;
				}
				else if(this.currGraph.getV().isEmpty())
				{
						JOptionPane.showMessageDialog(scan, "The is no nodes in the graph");
						return;
				}
				
				if(this.currGraph.getNode(srcParse)==null || this.currGraph.getNode(destParse)==null)
				{
					JOptionPane.showMessageDialog(scan, "The source key or the destination key doesn't exists");
				}
				algoGraph=new Graph_Algo();
				algoGraph.init(this.currGraph);
				ArrayList<node_data> nodeList = (ArrayList<node_data>) algoGraph.shortestPath(srcParse, destParse);
				DGraph graphShortest = new DGraph();

				Iterator iter = nodeList.iterator();
				int index=0;
				StringBuilder path=new StringBuilder();
				while (iter.hasNext()) 
				{
					node_data firstNode = (node_data) iter.next();
					graphShortest.addNode(firstNode);
					graphShortest.connect(nodeList.get(index).getKey(), nodeList.get(index+1).getKey(), this.currGraph.getEdge(nodeList.get(index).getKey(), nodeList.get(index+1).getKey()).getWeight());
					index++;
					path.append(nodeList.get(index).getKey());
					if(index!=nodeList.size()-1)
					{
						path.append("---->");
					}
					
					else
					{
						path.append(";");
					}
				}
				init(graphShortest);
				JOptionPane.showMessageDialog(null, path);
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(scan, "Error accoure");
			}

		}

		private void loadGraph() 
		{
			algoGraph=new Graph_Algo();
			JFileChooser folder=new JFileChooser(FileSystemView.getFileSystemView());
			folder.setDialogTitle("Init Specific Graph"); 
			int selection=folder.showOpenDialog(null);
			if(selection==JFileChooser.APPROVE_OPTION) 
			{
				algoGraph.init(folder.getSelectedFile().getAbsolutePath());
				graph gr_new=algoGraph.copy();
				init(gr_new);
			}
		}

		private void saveGraph() 
		{
			JFileChooser folder = new JFileChooser(FileSystemView.getFileSystemView());
			folder.setDialogTitle("Save Graph");

			int userSelection1 = folder.showSaveDialog(null);
			if (userSelection1 == JFileChooser.APPROVE_OPTION) 
			{
				algoGraph.save(folder.getSelectedFile().getAbsolutePath());
			}
		}
		
		public static void main (String [] args) 
		{
			//Graph_GUI graph = new Graph_GUI();
			//graph.init();

				/*	graphFactory r = new graphFactory();
					graph t = r.randomGraphSmallConnected();*/
					//boker tov eldar.
					
					Point3D p1 = new Point3D(306, 287);
					Point3D p2 = new Point3D(203, 96);
					Point3D p3 = new Point3D(154, 152);
					Point3D p4 = new Point3D(455, 151);
					Point3D p5 = new Point3D(1000, 420);
					Point3D p6 = new Point3D(702, 230);
					Point3D p7 = new Point3D(232, 437);
					Point3D p8 = new Point3D(191, 326);
					nodeData n1 = new nodeData(1,0,p1,0);
					nodeData n2 = new nodeData(2,0,p2,0);
					nodeData n3 = new nodeData(3,0,p3,0);
					nodeData n4 = new nodeData(4,0,p4,0);
					nodeData n5 = new nodeData(5,0,p5,0);
					nodeData n6 = new nodeData(6,0,p6,0);
					nodeData n7 = new nodeData(7,0,p7,0);
					nodeData n8 = new nodeData(8,0,p8,0);

					graph g = new DGraph();
					g.addNode(n1);//0
					g.addNode(n2);//1
					g.addNode(n3);//2
					g.addNode(n4);//3
					g.addNode(n5);//4
					g.addNode(n6);//5
					g.addNode(n7);//6
					g.addNode(n8);//7
					g.connect(n1.getKey(), n2.getKey(), 7);
					g.connect(n2.getKey(), n1.getKey(), 2.77);
					g.connect(n2.getKey(), n3.getKey(), 4.5);
					g.connect(n5.getKey(), n3.getKey(), 10);
					g.connect(n6.getKey(), n4.getKey(), 4.11);
					g.connect(n3.getKey(), n5.getKey(), 3.55);
					g.connect(n5.getKey(), n7.getKey(), 42);
					g.connect(n1.getKey(), n5.getKey(), 23);
					g.connect(n6.getKey(), n2.getKey(), 4.20);

					Graph_GUI a = new Graph_GUI(g);
					a.setVisible(true);
				}
			
			
			
		}
	
	
	