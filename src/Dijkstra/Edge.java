package Dijkstra;


public class Edge {
	private long weight;
	private long id;
	private Vertex source, destination;
	private String label;
	public Edge(Vertex s, Vertex d, long a, long b, String l){
		source = s;
		destination = d;
		id = a;
		weight = b;
		label = l;
	}
	public long getID(){
		return id;
	}
	public long getWeight(){
		return weight;
	}
	public String getLabel(){
		return label;
	}
	public Vertex getSource(){
		return source;
	}
	public Vertex getDestination(){
		return destination;
	}
	public void del(){
		source = null;
		if(destination != null){
		destination.removeEdge();
		}
		destination = null;
	}
	
}
