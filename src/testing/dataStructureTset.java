package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import dataStructure.DGraph;
import dataStructure.*;
import org.w3c.dom.Node;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import dataStructure.DGraph;
import dataStructure.edgeData;
import dataStructure.nodeData;
import dataStructure.nodeData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import utils.Point3D;

import static org.junit.Assert.*;
import static org.junit.Assert.*;
class dataStructureTset 
{


		@Test
	    public void getNode() 
	    {
	        DGraph graph= new DGraph();
	        Point3D p = new Point3D(1,2,3);
	        Point3D p2 = new Point3D(2,3,4);
	        nodeData test1 = new nodeData(1,0,p,0);
	        nodeData test2 = new nodeData(2,0,p2,0);
	        graph.addNode(test1);
	        graph.addNode(test2);
	        assertEquals(graph.getNode(1),test1);
	        assertEquals(graph.getNode(2),test2);
	    }

		@Test
	    public void getEdge() 
	    {
	    	DGraph graph= new DGraph();
	        Point3D p = new Point3D(1,2,3);
	        Point3D p2 = new Point3D(4,5,6);
	        Point3D p3 = new Point3D(7,8,9);
	        nodeData test1 = new nodeData(1,0,p,0);
	        nodeData test2 = new nodeData(2,0,p2,0);
	        nodeData test3 = new nodeData(3,0,p3,0);
	        graph.addNode(test1);
	        graph.addNode(test2);
	        graph.addNode(test3);
	        graph.connect(1, 2, 10);
	        graph.connect(2, 3, 2);
	        graph.connect(3, 1, 8);
	        assertEquals(0, 0);
	        boolean flag1 = graph.edge.get(1).containsKey(2);
	        boolean flag2 = graph.edge.get(2).containsKey(3);
	        boolean flag3 = graph.edge.get(3).containsKey(1);
	        assertEquals(true, flag1);
	        assertEquals(true, flag2);
	        assertEquals(true, flag3);
	    }

		@Test
	    public void addNode() 
	    {
	    	DGraph graph= new DGraph();
	        Point3D p = new Point3D(1,2,3);
	        Point3D p2 = new Point3D(7,2,5);
	        nodeData test1 = new nodeData(1,0,p,0);
	        nodeData test2 = new nodeData(2,0,p2,0);
	        graph.addNode(test1);
	        graph.addNode(test2);
	        assertEquals(graph.getNode(1),test1);
	        assertEquals(graph.getNode(2),test2);
	    }

		@Test
	    public void connect() 
	    {
	    	DGraph graph= new DGraph();
	        Point3D p = new Point3D(1,2,3);
	        Point3D p2 = new Point3D(5,2,3);
	        Point3D p3 = new Point3D(8,10,3);
	        nodeData test1 = new nodeData(1,0,p,0);
	        nodeData test2 = new nodeData(2,0,p2,0);
	        nodeData test3 = new nodeData(3,0,p3,0);
	        graph.addNode(test1);
	        graph.addNode(test2);
	        graph.addNode(test3);
	        graph.connect(1, 3, 10);
	        graph.connect(3, 2, 10);
	        graph.connect(2, 1, 10);
	        boolean flag1 = graph.edge.get(1).containsKey(3);
	        boolean flag2 = graph.edge.get(2).containsKey(1);
	        boolean flag3 = graph.edge.get(3).containsKey(2);
	        assertEquals(true, flag1);
	        assertEquals(true, flag2);
	        assertEquals(true, flag3);
	    }

		@Test
	    public void removeNode() 
	    {
			DGraph graph= new DGraph();
	        Point3D p = new Point3D(1,2,3);
	        Point3D p2 = new Point3D(5,2,3);
	        Point3D p3 = new Point3D(8,10,3);
	        nodeData test1 = new nodeData(1,0,p,0);
	        nodeData test2 = new nodeData(2,0,p2,0);
	        nodeData test3 = new nodeData(3,0,p3,0);
	        graph.addNode(test1);
	        graph.addNode(test2);
	        graph.addNode(test3);
	        graph.removeNode(1);
	        graph.removeNode(3);
	        assertEquals(null,graph.getNode(1));
	        assertEquals(null,graph.getNode(3));
	        assertNotEquals(null,graph.getNode(2));

	    }

		@Test
	    public void removeEdge()  
	    {
			DGraph graph= new DGraph();
	        Point3D p = new Point3D(1,2,3);
	        Point3D p2 = new Point3D(5,2,3);
	        Point3D p3 = new Point3D(8,10,3);
	        nodeData test1 = new nodeData(1,0,p,0);
	        nodeData test2 = new nodeData(2,0,p2,0);
	        nodeData test3 = new nodeData(3,0,p3,0);
	        graph.addNode(test1);
	        graph.addNode(test2);
	        graph.addNode(test3);
	        graph.connect(1, 2, 10);
	        graph.connect(2, 3, 10);
	        graph.connect(3, 1, 10);
	        assertEquals(graph.getEdge(1,2),graph.edge.get(1).get(2));
	        graph.removeEdge(1,2);
	        assertEquals(graph.getEdge(1,2),null);
	        assertEquals(graph.getEdge(3,1),graph.edge.get(3).get(1));
	        graph.removeEdge(3,1);
	        assertEquals(graph.getEdge(3,1),null);
	    }

		@Test
	    public void nodeSize() 
	    {
			DGraph graph= new DGraph();
	        Point3D p = new Point3D(1,2,3);
	        Point3D p2 = new Point3D(5,2,3);
	        Point3D p3 = new Point3D(8,10,3);
	        nodeData test1 = new nodeData(1,0,p,0);
	        nodeData test2 = new nodeData(2,0,p2,0);
	        nodeData test3 = new nodeData(3,0,p3,0);
	        graph.addNode(test1);
	        graph.addNode(test2);
	        graph.addNode(test3);
	        assertEquals(3,graph.nodeSize());
	        graph.removeNode(2);
	        graph.removeNode(3);
	        assertEquals(1,graph.nodeSize());
	    }

