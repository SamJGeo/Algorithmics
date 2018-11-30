import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {

		long start = System.currentTimeMillis();

		String inputFileName = args[0]; // input file name

		FileReader reader = new FileReader(inputFileName);
		Scanner in = new Scanner(reader);
		String line = in.nextLine();
		Scanner lineScanner = new Scanner(line);
		// read in the data here and create graph here
		int numVertices = lineScanner.nextInt();

		Graph g = new Graph(numVertices);
		int index = 0;
		int lineCounter = 0;
		String points[] = new String[2];

		// loop over each line
		while (in.hasNextLine()) {
			// read the next line in from the file
			line = in.nextLine();
			//check if on the final line
			if (lineCounter < numVertices) {
				int neighbourVertex = 0;
				// loop over the string looking for the distances, and add these to the
				// vertex's neighbours (adjaceny list)
				String lineElts[] = line.split(" ");
				for (int i = 0; i < lineElts.length; i++) {
					//
					int dist = Integer.parseInt(lineElts[i]);
					if (dist != 0) {
						g.getVertex(index).addToAdjList(neighbourVertex,dist);
					}
					neighbourVertex++;
				}
				index++;
			} 
			else {
				points = line.split(" ");
				// the last line of the file we are interested in, so just break out the loop
				break;
			}
			lineCounter++;
		}
		
		Vertex source = g.getVertex(Integer.parseInt(points[0]));
		Vertex destination = g.getVertex(Integer.parseInt(points[1]));



		reader.close();
		in.close();
		lineScanner.close();
		// do the work here
		g.setupBT(source);
		g.backtrack(0,destination);
		if(g.getBD() == Integer.MAX_VALUE){
			System.out.format("The is no path between vertices %d and %d",source.getIndex(),destination.getIndex());
		} else{
			System.out.format("The shortest path between %d and %d is %d \n",source.getIndex(),destination.getIndex(),g.getBD());
			System.out.print("Shortest path: ");
			g.printOut(destination);
		}

		// end timer and print total time
		long end = System.currentTimeMillis();
		System.out.println("\nElapsed time: " + (end - start) + " milliseconds");
	}

}

