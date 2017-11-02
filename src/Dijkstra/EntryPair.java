package Dijkstra;

public class EntryPair {
	 public String value;
	  public long priority;
	  boolean known;

	  public EntryPair(String aValue, long aPriority) {
	    value = aValue;
	    priority = aPriority;
	    known = false;
	  }
	  public void setKnown(){
		 known = true;
	  }
	  public String getValue() { return value; }
	  public long getPriority() { return priority; }
}