		@Test
	    public void edgeSize()  
	    {
			DGraph graph= new DGraph();
	        Point3D p = new Point3D(1,2,3);
	        Point3D p2 = new Point3D(5,2,3);
	        Point3D p3 = new Point3D(8,10,3);
	        nodeData test1 = new nodeData(1,0,p,0);
	        nodeData test2 = new nodeData(2,0,p2,0);
	        nodeData test3 = new nodeData(3,0,p3,0);
	        graph.addNode(test1);
	        graph.addNode(test2);
	        graph.addNode(test3);
	        graph.connect(1, 2, 10);
	        graph.connect(2, 3, 10);
	        graph.connect(3, 1, 10);
	        assertEquals(graph.edgeSize(),3);
	        graph.removeEdge(2,3);
	        graph.removeEdge(1,2);
	        assertEquals(1,graph.edgeSize());
	    }

		@Test
	    public void getMC()  
	    {
	    	DGraph graph= new DGraph();
	        Point3D p = new Point3D(1,2,3);
	        nodeData test1 = new nodeData(1,0,p,0);
	        nodeData test2 = new nodeData(2,0,p,0);
	        nodeData test3 = new nodeData(3,0,p,0);
	        graph.addNode(test1);
	        graph.addNode(test2);
	        graph.addNode(test3);
	        graph.connect(1, 2, 10);
	        graph.connect(2, 3, 10);
	        graph.connect(3, 1, 10);
	        graph.removeEdge(2,3);
	        graph.removeEdge(3,1);
	        graph.removeNode(3);
	        assertEquals(9,graph.getMC());
	    }
		@Test
		void getSrcDest() 
		{
	        edgeData test1 = new edgeData(1,2,3);
	        edgeData test2 = new edgeData(2,3,3);
			assertEquals(test1.getSrc(),1);
			assertEquals(test2.getSrc(),2);
			assertEquals(test1.getDest(),2);
			assertEquals(test2.getDest(),3);
		 }

		@Test
	    void EdgeGetWeight() 
		{
	        edgeData test1 = new edgeData(1,1,1);
	        edgeData test2 = new edgeData(2,2,2);
	        assertEquals(test1.getWeight(),1);
	        assertEquals(test2.getWeight(),2);
	    }

		@Test
	    void EdgeGetSetInfo() 
		{
			edgeData test1 = new edgeData(1,1,1);
	        edgeData test2 = new edgeData(2,2,2);
	        test1.setInfo("test1");
	        test2.setInfo("test2");
	        assertEquals(test1.getInfo(),"test1");
	        assertEquals(test2.getInfo(),"test2");
	    }

		@Test
	    void EdgeGetSetTag() 
		{
	        edgeData test1 = new edgeData(1,1,1);
	        edgeData test2 = new edgeData(2,2,2);
	        test1.setTag(5);
	        test2.setTag(8);
	        assertEquals(test1.getTag(),5);
	        assertEquals(test2.getTag(),8);
		} 

	    @Test
	    public void NodeGetKey() 
	    {
    	Point3D p1 = new Point3D(4,9,7);
    	Point3D p2 = new Point3D(4,6,3);
        nodeData n1 = new nodeData(1,0,p1,0);
        nodeData n2 = new nodeData(2,0,p2,0);
        DGraph graph = new DGraph();
        graph.addNode(n1);
        graph.addNode(n2);
        assertEquals(1,n1.getKey());
        assertEquals(2,n2.getKey());
	    }

	    @Test
	    public void NodeGetSetLocation() 
	    {
	    	Point3D p1 = new Point3D(5,3,6);
	    	Point3D p2 = new Point3D(6,8,10);
	    	nodeData n1 = new nodeData(1,0,p1,0);
	    	nodeData n2 = new nodeData(2,0,p2,0);
	    	assertEquals(p1,n1.getLocation());
		    assertEquals(p2,n2.getLocation());
	    	n2.setLocation(p1);
	        assertEquals(p1,n2.getLocation());
	    }

	    @Test
	    public void NodeGetSetWeight() 
	    {
	    	Point3D p1 = new Point3D(5,3,6);
	    	Point3D p2 = new Point3D(1,2,9);
	    	Point3D p3 = new Point3D(67,45,90);
	    	nodeData n1 = new nodeData(1,0,p1,0);
	    	nodeData n2 = new nodeData(2,0,p2,0);
	        nodeData n3 = new nodeData(3,0,p3,0);
	        n1.setWeight(89);
	        n2.setWeight(267);
	        n3.setWeight(20);
	        boolean flag1 = 89 == n1.getWeight();
	        boolean flag2 = 267 == n2.getWeight();
	        boolean flag3 = 20 == n3.getWeight();
	        assertEquals(true,flag1);
	        assertEquals(true,flag2);
	        assertEquals(true,flag3);
	    }

	    @Test
	    public void NodeGetSetInfo() 
	    {
	    	Point3D p1 = new Point3D(7,3,2);
	    	nodeData n1 = new nodeData(1,0,p1,0);
	        n1.setInfo("test");
	        assertEquals("test",n1.getInfo());
	        n1.setInfo("check");
	        assertEquals("check",n1.getInfo());

	    }

	    @Test
	    public void nodeGetSetTag() 
	    {
	    	Point3D p1 = new Point3D(5,9,3);
	    	nodeData n1 = new nodeData(1,0,p1,0);
	        n1.setTag(8);
	        assertEquals(8,n1.getTag());
	        
	    }
	}

		
	