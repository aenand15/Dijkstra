package Dijkstra;

import java.util.ArrayList;

public class Vertex {
	//source list is list when vertex is source edge
	//dest list is list when vertex is dest on edge
	//topo sort is based on dest edges
	private long id;
	private String label;
	private ArrayList<Edge> source = new ArrayList<Edge>();
	private ArrayList<Edge> dest = new ArrayList<Edge>();
	private int numEdges;
	private boolean known;
	public Vertex(long a, String b){
		numEdges = 0;
		id = a;
		label = b;
		known = false;
	}
	public long getID(){
		return id;
	}
	public String getLabel(){
		return label;
	}
	public void addSourceEdge(Edge e){
		source.add(e);
	}
	public void addDestEdge(Edge e){
		numEdges++;
		dest.add(e);
	}
	public ArrayList<Edge> getSourceEdges(){
		return source;
	}
	public ArrayList<Edge> getDestEdges(){
		return dest;
	}
	public int getNumEdges(){
		return numEdges;
	}
	public void removeEdge(){
		numEdges--;
	}
	public boolean getKnown(){
		return known;
	}
	public void setKnown(){
		known = true;
	}
}
