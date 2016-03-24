import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class Djkstra {

	public static void main(String [] args) throws Exception {

		//pass an array of arrays from the parse...
		Parse p = new Parse();
		Vertex[][] nodes = p.computeEdges();

		int n = p.getN();
		int[] dis = new int[n];
		int[] prev =  new int[n];
		Hashtable<Integer, Vertex> h = new Hashtable<Integer, Vertex>();

		//will need to identify depth of tree
		int depth = (int) Math.ceil((Math.log(n) / Math.log(2)));
		BinaryHeap b = new BinaryHeap((int)Math.pow(2, depth + 1)); // pad lowest level with nulls

		//set up source vertex
		dis[0] = 0;
		b.insert(new Vertex(0, 0));

		//set distance from source to all vertices as practically infinite
		for(int i=1; i<n; i++){
			dis[i] = 100000;
			Vertex v = new Vertex(i, dis[i]);
			b.insert(v);
			h.put(i, v); //put each vertex in a hash table by its vertex number
		}

		while(!b.isEmpty()) {
			Vertex u = b.extractMin();

			//now get neighbors of u
			for(int i = 0; i< nodes[u.getV()].length; i++){
				if(nodes[u.getV()][i] != null) {

					//get the distance from source to the neighbor of u
					int alt = nodes[u.getV()][i].getD() + dis[u.getV()];

					//if the alt route is less than this vertex's original route from source to itself, replace its smallest path
					if( alt < dis[nodes[u.getV()][i].getV()]) {
						//ugly but gets the index in the heap of the vertex of the neighbor. Allows for an O(1) heap update
						b.update(((Vertex) h.get(nodes[u.getV()][i].getV())).getIndex(), alt); //update the path value in the heap
						dis[nodes[u.getV()][i].getV()] = alt; //
						prev[nodes[u.getV()][i].getV()] = u.getV();
					}
				}
			}
		}

		int sum = 0;
		for(int i = 0; i< dis.length; i++) {
			sum += dis[i];
		}

		System.out.println(sum);

	}
}
