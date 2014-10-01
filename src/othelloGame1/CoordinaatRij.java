package othelloGame1;


class CoordinateRow {

	private static final int MAX_AMOUNT_COORDINATES = 1000; //

	private Coordinate[] coordinatesRow;

	private int amountOfCoordinates;

	CoordinateRow() {

		coordinatesRow = new Coordinate[MAX_AMOUNT_COORDINATES];

		amountOfCoordinates = 0;
	}
	

	void addOneAfterRow(Coordinate currentCoordinate) {
		coordinatesRow[amountOfCoordinates] = currentCoordinate;
		amountOfCoordinates += 1;
	}
	

	void removeOneFromRow(Coordinate currentCoordinate) {
		for (int i = 0; i < getAmountOfCoordinates(); i++) {
			if (currentCoordinate == coordinatesRow[i]) {
				coordinatesRow[i] = coordinatesRow[amountOfCoordinates];
				amountOfCoordinates -= 1;
			}
		}
	}
	
	
	void concatTwoCoordinateRows(CoordinateRow temp) {
		for (int i = 0; i < temp.amountOfCoordinates; i++) {
			addOneAfterRow(temp.coordinatesRow[i]);
		}
	}


	public Coordinate getFromCoordinatesRow(int i) {
		return coordinatesRow[i];
	}

	public int getAmountOfCoordinates() {
		return amountOfCoordinates;
	}
}