# Graphs

this project represent data structure for directed graph. including data structure how fit to graphs, numbers of algorithms on graphs and also provides GUI to user to see the graphs.

the project divide to four package:

## algorithms package contains the following class:
### graph_algorithms (interface): represents the "regular" Graph Theory algorithms including:
 * clone()
 * init(String file_name);
 * save(String file_name);
 * isConnected();
 * double shortestPathDist(int src, int dest);
 * List<Node> shortestPath(int src, int dest);

### Graph_Algo: represents the set of graph-theory algorithms that implement the interface.
