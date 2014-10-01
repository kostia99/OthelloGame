package othelloGame1;

import java.util.Scanner;
import ui.Event;
import ui.OthelloUserInterface;
import ui.UserInterfaceFactory;


class OthelloReplay {
	
	Game game;
	
	OthelloUserInterface ui;
	

	OthelloReplay() {
		
		game = new Game();
		
		ui = UserInterfaceFactory.geefOthelloUI();
	}
	
	
	int amountOfSets(String data) {
		return Integer.parseInt(data);
	}
	
	
	void showBoard() {
		
		ui.maakLeeg();
		
		int amountOfPossibleSets = 0;
		
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				
				ui.plaats(x, y, game.getBoardValue(y, x));
				
				if (game.cellIsEmpty(new Coordinate(x, y)) && game.possibleSet(new Coordinate(x, y))) {
					
					ui.plaatsDoorzichtig(x, y, game.player1());	
					amountOfPossibleSets += 1;
				}
			}
		}
		
		if (amountOfPossibleSets == 0) {
			game.playerPasses(game.player1());
		}
		
		ui.toon();
	}
	
	
	void setEvent(Event event) {
		
		if (event.naam.equals("cijfer")) {
			
			processCijfer(event.data);
		}
		
		if (event.naam.equals("pijltje")) {
			if (event.data.equals("L")) {
				
				processPijltje();
			} 
		}
		
		if (event.naam.equals("klik")) {
		
			processClick(clicked(event.data));
		}
	}
	
	
	void processPijltje() {
		game.removeSets(1);
		showBoard();
	}
	
	
	void processCijfer(String data) {
		undoMoves(data);
		showBoard();
	}
	
	void undoMoves(String data) {
		int amountOfSets = amountOfSets(data);
		
		game.removeSets(amountOfSets);
	}
	
	
	
	void processClick(Coordinate click) {
		
		if (game.cellIsEmpty(click) && game.possibleSet(click)) {
			
			game.addNewMove(click);
			showBoard();
			
		} else {
			System.out.println("Wrong move");
		}
	}
	
	
	Coordinate clicked(String data) {
		Scanner dataScanner = new Scanner(data);
		Coordinate c = new Coordinate(dataScanner.next().charAt(0) - 'a', dataScanner.nextInt() - 1);
		
		return c;
	}
	
	
	void playGame() {
		
		while (true) {
			
			Event event = ui.geefEvent();
			setEvent(event);
		}
	}
	
	
	
	void start() {
		
		game.getWholeBoard().fillBoard();
		
		showBoard();
		
		playGame();
		
		System.out.println("Fatal error..");
		System.exit(1);
	}
	
	public static void main(String[] argv) {
		new OthelloReplay().start();
	}

}
