import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Board {
	//integer matrix of distances
	//Represents the length of shortest path to each square
	private int[][] distances = new int[8][8];

	//matrix of adjacency lists
	//Stores an adjacency list for each square
	//An adjacency list is a linked list of size 2 integer arrays
	//Each integer array represents the file and rank of an adjacent square
	private List<List<List<int[]>>> adjacencies = new ArrayList<>(8);

	//Initialize and pre-compute adjacencies
	Board () {
		for(int i = 0; i < 8; i++) {
			adjacencies.add(new ArrayList<>(8));
		}

		for(int file = 1; file <= 8; file++) {
			for(int rank = 1; rank <= 8; rank++) {
				adjacencies.get(file - 1).add(getAdjacencies(file, rank));
			}
		}
	}

	//Set the distance of each square to 7
	private void clearDistances() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				distances[i][j] = 7;
			}
		}
	}

	//Compute the distance matrix according to te given square
	void createShortestPath(int file, int rank) {
		clearDistances();
		createShortestPath(file, rank, 0);
	}

	//Recursive helper method to compute the distance matrix
	private void createShortestPath(int file, int rank, int distance) {
		//Base case
		if(distance >= distances[file - 1][rank - 1]) {
			return;
		}

		//Recursive Case
		distances[file - 1][rank - 1] = distance;
		List<int[]> adjacencies = this.adjacencies.get(file - 1).get(rank - 1);
		for(int[] a: adjacencies) {
			//The distance to each square adjacent to the current one is at most
			//one more that the distance to the current square
			createShortestPath(a[0], a[1], distance + 1);
		}
	}

	//Used to pre-compute the adjacency list of a given square
	private List<int[]> getAdjacencies(int file, int rank) {
		List<int[]> output = new LinkedList<>();
		output.add(new int[] {file + 2, rank + 1});
		output.add(new int[] {file + 1, rank + 2});
		output.add(new int[] {file - 2, rank + 1});
		output.add(new int[] {file + 1, rank - 2});
		output.add(new int[] {file - 2, rank - 1});
		output.add(new int[] {file - 1, rank - 2});
		output.add(new int[] {file + 2, rank - 1});
		output.add(new int[] {file - 1, rank + 2});

		//Delete adjacencies that fall outside the board
		Iterator<int[]> it = output.iterator();
		while(it.hasNext()){
			int[] square = it.next();
			for(int position: square) {
				if(position < 1 || position > 8){
					it.remove();
					break;
				}
			}
		}

		return output;
	}

	//String representation of the board
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (int i = 7; i >= 0; i--) {
			s.append(i + 1);
			s.append(" ");
			for (int j = 0; j < 8; j++) {
				s.append(distances[j][i]);
				s.append(" ");
			}
			s.append("\n");
		}
		s.append("  a b c d e f g h");
		return s.toString();
	}
	
}
