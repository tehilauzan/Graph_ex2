package algorithms;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.nodeData;
import dataStructure.node_data;
	
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
*/
public class Graph_Algo implements graph_algorithms{

	private DGraph graph;
	
	public Graph_Algo() 
	{
		this.graph = new DGraph();
	}
	@Override
	public void init(graph g) 
	{
		this.graph = (DGraph)g;
	}

	@Override
	public void init(String file_name) 
	{
		try
		{
			FileInputStream fileInput = new FileInputStream(file_name);
			ObjectInputStream inObject = new ObjectInputStream(fileInput);
			DGraph initGraph = (DGraph)inObject.readObject();
			inObject.close(); 
			fileInput.close(); 
		} 
		catch (Exception e) 
		{
			System.out.println("Error - file couldn't be opened");
		} 
	}

	@Override
	public void save(String file_name) 
	{
		FileOutputStream fileOutput;
		ObjectOutputStream out;
		try 
		{
			fileOutput = new FileOutputStream(file_name);
			out = new ObjectOutputStream(fileOutput);
			out.writeObject(this.graph); 
			out.close(); 
			fileOutput.close();
		} 
		catch (Exception e) 
		{
			System.out.println("Error - the saved failed");
		}
		
	}

	@Override
	/**
	 * this function use helperIsConnected function, if not all node tag's are 1 
	 * meaning the graph is not connected.
	 * return boolean
	 * 
	 */
	public boolean isConnected() 
	{
			setTags();
			node_data randomNode = (node_data)graph.getV().toArray()[0];
			helperIsConnected(randomNode,this.graph.nodeSize());
			for (node_data n: this.graph.getV()) 
			{
				if (n.getTag()==0) 
				{
					return false;
				}
			}
			return true;
	}
	/**
	 * 	recursive function that set all neighbors tag's to 1
	 * @param node - get first node to start with
	 * @param nodeCounter - counter that rerpresent how many nodes we went over
	 */
    private void helperIsConnected(node_data node ,int nodeCounter) 
    {
		if(node==null || nodeCounter==0)
			return;
		node.setTag(1);
		nodeCounter--;
		for(edge_data currSpcEntry:graph.getE(node.getKey()))
		{
			helperIsConnected(graph.getNode(currSpcEntry.getDest()),nodeCounter);
		}			
    }

	@Override
	public double shortestPathDist(int src, int dest) 
	{
		vertexToInfinity();
		//init the src to be zero weight;
		graph.getNode(src).setWeight(0);
		graph.getNode(src).setTag(1);
		nodeData srcTemp = (nodeData) graph.getNode(src);
		nodeData destTemp = (nodeData) graph.getNode(dest);
		helpShortest(srcTemp,destTemp);
		return graph.getNode(dest).getWeight();
	}
	
	private void helpShortest (node_data src, node_data dest) 
	{
       	if (src.getKey()== dest.getKey()|| src.getTag()==1) 
       	{
       		return;
       	}
		double weightEdge,weightDestNode,weightSrcNode,sumEdgeAndSrc;	
		if (graph.getE(src.getKey())!=null)
		{
		for(edge_data currSpcEntry:graph.getE(src.getKey()))
    		{
       			weightEdge = currSpcEntry.getWeight();
       			weightDestNode = graph.getNode(currSpcEntry.getDest()).getWeight();
       			weightSrcNode = src.getWeight();
       			sumEdgeAndSrc = weightEdge+weightSrcNode;
       			if (sumEdgeAndSrc<weightDestNode) 
       			{
       				graph.getNode(currSpcEntry.getDest()).setWeight(sumEdgeAndSrc);
       				src.setTag(1);
       				helpShortest(this.graph.getNode(currSpcEntry.getDest()), dest);
       			}
    		}
		}
	} 

	@Override
	public List<node_data> shortestPath(int src, int dest) 
	{
		if(shortestPathDist(src, dest)==Double.POSITIVE_INFINITY) 
			return null;
		
		return null;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) 
	{
		return null;
	}
		
	@Override
	public graph copy() 
	{
		DGraph copiedGraph=new DGraph();
		for (node_data n: this.graph.getV()) 
		{
			nodeData copy_node = new nodeData (n.getKey(),n.getWeight(),n.getTag(),n.getLocation(),n.getInfo());
			copiedGraph.addNode(copy_node);
		
			for(edge_data edge: this.graph.getE(n.getKey()))
				{
					try 
						{
							copiedGraph.connect(edge.getSrc(), edge.getDest(), edge.getWeight());
						} 
					catch (Exception e) 
						{
							System.out.println("connect been failed, copy wasn't done");
						}
				}
		}
		return copiedGraph;
	}
	
	private void vertexToInfinity() 
	{
		Iterator<node_data> iter = this.graph.getV().iterator();
		while (iter.hasNext()) 
		{
			node_data currentNode = iter.next();
			currentNode.setWeight(Double.POSITIVE_INFINITY);
			setTags();
		}
		
	}
	private void setTags () 
	{
		Iterator<node_data> iter = this.graph.getV().iterator();
		while (iter.hasNext()) 
		{
			node_data currentNode = iter.next();
			currentNode.setTag(0);
		}
	}
		
}
		
		
