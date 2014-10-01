package othelloGame1;

class Set {
	
	private Coordinate stone;
	
	private CoordinateRow turnOver;
	
	Set() {
		
		stone = new Coordinate();
		
		turnOver = new CoordinateRow();
	}
	
	Set(Coordinate stone, CoordinateRow turnOver) {
		
		this.stone = stone;
		this.turnOver = turnOver;
	}


	public CoordinateRow getTurnOver() {
		return turnOver;
	}

	public Coordinate getStone() {
		return stone;
	}
}