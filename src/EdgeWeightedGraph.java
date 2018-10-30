
public class EdgeWeightedGraph {

	private final int V;
	private int E;
	private Bag<Edge>[] adj;

	public EdgeWeightedGraph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<Edge>[]) new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<Edge>();
		}

	}

	public EdgeWeightedGraph(In in) {
		this(in.readInt());
		int E = in.readInt();
		for (int i = 0; i < E; i++) {
			int v = in.readInt();
			int w = in.readInt();
			double weight = in.readInt();
			int id = in.readInt();
			int times = 0;
			String from = in.readString();
			String to = in.readString();
			
			addEdge(new Edge(v, w, weight, id, times, from, to));
		}
	}

	public int V() {
		return V;
	}

	public int E() {
		return E;
	}

	public void addEdge(Edge e) {
		int v = e.either();
		int w = e.other(v);
		adj[v].add(e);
		adj[w].add(e);
		E++;
	}
	public void removeEdge(Edge e){
		int v = e.either();
		int w = e.other(v);
		adj[v].remove(e);
		adj[w].remove(e);
		E--;
	}

	public Iterable<Edge> adj(int v) {
		return adj[v];
	}

	public Iterable<Edge> edges() {
		Bag<Edge> b = new Bag<Edge>();
		for (int v = 0; v < V; v++) {
			for (Edge e : adj[v]) {
				if (e.other(v) > v) {
					b.add(e);
				}
			}
		}
		return b;
	}

	public String toString() {
		String s = "";
		for (int v = 0; v < V(); v++) {
			s += v + ": ";
			for (Edge e : adj[v]) {
				
				
				s += e.other(v) + " (weight " + e.weight() + "), ";
				
			}
			s += "\n---------------------------------\n";
		}
		return s;
	}
}
