package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.nodeData;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class algoTest 
{	
    @Test
    public void init() 
    {
    	Graph_Algo algoGraph = new Graph_Algo();
        DGraph DGraph = new DGraph();
    	Point3D p1 = new Point3D(1,2,3);
    	Point3D p2 = new Point3D(4,5,6);
    	Point3D p3 = new Point3D(7,8,9);
        nodeData n1 = new nodeData(1,0,p1,0);
        nodeData n2 = new nodeData(9,0,p2,0);
        nodeData n3 = new nodeData(5,0,p3,0);
        DGraph.addNode(n1);
        DGraph.addNode(n2);
        DGraph.addNode(n3);
        DGraph.connect(1,9,5);
        DGraph.connect(9,5,5);
        DGraph.connect(5,1,5);
        algoGraph.init(DGraph);
        assertEquals(DGraph,algoGraph.getGraph());
    }

    @Test
    public void testInit() 
    {
    	Graph_Algo algoGraph = new Graph_Algo();
        DGraph DGraph = new DGraph();
    	Point3D p1 = new Point3D(1,2,3);
    	Point3D p2 = new Point3D(4,5,6);
    	Point3D p3 = new Point3D(7,8,9);
        nodeData n1 = new nodeData(1,0,p1,0);
        nodeData n2 = new nodeData(9,0,p2,0);
        nodeData n3 = new nodeData(5,0,p3,0);
        DGraph.addNode(n1);
        DGraph.addNode(n2);
        DGraph.addNode(n3);
        DGraph.connect(1,9,5);
        DGraph.connect(9,5,5);
        DGraph.connect(5,1,5);
        algoGraph.init(DGraph);
        algoGraph.save("Check_Init");
	    Graph_Algo algoGraph_2 = new Graph_Algo();
	    algoGraph_2.init("Check_Init");
	    Boolean flag = algoGraph.getGraph().getEdge(1,9).getWeight() == algoGraph_2.getGraph().getEdge(1,9).getWeight();
	    Boolean flag2 = algoGraph.isConnected() == algoGraph_2.isConnected();
	    assertEquals(true,flag);
	    assertEquals(true,flag2);

    }

    @Test
    public void save() 
    {
    	Graph_Algo algoGraph = new Graph_Algo();
        DGraph DGraph = new DGraph();
    	Point3D p1 = new Point3D(1,2,3);
    	Point3D p2 = new Point3D(4,5,6);
    	Point3D p3 = new Point3D(7,8,9);
        nodeData n1 = new nodeData(1,0,p1,0);
        nodeData n2 = new nodeData(9,0,p2,0);
        nodeData n3 = new nodeData(5,0,p3,0);
        DGraph.addNode(n1);
        DGraph.addNode(n2);
        DGraph.addNode(n3);
        DGraph.connect(1,9,5);
        DGraph.connect(9,5,5);
        DGraph.connect(5,1,5);
        algoGraph.init(DGraph);
        algoGraph.save("Check_Save");
        Graph_Algo algoGraph_2 = new Graph_Algo();
        algoGraph_2.init("Check_Save");
        Boolean flag = algoGraph.getGraph().getEdge(1,9).getWeight() == algoGraph_2.getGraph().getEdge(9,5).getWeight();
        Boolean flag2 = algoGraph.isConnected() == algoGraph_2.isConnected();
        assertEquals(true,flag);
        assertEquals(true,flag2);
    }

    @Test
    public void isConnected() 
    {
    	Graph_Algo algoGraph = new Graph_Algo();
        DGraph DGraph = new DGraph();
    	Point3D p1 = new Point3D(1,2,3);
    	Point3D p2 = new Point3D(4,5,6);
    	Point3D p3 = new Point3D(7,8,9);
        nodeData n1 = new nodeData(1,0,p1,0);
        nodeData n2 = new nodeData(9,0,p2,0);
        nodeData n3 = new nodeData(5,0,p3,0);
        DGraph.addNode(n1);
        DGraph.addNode(n2);
        DGraph.addNode(n3);
        DGraph.connect(1,9,5);
        DGraph.connect(9,5,5);
        DGraph.connect(5,1,5);
        algoGraph.init(DGraph);
        assertEquals(true,algoGraph.isConnected());
        DGraph.removeEdge(5, 1);
        algoGraph.init(DGraph);
        assertEquals(false,algoGraph.isConnected());
    }

    @Test
    public void shortestPathDist() 
    {
    	Graph_Algo algoGraph = new Graph_Algo();
        DGraph DGraph = new DGraph();
    	Point3D p1 = new Point3D(1,2,3);
    	Point3D p2 = new Point3D(4,5,6);
    	Point3D p3 = new Point3D(7,8,9);
        nodeData n1 = new nodeData(1,0,p1,0);
        nodeData n2 = new nodeData(9,0,p2,0);
        nodeData n3 = new nodeData(5,0,p3,0);
        DGraph.addNode(n1);
        DGraph.addNode(n2);
        DGraph.addNode(n3);
        DGraph.connect(1,9,1);
        DGraph.connect(9,5,2);
        DGraph.connect(5,1,6);
        algoGraph.init(DGraph);
        boolean flag = 1 == algoGraph.shortestPathDist(1,9);
        assertEquals(true,flag);
        DGraph.connect(1,5,5);
        algoGraph.init(DGraph);
        boolean flag2 = 3 == algoGraph.shortestPathDist(1,5);
        assertEquals(true,flag2);
    }

    @Test
    public void shortestPath() 
    {
        Point3D p1 = new Point3D(14,4,0);
        Point3D p2 = new Point3D(-75,14,0);
        Point3D p3 = new Point3D(80,5,0);
        Point3D p4 = new Point3D(1,4,0);
        node_data n1 = new nodeData(1,0,p1,0);
        node_data n2 = new nodeData(2,0,p2,0);
        node_data n3 = new nodeData(3,0,p3,0);
        node_data n4 = new nodeData(4,0,p4,0);
        DGraph DGraph = new DGraph();
        DGraph.addNode(n1);
        DGraph.addNode(n2);
        DGraph.addNode(n3);
        DGraph.addNode(n4);
        DGraph.connect(n1.getKey(),n2.getKey(),5);
        DGraph.connect(n1.getKey(),n4.getKey(),2);
        DGraph.connect(n4.getKey(),n3.getKey(),6);
        DGraph.connect(n2.getKey(),n3.getKey(),7);
        DGraph.connect(n2.getKey(),n1.getKey(),8);
        DGraph.connect(n3.getKey(),n2.getKey(),8);
        DGraph.connect(n4.getKey(),n1.getKey(),8);
        Graph_Algo algoGraph = new Graph_Algo();
        algoGraph.init(DGraph);
        List<node_data> ans = new LinkedList<node_data>();
        ans = algoGraph.shortestPath(1,4);
        List<node_data> test = new LinkedList<node_data>();
        test.add(n1);
        test.add(n4);
        assertEquals(test.get(0).getKey(),ans.get(0).getKey());
        assertEquals(test.get(1).getKey(),ans.get(1).getKey());
    }

	    
	    @Test
	    public void TSP() 
	    {
	    	graph graph = new DGraph();
			Point3D p1 = new Point3D(1, 4, 5);
			Point3D p2 = new Point3D(2, 3, 6);
			Point3D p3 = new Point3D(3, 2, 7);
			Point3D p4 = new Point3D(4, 1, 8);
			nodeData n1 = new nodeData(1,0, p1, 2);
			nodeData n2 = new nodeData(2,0, p2, 1);
			nodeData n3 = new nodeData(3,0, p3, 1);
			nodeData n4 = new nodeData(4,0, p4, 1);
			graph.addNode(n1);
			graph.addNode(n2);
			graph.addNode(n3);
			graph.addNode(n4);
			graph.connect(1, 2, 7);
			graph.connect(2, 3, 2);
			graph.connect(3, 4, 23);
			graph.connect(4, 1, 10);
			Graph_Algo algoGraph = new Graph_Algo();
	        algoGraph.init(graph);
	        List<Integer> targe = new ArrayList();
	        targe.add(1);
	        targe.add(3);
	        List <node_data> nodeTsp =  algoGraph.TSP(targe);
	        List <node_data> nodeAns =  new ArrayList();
	        nodeAns.add(n1);
	        nodeAns.add(n2);
	        nodeAns.add(n3);
	        assertEquals(nodeTsp.get(0).getKey(),nodeAns.get(0).getKey());
	        assertEquals(nodeTsp.get(1).getKey(),nodeAns.get(1).getKey());
	        assertEquals(nodeTsp.get(2).getKey(),nodeAns.get(2).getKey());

	      
    }

    @Test
    public void copy() 
    {
    	Point3D p1 = new Point3D(14,4,0);
        Point3D p2 = new Point3D(-75,14,0);
        Point3D p3 = new Point3D(80,5,0);
        Point3D p4 = new Point3D(1,4,0);
        node_data n1 = new nodeData(1,0,p1,0);
        node_data n2 = new nodeData(2,0,p2,0);
        node_data n3 = new nodeData(3,0,p3,0);
        node_data n4 = new nodeData(4,0,p4,0);
        DGraph DGraph = new DGraph();
        DGraph.addNode(n1);
        DGraph.addNode(n2);
        DGraph.addNode(n3);
        DGraph.addNode(n4);
        DGraph.connect(n1.getKey(),n2.getKey(),5);
        DGraph.connect(n1.getKey(),n4.getKey(),2);
        DGraph.connect(n1.getKey(),n3.getKey(),6);
        DGraph.connect(n3.getKey(),n4.getKey(),7);
        Graph_Algo algoGraph = new Graph_Algo();
        algoGraph.init(DGraph);
        graph graph = new DGraph();
        graph = algoGraph.copy();
        assertNotEquals(graph,algoGraph.getGraph());
    }
	}