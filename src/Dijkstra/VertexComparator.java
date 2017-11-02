package Dijkstra;

import java.util.Comparator;

public class VertexComparator implements Comparator<Vertex>{

	@Override
	public int compare(Vertex o1, Vertex o2) {
		// TODO Auto-generated method stub
		return o1.getNumEdges()-o2.getNumEdges();
	}

}
