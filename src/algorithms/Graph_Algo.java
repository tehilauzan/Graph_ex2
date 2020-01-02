package algorithms;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import dataStructure.DGraph;
import dataStructure.edgeData;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.nodeData;
import dataStructure.node_data;
import utils.Point3D;
	
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
*/
public class Graph_Algo implements graph_algorithms
{

	private DGraph graph;
	
	public Graph_Algo() 
	{
		this.graph = new DGraph();
	}
	public Graph_Algo(DGraph x) //check
	{
		this.graph = x;
	}
	public Graph_Algo(graph _graph) 
	{
		this.graph = (DGraph) _graph;
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
			init(initGraph);
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
			if(!graph.getV().isEmpty())
			{
			node_data randomNode = (node_data)graph.getV().toArray()[0];
			helperIsConnected(randomNode,this.graph.nodeSize());
			for (node_data n: this.graph.getV()) 
			{
				if (n.getTag()==0) 
				{
					return false;
				}
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
		if(graph.getE(node.getKey())!=null)
		{
			for(edge_data currSpcEntry:graph.getE(node.getKey()))
			{
				helperIsConnected(graph.getNode(currSpcEntry.getDest()),nodeCounter);
			}	
		}
				
    }

	@Override
	public double shortestPathDist(int src, int dest) 
	{
		vertexToInfinity();
		//init the src to be zero weight;
		graph.getNode(src).setWeight(0);
		//graph.getNode(src).setTag(1);
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
       				graph.getNode(currSpcEntry.getDest()).setInfo(""+src.getKey());
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
		{
			throw new RuntimeException("this two points aren't connected");
		}
		shortestPathDist(src,dest);
		ArrayList<node_data> ans=new ArrayList<node_data>();
		ans.add(graph.getNode(dest));
		node_data curreNode = graph.getNode(dest);
		while (!ans.contains(graph.getNode(src))) 
		{
			ans.add((node_data) this.graph.getNodeHash().get(Integer.valueOf(curreNode.getInfo())));
			curreNode = (node_data) this.graph.getNodeHash().get(Integer.valueOf(curreNode.getInfo()));
		}
		ans=reverse(ans);
		return ans;
	}
	
	public ArrayList<node_data> reverse(ArrayList<node_data> array)
	{
		ArrayList<node_data> copy=new ArrayList<node_data>();
		for(int i=array.size()-1;i>=0;i--)
		{
			copy.add(array.get(i));
		}
		return copy;

	}

	@Override
	public List<node_data> TSP(List<Integer> targets) 
	{
		if (targets.isEmpty() || !chackIfTargetsExist(targets)) 
		{
			throw new RuntimeException("targets list is empty or not all targets id's exsits in the graph");
		}
		ArrayList<node_data> ans = new ArrayList<node_data>();
		if (targets.size()==1)
		{
			ans.add(this.graph.getNode(targets.get(0)));
			return ans;
		}
		ArrayList <node_data> partialPath = new ArrayList<node_data>();
		for (int target : targets) 
		{
			partialPath = (ArrayList<node_data>) shortestPath(target, target+1);
			if (target != 0) 
			{
				partialPath.remove(0);
			}
			for (node_data currNode : partialPath) 
			{
				ans.add(currNode);
			}
			partialPath.clear();
		}
		return ans;
	}
		
	@Override
	public graph copy() 
	{
		DGraph copiedGraph=new DGraph();
		for (node_data n: this.graph.getV()) 
		{
			nodeData copy_node = new nodeData (n.getKey(),n.getWeight(),n.getTag(),n.getLocation(),n.getInfo());
			copiedGraph.addNode(copy_node);
			
			if(this.graph.getE(n.getKey())!=null)
			{
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
			
		}
		return copiedGraph;
	}
	
	public boolean chackIfTargetsExist(List<Integer> targets) 
	{
		for (int target: targets) 
		{
			if (this.graph.getNode(target)==null)
			{
				return false;
			}	
		}
		return true;
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
	
	public graph getGraph()
	{
		return this.graph;
	}
	
	
	
	
	
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		Point3D p1=new Point3D(10,15,0);
		Point3D p2=new Point3D(50,60,0);
		Point3D p3=new Point3D(90,40,0);
		Point3D p4=new Point3D(20,50,0);
		Point3D p5=new Point3D(80,10,0);
		Point3D p6=new Point3D(5,90,0);


		nodeData a=new nodeData(1,0,p1,0);
		nodeData b=new nodeData(2,0,p2,0);
		nodeData c=new nodeData(3,0,p3,0);



		Hashtable<Integer, node_data> hashnodes=new Hashtable<Integer, node_data> ();
		hashnodes.put(a.getKey(), a);
		hashnodes.put(b.getKey(), b);
		hashnodes.put(c.getKey(), c);


		edgeData aa=new edgeData(a,b,40,0);

		edgeData cc=new edgeData(b,c,20,0);
		edgeData dd=new edgeData(a,c,80,0);


		Hashtable<Integer, edge_data>hash_a=new Hashtable<Integer, edge_data>();
		Hashtable<Integer, edge_data>hash_b=new Hashtable<Integer, edge_data>();
		Hashtable<Integer, edge_data>hash_c=new Hashtable<Integer, edge_data>();

		hash_a.put(aa.getDest(), aa);

		hash_b.put(cc.getDest(), cc);
		hash_a.put(dd.getDest(), dd);



		DGraph x=new DGraph();	
		Hashtable<Integer , Hashtable <Integer,edge_data>> hashedges=new Hashtable<Integer, Hashtable<Integer, edge_data>>();
		hashedges.put(a.getKey(), hash_a);
		hashedges.put(b.getKey(),hash_b);
		hashedges.put(c.getKey(),hash_c);
		x.setHashnodes(hashnodes);
		x.setEdgeHash(hashedges);
		
		




		Graph_Algo test=new Graph_Algo(x);
		Graph_Algo afterInit=new Graph_Algo();

		test.save("saveFile.txt");
		afterInit.init("saveFile.txt");



		
	}

}		
		
