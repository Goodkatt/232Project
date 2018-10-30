import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;



public class SymbolGraphTwo {
	private static HashMap<Edge, Integer> st4;
    private static LinearProbingHashST<String, Integer> st, st2; // string -> index
    private static HashMap<Integer, String> stt;
    private static HashMap<String, Integer>st3;
    //private static LinearProbingHashST<Integer, String>st, st2;
    private static String[] keys;           // index  -> string
    private EdgeWeightedGraph graph, graph2;// the underlying graph
    //private static LinearProbingHashST<String, Integer> st = st = new LinearProbingHashST<String, Integer>();
    
    public void hue(String stream, String sp){
    	In in = new In(stream);
    	in.readLine();
    	stt = new HashMap<Integer, String>();
    	
    	while (in.hasNextLine()) // builds the index
		{
			String[] a = in.readLine().split(sp); // by reading strings
			
			for(int i = 6; i < 8; i++){
				if(!stt.containsValue(a[i])){
					stt.put(stt.size(), a[i]);
				}

			}
		}
    	System.out.println(stt);
    }
    public void maxUsedEdges(HashMap<String, Integer> myHashMap){
    	for(int i = 0; i < 10; i++){
            Object maxEntry = Collections.max(myHashMap.entrySet(), Map.Entry.comparingByValue()).getKey();      
           

            System.out.println("max used number"+ " "+(i+1)+" edge is between " + maxEntry + " " + "is used" + " " + myHashMap.get(maxEntry) + " " + "times.");
            myHashMap.remove(maxEntry);
        }
    }


    
    /**  
     * Initializes a graph from a file using the specified delimiter.
     * Each line in the file contains
     * the name of a vertex, followed by a list of the names
     * of the vertices adjacent to that vertex, separated by the delimiter.
     * @param filename the name of the file
     * @param delimiter the delimiter between fields
     */
    public SymbolGraphTwo(String stream, String sp) {	
    	st3 = new HashMap<String, Integer>();
    	st4 = new HashMap<Edge, Integer>();					
    																
		st = new LinearProbingHashST<String, Integer>();
		st2 = new LinearProbingHashST<String, Integer>();			 
		In in = new In(stream); // First pass						
		in.readLine();
		int q = 0;
		while (in.hasNextLine()) // builds the index
		{
			String[] a = in.readLine().split(sp); // by reading strings
			for (int i = 0; i < 2; i++) // to associate each  			
			{
				if (!st.contains(a[i])) // distinct string
				{
					st.put(a[i], st.size()); // with an index.
					
				}
			}

		}

		
		//545977
		keys = new String[st.size()]; // Inverted index				
		for (String name : st.keys()) // to get string keys
		{
			keys[st.get(name)] = name; // is an array.
		}
		graph2 = new EdgeWeightedGraph(st.size());
		graph = new EdgeWeightedGraph(st.size());
		in = new In(stream); // Second pass
		in.readLine();
		int x = 0;

		while (in.hasNextLine()) // builds the graph				
		{
			String[] a = in.readLine().split(sp); // by connecting the
			int v = st.get(a[0]); // first vertex
			graph.addEdge(new Edge(v, st.get(a[1]), Double.parseDouble(a[4]), Integer.parseInt(a[3]) ,0, a[6], a[7]));
			st4.put(new Edge(v, st.get(a[1]), Double.parseDouble(a[4]), Integer.parseInt(a[3]), 0, a[6], a[7]), 0);
			
		}
    }

    /**
     * Does the graph contain the vertex named {@code s}?
     * @param s the name of a vertex
     * @return {@code true} if {@code s} is the name of a vertex, and {@code false} otherwise
     */
    public boolean contains(String s) {
        return st.contains(s);
    }

    /**
     * Returns the integer associated with the vertex named {@code s}.
     * @param s the name of a vertex
     * @return the integer (between 0 and <em>V</em> - 1) associated with the vertex named {@code s}
     * @deprecated Replaced by {@link #indexOf(String)}.
     */
    @Deprecated
    public int index(String s) {
        return st.get(s);
    }


    /**
     * Returns the integer associated with the vertex named {@code s}.
     * @param s the name of a vertex
     * @return the integer (between 0 and <em>V</em> - 1) associated with the vertex named {@code s}
     */
    public int indexOf(String s) {
        return st.get(s);
    }

    /**
     * Returns the name of the vertex associated with the integer {@code v}.
     * @param  v the integer corresponding to a vertex (between 0 and <em>V</em> - 1) 
     * @return the name of the vertex associated with the integer {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     * @deprecated Replaced by {@link #nameOf(int)}.
     */
    @Deprecated
    public String name(int v) {
        validateVertex(v);
        return keys[v];
    }

    /**
     * Returns the name of the vertex associated with the integer {@code v}.
     * @param  v the integer corresponding to a vertex (between 0 and <em>V</em> - 1) 
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     * @return the name of the vertex associated with the integer {@code v}
     */
    public String nameOf(int v) {
        validateVertex(v);
        return keys[v];
    }

    /**
     * Returns the graph assoicated with the symbol graph. It is the client's responsibility
     * not to mutate the graph.
     * @return the graph associated with the symbol graph
     * @deprecated Replaced by {@link #graph()}.
     */
    @Deprecated
    public EdgeWeightedGraph G() {
        return graph;
    }
    

    /**
     * Returns the graph assoicated with the symbol graph. It is the client's responsibility
     * not to mutate the graph.
     * @return the graph associated with the symbol graph
     */
    public EdgeWeightedGraph graph() {
        return graph;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
         int V = graph.V();
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }


    /**
     * Unit tests the {@code SymbolGraph} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
    	String filename = "/Users/grkmaltunay/Desktop/BabelTest.csv";
		String delim = ",";
        SymbolGraphTwo sg = new SymbolGraphTwo(filename, delim);
        EdgeWeightedGraph graphIn = sg.graph();						
        st2 = new LinearProbingHashST<String, Integer>();
        //sg.hue(filename, delim);

        
        for(int i = 0; i < st.size(); i++){											
			DijkstraEdgeWeighted xx = new DijkstraEdgeWeighted(graphIn, i);	
																			
																			
			for (int t = 0; t < graphIn.V(); t++) {
				if (xx.hasPathTo(t)) {
					System.out.print("Path from" + " " +sg.nameOf(i) + " " +"to" + " " + sg.nameOf(t)+":" + " " + "Weight is:" +"(" +xx.distTo(t) + ")" + " "+ "Path is:");
					//System.out.print("Path from" + " " +stt.get(i) + " " +"to" + " " + stt.get(t)+":" + " " + "Weight is:" +"(" +xx.distTo(t) + ")" + " "+ "PATH IS:" + "  ");

					for (Edge e : xx.pathTo(t)) {
						
						e.timesUsed++;
						//st3.put(e.edgeID(), e.timesUsed);
						st3.put(e.from +" " +e.to, e.timesUsed);
						
						StdOut.print(e + "    "  );

					}
                StdOut.println();
              
				}
				else {
	                    StdOut.printf("%d to %d         no path\n", i, t);
	            }
        }
			
		}
        

        sg.maxUsedEdges(st3);
       
        System.out.println(st4);
        System.out.println(st4.size());
        System.out.println(st4.keySet());
        System.out.println(st4.values());

    }
}