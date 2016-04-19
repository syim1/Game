import java.util.Random;
import java.util.Scanner;

public class TTT {

	private String[][] array = new String[3][3];
	private String p1, p2;
	private int turnNumber;
	private int firstPlayer;
	
	public TTT(){
		initialize();
		setUpGame();
		turnNumber = 1;
	}
	public void initialize(){
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				array[i][j] = "-";
			}
		}
	}
	
	public int setUpGame(){
		int num1, num2;
		String stringNum1, stringNum2;
		Scanner kb = new Scanner(System.in);
		System.out.println("Please enter name of Player 1");
		p1 = kb.nextLine();
		System.out.println("Please enter name of Player 2");
		p2 = kb.nextLine();

		do {
			System.out.println("Player 1 please enter a number 1 - 10");
			stringNum1 = kb.nextLine();
			num1 = Integer.parseInt(stringNum1);
		} while (num1 < 1 || num1 > 10);

		do {
			System.out.println("Player 2 please enter a number 1 - 10");
			stringNum2 = kb.nextLine();
			num2 = Integer.parseInt(stringNum2);
		} while (num2 < 1 || num2 > 10 || num1 == num2);
		
		Random rand = new Random();
		int num = rand.nextInt(10) + 1;
		int p1Guess = Math.abs(num1 - num);
		int p2Guess = Math.abs(num2 - num);
		firstPlayer = (p1Guess < p2Guess) ? 1 : 2;
		return firstPlayer;
		// Finishes line started by last nextInt()
		//kb.nextLine();
	}
	
	public String getCurrentPlayerName(){
		String currentPlayerName;
		if(firstPlayer == 1 && turnNumber % 2 != 0)
			currentPlayerName = p1;
		else if(firstPlayer == 2 && turnNumber % 2 != 0)
			currentPlayerName = p2;
		else if(firstPlayer == 1 && turnNumber % 2 == 0)
			currentPlayerName = p2;
		else
			currentPlayerName = p1;
		
		return currentPlayerName;
	}

	public void userGuess(){
		int x, y;
		int count = 0;
		System.out.println(getCurrentPlayerName() + ", please pick a spot. (Ex. 1,1)");
		do{
		Scanner kb = new Scanner(System.in);
		if(count > 0){
			System.out.println("That spot has been taken.");
		}
		String input = kb.nextLine();
		String[] parts = input.split(",");
		x = Integer.parseInt(parts[0]);
		y = Integer.parseInt(parts[1]);
		count++;
		} while(array[x][y] != "-");
		
		if(turnNumber % 2 != 0){
			array[x][y] = "X";
		}
		else{
			array[x][y] = "O";
		}
		printBoard();
	}
	
	public void nextTurn() {
		turnNumber++;
	}
	
	public void printBoard() {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				System.out.print(array[i][j]);
				if (j < array.length - 1)
					System.out.print(" | ");
			}
			System.out.println();
			if (i < array.length - 1)
				System.out.println("----------");
		}
		System.out.println();
	}
	
	public boolean checkWinner(){
		if(checkRows() || checkColumns() || checkDiagonals()){
			return true;
		}
		else
			return false;
	}
	
	public void gameFinished(){
		
	}
	
	public boolean checkRows(){
		// X O X
		// X X X
		// X O X
		for(int row=0; row<array.length; row++){
			String first = array[row][0];
			boolean threeInRow = true;
			if(!first.equals("-")){
				for(int col=1; col<array[row].length; col++){
					threeInRow = threeInRow && first.equals(array[row][col]);
				}
			}
			else
				threeInRow = false;
			if(threeInRow)
				return true;
		}
		return false;
	}
	
	public boolean checkColumns(){
		// X O X
		// X X X
		// X O X
		for(int col=0; col<array[0].length; col++){
			String first = array[0][col];
			boolean threeInRow = true;
			if(!first.equals("-")){
				for(int row=1; row<array.length; row++){
					threeInRow = threeInRow && first == array[row][col];		
				}
			}
			else
				threeInRow = false;
			if(threeInRow)
				return true;
		}
		return false;
	}
	
	public boolean checkDiagonals(){
		boolean threeInRow = true;
		String first = array[0][0];
		if(!first.equals("-")){
			for(int i=0; i<array[0].length; i++){
				threeInRow = threeInRow && first == array[i][i];
			}
		}
		else
			threeInRow = false;
		if(threeInRow)
			return true;
		
		threeInRow = true;
		first = array[array.length-1][0];
		if(!first.equals("-")){
			for(int x=array.length-1, y=0 ; x<array[0].length;y++, x--){
				threeInRow = threeInRow && first == array[x][y];
			}
		}
		else
			threeInRow = false;
		if(threeInRow)
			return true;
		else
			return false;
	}
	
}
	

	
	
