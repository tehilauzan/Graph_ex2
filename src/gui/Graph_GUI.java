package gui;
import utils.Point3D;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;
import algorithms.Graph_Algo;
import dataStructure.*;

	public class Graph_GUI extends JFrame implements ActionListener , MouseListener
	{
		public graph currGraph;
		
		public Graph_GUI(DGraph gra) 
		{
			this.currGraph = gra;
			init(gra);
		}
		
		public Graph_GUI() 
		{
			this.currGraph = null;
			init(this.currGraph);
		}

		public Graph_GUI(graph gra) 
		{
			this.currGraph = new DGraph((DGraph) gra);
			init(this.currGraph);
		}

		public void init(graph g) 
		{
			this.currGraph=g;
			this.setSize(1000, 10000);
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
			
			this.addMouseListener(this);
		} 
		
	  
		public void paint(Graphics graph) {
			super.paint(graph);
			graph.setFont(new Font ("Courier", Font.PLAIN,20));
			if (currGraph == null) {
				JFrame mesg = new JFrame(); 
				JOptionPane.showMessageDialog(mesg, "There Isn't Graph To Show");
			}
			else 
			{
				for (node_data currNode : currGraph.getV()) 
				{
					graph.setColor(Color.blue);
					Point3D srcNode = currNode.getLocation();
					graph.fillOval(srcNode.ix(), srcNode.iy(), 12, 12);
					graph.setColor(Color.RED);
					graph.drawString(""+currNode.getKey(), srcNode.ix()-4, srcNode.iy()-4);
					if ((currGraph.getE(currNode.getKey())!=null)) 
					{
						for (edge_data edge : currGraph.getE(currNode.getKey())) 
						{
							graph.setColor(Color.RED);
							((Graphics2D) graph).setStroke(new BasicStroke(3,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
							Point3D destPoint = currGraph.getNode(edge.getDest()).getLocation();
							graph.drawLine(srcNode.ix()+5, srcNode.iy()+5, destPoint.ix()+5, destPoint.iy()+5);
							graph.setColor(Color.black);
							graph.fillOval((int)((srcNode.ix()*0.7)+(0.3*destPoint.ix()))+2, (int)((srcNode.iy()*0.7)+(0.3*destPoint.iy())), 9, 9);
							graph.drawString(String.valueOf(edge.getWeight()), 1+(int)((srcNode.ix()*0.7)+(0.3*destPoint.ix())), (int)((srcNode.iy()*0.7)+(0.3*destPoint.iy()))-2);
						}
					}	
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
	    	case "Shortest Path" : shortestPath();
	    	break;
	    	case "Shortest Path Dist" : shortestPathDist();
	    	break;
	    	case "TSP" : tsp();
	    	break;
	    	case "Is the graph connected Connected" : isConnected();
	    	break;
	    	default: //defult set to be load
	    		loadGraph();
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
			Graph_Algo algo = new Graph_Algo();
			algo.init(this.currGraph);
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
				algo.TSP(listInt);
			}
			
			catch(Exception e)
			{
				
			}
		}

		private void shortestPathDist() 
		{
			Graph_Algo algo = new Graph_Algo();
			algo.init(this.currGraph);
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
				double shortDist=algo.shortestPathDist(srcParse, destParse);
				JOptionPane.showMessageDialog(null, "Shortest path is :" + shortDist);
				
			}	
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(scan, "Error accoure");
			}
			
			
		}

		private void shortestPath() 
		{
			Graph_Algo algo = new Graph_Algo();
			algo.init(this.currGraph);
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
				algo=new Graph_Algo();
				algo.init(this.currGraph);
				ArrayList<node_data> nodeList = (ArrayList<node_data>) algo.shortestPath(srcParse, destParse);
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
			Graph_Algo algo = new Graph_Algo();
			JFileChooser folder=new JFileChooser(FileSystemView.getFileSystemView());
			folder.setDialogTitle("Init Specific Graph"); 
			int selection=folder.showOpenDialog(null);
			if(selection==JFileChooser.APPROVE_OPTION) 
			{
				algo.init(folder.getSelectedFile().getAbsolutePath());
				//Graph_Algo gr_new=(Graph_Algo)algo.copy();
				//algo.init(currGraph);
			}
		}

		private void saveGraph() 
		{
			Graph_Algo algo = new Graph_Algo();
			algo.init(this.currGraph);
			JFileChooser folder = new JFileChooser(FileSystemView.getFileSystemView());
			folder.setDialogTitle("Save Graph");

			int userSelection1 = folder.showSaveDialog(null);
			if (userSelection1 == JFileChooser.APPROVE_OPTION) 
			{
				algo.save(folder.getSelectedFile().getAbsolutePath());
			}
		}
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
		public static void main (String [] args) 
		{
			graph gr = new DGraph();
			//repaint();
			
		}
				
	}