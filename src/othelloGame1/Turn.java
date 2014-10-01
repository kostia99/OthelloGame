package othelloGame1;

class Turn {

	private boolean action;
	private int waittime, player;
	private Set move;
	
	Turn() {
		
		action = true;
		player = 0;
		waittime = 0;
		move = new Set();
	}
	
	
	Turn (int player, int waittime, boolean action, Set move) {
		
		this.player = player;
		this.waittime = waittime;
		this.action = action;
		this.move = move;
	}
	
	Turn (int player, int waittime, boolean action) {
		this.player = player;
		this.waittime = waittime;
		this.action = action;
	}

	public int getPlayer() {
		return player;
	}
	
	public boolean getAction() {
		return action;
	}
	
	public Set getMove() {
		return move;
	}
}
