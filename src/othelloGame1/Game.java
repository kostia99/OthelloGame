package othelloGame1;

class Game {
	
	private TurnRow data;
	
	private Board board;
	
	private int step, player1, player2;
	
	
	Game () {
		
		data = new TurnRow();
		
		board = new Board();
		
		step = 0;
		
		player1 = 2;
		player2 = 1;
	}
	
	
	boolean cellIsEmpty(Coordinate click) {
		return board.getBoard(click.getY(), click.getX()) == 0;
	}
	
	
	boolean possibleSet(Coordinate click) {
		return board.possibleSet(click, player1, player2);
	}

	
	
	Turn newTurn(Coordinate click) {
		Set nextSet = new Set(click, board.getInclosed(click, player1, player2));
		Turn nextTurn = new Turn(player1, 0, true, nextSet);
		
		return nextTurn;
	}
	
	
	void playerPasses(int player1) {
		Turn passed = new Turn(player1, 0, false);
		data.addOneTurnToRow(passed);
		step += 1;
		switchPlayers();
	}
	
	
	void addNewMove(Coordinate click) {
		data.addOneTurnToRow(newTurn(click));
		board.addSet(step, data);
		step += 1;
		switchPlayers();
	}
	
	
	void removeSets(int amountOfSets) {
		
		for (int i = 0; i < amountOfSets; i++) {
			if (step > 1) {
				
				step -= 1;
				switchPlayers();
				
				board.removeSet(step, data);
				data.removeLastFromRow();
				
			} else if (step == 1) {
				
				player1 = 2;
				player2 = 1;
				step = 0;
				
				board.fillBoard();
				data.removeLastFromRow();
			}
		}
	}
	
	
	void switchPlayers() {
		player1 = player2;
		player2 = data.getStep(step - 1).getPlayer();
	}
	
	
	public int player1() {
		return player1;
	}
	
	public int player2() {
		return player2;
	}
	
	public int getBoardValue(int y, int x) {
		return board.getBoard(y, x);
	}
	
	public Board getWholeBoard() {
		return board;
	}
}
