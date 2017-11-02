package Dijkstra;

public class DiGraphPlayground {

	public static void main (String[] args) {
		  
	      // thorough testing is your responsibility
	      //
	      // you may wish to create methods like 
	      //    -- print
	      //    -- sort
	      //    -- random fill
	      //    -- etc.
	      // in order to convince yourself your code is producing
	      // the correct behavior
	    exTest();
	    }

    public static void exTest(){
      DiGraph d = new DiGraph();
      d.addNode(1, "R");
      d.addNode(2, "D");
      d.addNode(3, "P");
      d.addNode(4, "LA");
      d.addNode(5, "G");
      d.addNode(6, "CA");
      d.addNode(7, "CH");
      d.addNode(8, "H");
      d.addNode(9, "S");
      d.addEdge(1, "R", "D", 14, null);
      d.addEdge(2, "D", "H", 9, null);
      d.addEdge(3, "CH", "G", 25, null);
      d.addEdge(6, "CA", "R", 3, null);
      d.addEdge(7, "P", "CA", 17, null);
      d.addEdge(8, "P", "S", 15, null);
      d.addEdge(9, "S", "LA", 3012, null);
      ShortestPathInfo [] a = d.shortestPath("P");
      for(int i = 0; i < a.length; i++){
    	  System.out.println("Node " + a[i].getDest() + " Weight " + a[i].getTotalWeight());
      }
    }
}
