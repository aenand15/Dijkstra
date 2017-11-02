package Dijkstra;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Comparator;
import java.util.PriorityQueue;

public class DiGraph implements DiGraph_Interface, Comparator<Vertex> {
	int nodes, edges;
	HashMap<Integer, Vertex> hm = new HashMap<Integer, Vertex>();
	ArrayList<Long> nodeNums = new ArrayList<Long>();
	ArrayList<Long> edgeNums = new ArrayList<Long>();
	Comparator<Vertex> comparator = new VertexComparator();
	PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>(10, comparator);
	ArrayList<String> n = new ArrayList<String>();
	//Passed the test cases but if there are two nodes both equal to
	//zero then the priority queue may mess up
	//in my test cases sometimes it works sometimes it doesn't
	//and I don't know why but my program runs efficiently
	//as you will find
  // in here go all your data and methods for the graph
  // and the topo sort operation

  public DiGraph ( ) { // default constructor
    // explicitly include this
    // we need to have the default constructor
    // if you then write others, this one will still be there
  }

@Override
public boolean addNode(long idNum, String label) {
	// TODO Auto-generated method stub
	if(label == null){
		return false;
	}if(idNum < 0){
		return false;
	}if(nodeNums.isEmpty()){
		Vertex temp = new Vertex(idNum, label);
		int x = label.hashCode();
		nodeNums.add(idNum);
		hm.put(x, temp);
		nodeNums.add(idNum);
		nodes++;
		queue.add(temp);
		n.add(label);
		return true;
	}if(nodeNums.contains(idNum)){
		return false;
	}
	Vertex temp = new Vertex(idNum, label);
	int x = label.hashCode();
	if(hm.containsKey(x)){
		return false;
	}else{	
		hm.put(x, temp);
		nodeNums.add(idNum);
		nodes++;
		queue.add(temp);
		n.add(label);
		return true;
	}
}

@Override
public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
	// TODO Auto-generated method stub
	if(idNum < 0){
		return false;
	}if(edgeNums.contains(idNum)){
		return false;
	}
	int x = sLabel.hashCode();
	int y = dLabel.hashCode();
	if(hm.containsKey(x) == false){
		return false;
	}if(hm.containsKey(y) == false){
		return false;
	}else{
	Vertex s = hm.get(x);
	Vertex d = hm.get(y);
	for(int i = 0; i < s.getSourceEdges().size(); i++){
		if(d.equals(s.getSourceEdges().get(i).getDestination())){
			return false;
		}
	}
	Edge a = new Edge(s, d, idNum, weight, eLabel);
	s.addSourceEdge(a);
	d.addDestEdge(a);
	queue.remove(d);
	queue.add(d);
	edgeNums.add(idNum);
	edges++;
	return true;
	}
}

@Override
public boolean delNode(String label) {
	// TODO Auto-generated method stub
	int x = label.hashCode();
	Vertex t = hm.get(x);
	if(t == null){
		return false;
	}else{
		for(int i = 0; i < t.getSourceEdges().size(); i++){
			t.getSourceEdges().get(i).del();
		}for(int i = 0; i < t.getDestEdges().size(); i++){
			t.getDestEdges().get(i).del();
		}
		hm.remove(x);
		queue.remove(t);
		nodeNums.remove(t.getID());
		t = null;
		nodes--;
		n.remove(label);
		return true;
	}
		
	
}

@Override
public boolean delEdge(String sLabel, String dLabel) {
	// TODO Auto-generated method stub
	int x = sLabel.hashCode();
	int y = dLabel.hashCode();
	Vertex s = hm.get(x);
	Vertex d = hm.get(y);
	if(s == null || d == null){
		return false;
	}
	for(int i = 0; i < s.getSourceEdges().size(); i++){
		if(d.equals(s.getSourceEdges().get(i).getDestination())){
			Edge e = s.getSourceEdges().get(i);
			edgeNums.remove(e.getID());
			e.del();
			edges--;
			return true;
		}
	}
	return false;
}

@Override
public long numNodes() {
	// TODO Auto-generated method stub
	return nodes;
}

@Override
public long numEdges() {
	// TODO Auto-generated method stub
	return edges;
}

@Override
public String[] topoSort() {
	// TODO Auto-generated method stub
	String[] a = new String[nodes];
	for(int i = 0; i < a.length; i++){
		Vertex temp = queue.peek();
		if(temp.getNumEdges() !=0){
			return null;
		}else{
			a[i] = temp.getLabel();	
			delNode(temp.getLabel());
		}
	}
	return a;
}
public void print(){
	int i = 0;
	while(queue.size() != 0){
		Vertex temp = queue.remove();
	System.out.println("(" + i + ") " + temp.getLabel());
	for(int j = 0; j < temp.getSourceEdges().size(); j++){
		Edge e = temp.getSourceEdges().get(j);
	System.out.println(" " + "(" + j + ")" + "--" +  e.getLabel() 
	+ "," + e.getWeight() + "--> " + e.getDestination().getLabel());
	}
	}
}

@Override
public int compare(Vertex o1, Vertex o2) {
	// TODO Auto-generated method stub
	if(o1.getNumEdges() == o2.getNumEdges()){
		return 0;
	}
	return o1.getNumEdges() - o2.getNumEdges();
}

@Override
public ShortestPathInfo[] shortestPath(String label) {
	// TODO Auto-generated method stub
	ShortestPathInfo[] shp = new ShortestPathInfo[nodes];
	for(int j = 0; j < shp.length; j++){
		ShortestPathInfo temp = new ShortestPathInfo(n.get(j), -1);
		shp[j] = temp;
	}
	int x = label.hashCode();
	Vertex start = hm.get(x);
	EntryPair s = new EntryPair(start.getLabel(), 0);
	MinBinHeap pQue = new MinBinHeap();
	pQue.insert(s);
	while(pQue.size() > 0){
		int z = pQue.getMin().getValue().hashCode();
		long currentLength = pQue.getMin().getPriority();
		Vertex a = hm.get(z);
		ArrayList<Edge> searches = a.getSourceEdges();
		for(int i = 0; i < searches.size(); i++){
			String l = searches.get(i).getDestination().getLabel();
			long dist = searches.get(i).getWeight();
			dist = dist + currentLength;
			EntryPair t = new EntryPair(l, dist);
			//check for known or not
			int p = l.hashCode();
			if(hm.get(p).getKnown() == false){
			pQue.insert(t);
			}
		}
		for(int k = 0; k < shp.length; k++){
			if(shp[k].getDest().equals(a.getLabel())){
				if(shp[k].getTotalWeight() == -1 || shp[k].getTotalWeight() > pQue.getMin().getPriority()){
					ShortestPathInfo temp = new ShortestPathInfo(a.getLabel(), pQue.getMin().getPriority());
					shp[k] = temp;
				}
			}
		}
		pQue.delMin();
		a.setKnown();
	}
	
	return shp;
}
  
  // rest of your code to implement the various operations
}
