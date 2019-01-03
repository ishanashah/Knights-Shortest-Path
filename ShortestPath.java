import java.util.Scanner;

public class ShortestPath {
	public static void main(String[] args) {
		Board chessboard = new Board();

		Scanner console = new Scanner(System.in);
		while (true) {
			System.out.println("What is your square?");
			System.out.println("Type \"exit\" to end the program.");
			String location = console.next();

			//Exit the program
			if(location.toLowerCase().equals("exit")) {
				System.exit(0);
			}

			//Check for invalid input
			if(location.length() != 2 ||
					Character.toLowerCase(location.charAt(0)) < 'a' ||
					Character.toLowerCase(location.charAt(0)) > 'h' ||
					Character.toLowerCase(location.charAt(1)) < '1' ||
					Character.toLowerCase(location.charAt(1)) > '8') {
				System.out.println("Invalid Input, Try Again:");
				System.out.println();
				continue;
			}

			//Calculate shortest paths and print board
			chessboard.createShortestPath(getFile(location), getRank(location));
			System.out.println(chessboard);
			System.out.println();
		}
	}

	//Get numerical file from chess-notation
	private static int getFile(String input) {
		char c = Character.toLowerCase(input.charAt(0));
		return c - 'a' + 1;
	}

	//Get numerical rank from chess-notation
	private static int getRank(String input) {
		char c = input.charAt(1);
		return c - '1' + 1;
	}
}
