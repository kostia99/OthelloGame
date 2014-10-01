package othelloGame1;

class Coordinate {

	private int x, y;

	Coordinate() {

		x = 0;
		y = 0;
	}

	Coordinate(int x, int y) {

		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}