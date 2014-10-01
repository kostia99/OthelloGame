package othelloGame1;

class TurnRow {
	
	private static final int MAX_AMOUNT_TURNS = 1000;
	
	private Turn[] allSteps;
	
	private int amountOfTurns;
	
	TurnRow() {
		
		allSteps = new Turn[MAX_AMOUNT_TURNS];
		
		amountOfTurns = 0;
	}
	
	
	void addOneTurnToRow(Turn currentTurn) {
		allSteps[amountOfTurns] = currentTurn;
		amountOfTurns += 1;
	}
	
	
	void removeLastFromRow() {
		amountOfTurns -= 1;
	}


	public int getAmountOfTurns() {
		return amountOfTurns;
	}

	public Turn getStep(int i) {
		return allSteps[i];
	}
}
