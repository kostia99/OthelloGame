package othelloGame1;

class Board {

	private static int HEIGHT = 8,
						 WIDTH = 8;
	
	private int[][] board;

	
	Board() {
		
		board = new int[HEIGHT][WIDTH];
	}
	
	
	enum Direction {
		
	    NORTH (0, -1),
	    SOUTH (0, +1),
	    WEST (+1, 0),
	    EAST (-1, 0),
	    SOUTH_EAST (-1, +1),
	    SOUTH_WEST (+1, +1),
	    NORTH_EAST (-1, -1),
	    NORTH_WEST (+1, -1);

	    final int x, y;

	    Direction(int x, int y) {
	        this.x = x;
	        this.y = y;
	    }
	}
	
	
	public void fillBoard() {
		for (int y = 0; y < board.length; y++) { 
			for (int x = 0; x < board[y].length; x++) { 
				board[y][x] = 0; 
			}	
		}
		
		board[3][3] = 1; // begin game;	
		board[4][4] = 1;
		board[3][4] = 2;
		board[4][3] = 2; 
	}
	
	
	void addSet(int step, TurnRow inputData) {
		
		if (inputData.getStep(step).getAction()) {
			
			processStone(inputData, step, inputData.getStep(step).getPlayer());
			turnOver(inputData, step, inputData.getStep(step).getPlayer());
		}
	}
	
	
	void removeSet(int step, TurnRow inputData) {
		
		if (inputData.getStep(step).getAction()) {
			
			processStone(inputData, step, 0);
			turnOver(inputData, step, inputData.getStep(step - 1).getPlayer());
		}
	}
	
	
	void processStone(TurnRow inputData, int step, int value) {
		int pX = inputData.getStep(step).getMove().getStone().getX();
		int pY = inputData.getStep(step).getMove().getStone().getY();
	
		board[pY][pX] = value;
	}
	
	void turnOver(TurnRow inputData, int step, int value) {
		for (int j = 0; j < inputData.getStep(step).getMove().getTurnOver().getAmountOfCoordinates(); j++) {
			int tX = inputData.getStep(step).getMove().getTurnOver().getFromCoordinatesRow(j).getX();
			int tY = inputData.getStep(step).getMove().getTurnOver().getFromCoordinatesRow(j).getY();
	
			board[tY][tX] = value;
		}
	}
	
	
	boolean nextOnBoard(int x, int y, int player) {
		return (x >= 0 && y >= 0 && x < 8 && y < 8 && board[y][x] == player);
	}

	
	public CoordinateRow getInclosed(Coordinate click, int player1, int player2) {
		CoordinateRow inclosed = new CoordinateRow();
		
		for (Direction d : Direction.values()) {
			
			inclosed = makeInclosed(inclosed, click, d, player1, player2);
		}
		
		return inclosed;
	}
	
	
	private CoordinateRow makeInclosed(CoordinateRow inclosed, Coordinate click, Direction d, int player1, int player2) {
		CoordinateRow temp = new CoordinateRow();
		Coordinate s = new Coordinate(click.getX() + d.x, click.getY() + d.y);	
		
		int sX = s.getX(), sY = s.getY();
		
		while (nextOnBoard(sX, sY, player2)) {				
			Coordinate in = new Coordinate(sX, sY);
			temp.addOneAfterRow(in);
			
			sX += d.x;
			sY += d.y;
		} 
			
		if (nextOnBoard(sX, sY, player1)) {
			inclosed.concatTwoCoordinateRows(temp);
		} 
		
		return inclosed;
	}
	
	
	
	public boolean possibleSet(Coordinate click, int player1, int player2) {
		return getInclosed(click, player1, player2).getAmountOfCoordinates() > 0;
	}


	public int getBoard(int y, int x) {
		return board[y][x];
	}
	
	public void setValue(int y, int x, int v) {
		this.board[y][x] = v;
	}
}
