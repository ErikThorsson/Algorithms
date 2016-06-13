import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//testing testing testing

public class Parse {
	private int n = 0;
	private int m = 0;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Parse p = new Parse();
		Vertex[][] v = p.computeEdges();
		//Vertex[][] v = p.parse();
		System.out.print(v[999][0].getV());
	}

	public Vertex[][] parse() throws FileNotFoundException, IOException{
		int v = 0,count = 0;
		Vertex[][] vertices = null;

		try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\eorndahl\\Documents\\gitTest\\25000.txt"))) {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			boolean inside = false;

			while (line != null) {

				//get n and m
				if(line.contains("n")) {
					String split[] = line.split(" ");
					String splitN[] = split[0].split("=");
					n = Integer.parseInt(splitN[1]);
					String splitM[] = split[1].split("=");
					m = Integer.parseInt(splitM[1]);
					//System.out.print(n + " " + m);
					vertices = new Vertex[n][];
					for(int i = 0; i < n; i++) {
						vertices[i] = new Vertex[200];
					}
				}

				//if line contains 1 number and no spaces...
				if(line.matches("^[0-9]+")) {
					v = Integer.parseInt(line.trim());
					inside = true;
					count = 0;
					//System.out.println(line);
					line = br.readLine();
					continue;
				}

				if(line.trim().isEmpty()) { 
					inside = false;
					//System.out.println("BLANK");
				}

				if(inside == true) {
					//System.out.println(line);
					String[] split = line.split("\\s+");
					int neigh = Integer.parseInt(split[1].trim());
					//System.out.println(neigh);
					int dis = Integer.parseInt(split[2].trim());
					vertices[v][count] = new Vertex(neigh, dis);
					count++;
				}

				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
		}
		return vertices;
	}

	public Vertex[][] computeEdges() throws FileNotFoundException, IOException{
		Vertex[][] v = parse();
		for(int i = 0; i< v.length; i++) {
			for(int k = 0; k < v[i].length; k++) {
				//check to see if the neighbor has this vertex listed as a neighbor
				boolean found = false;
				for(int j = 0; j < v[i].length; j++) {
					//so check each index in the neighbor
					if(v[i][k] != null) {
						if(v[v[i][k].getV()][0] == null) { //if the neighbor has no indices... add the vertex
							v[v[i][k].getV()][0] = new Vertex(i,v[i][k].getD());
						}
						if(v[v[i][k].getV()][j] != null) {
							if(v[v[i][k].getV()][j].getV() == v[i][k].getV())
								found = true;
							if(found == false && v[v[i][k].getV()][j + 1] == null) {
								//find empty index inside and insert edge
								for(int l = 0; l < v[i].length; l++) {
									if(v[v[i][k].getV()][l] == null) {
										v[v[i][k].getV()][l] = new Vertex(i,v[i][k].getD());
										break;
									}
								}
								break;
							}	
						}
					}
				}
			}
		}
		return v;
	}

	public int getN(){return n;}

	public int getM(){return m;}

}
