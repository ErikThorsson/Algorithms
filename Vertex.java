
public class Vertex {
	private int v;
	private int dist;
	private int index;
	
	Vertex(int n, int d) {
		v = n;
		dist = d;
		index = -1;
	}
	
	public int getV(){return v;}
	
	public int getD(){return dist;}
	
	public void setV(int val){v = val;}
	
	public void setD(int d){dist = d;}
	
	public int getIndex(){return index;}
	
	public void setIndex(int i){index = i;}
}
