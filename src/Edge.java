
public class Edge implements Comparable<Edge> {
	private  int v;
	private final int w;
	private final double weight;
	public int timesUsed;
	public int id;
	public String from, to;


	public Edge(int v, int w, double weight, int id ,int timesUsed, String from, String to) {
		this.v = v;
		this.w = w;
		this.weight = weight;
		this.timesUsed = timesUsed;
		this.id = id;
		this.from = from;
		this.to = to;
	}

	public double weight() {
		return weight;
	}
	public String getFrom(){
		return from;
	}
	public String getTo(){
		return to;
	}
	
    public int times(){
    	return timesUsed;
    }
    
    public int edgeID(){
    	return id;
    }

	public int either() {
		return v;
	}
	public void eitherNull(){
		v = Integer.MAX_VALUE;
	}

	public int other(int vertex) {
		if (vertex == v) {
			return w;
		} else if (vertex == w) {
			return v;
		} else {
			throw new RuntimeException("Inconsistent edge");
		}
	}

	public int compareTo(Edge that) {
		if (this.weight() < that.weight()) {
			return -1;
		} else if (this.weight() > that.weight()) {
			return 1;
		} else {
			return 0;
		}
	}

	public String toString() {
		//return String.format("%d - %d %.2f", v, w, weight);
		return "(" +from  + "-" +to + ")"+" " + String.format("%.2f", weight) + " " + "->";
	}
}
