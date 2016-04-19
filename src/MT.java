
public class MT {

	public static void main(String[] args) {
		TTT t = new TTT();
		
		boolean gameIsFinished = false;
		while(!gameIsFinished){
			t.printBoard();
			t.userGuess();
			gameIsFinished = t.checkWinner();
			if(!gameIsFinished)
				t.nextTurn();
		}
		
		System.out.println("Congratulations " + t.getCurrentPlayerName() + "!");
		System.out.println("You win!");
		System.exit(0);
	}
}
