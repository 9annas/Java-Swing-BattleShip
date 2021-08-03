package model.player;

import model.IBoard;
import model.Tile;
import util.PlayerState;
import util.Position;
import util.TileState;

import java.util.ArrayList;

public class AI extends Player {
	ArrayList<Tile> possibleTile = new ArrayList<>();

	public AI(IBoard attackBoard, IBoard defenseBoard) {
		super(attackBoard, defenseBoard, "AI");
		state = PlayerState.MY_TURN;

		possibleTile.addAll(attackBoard.getTiles());
	}

	@Override
	public void changeState(PlayerState state) {
		super.changeState(state);

		if (state == PlayerState.MY_TURN) {
			tryShootAt(choosePosition());
		}
	}

	@Override
	public void tryShootAt(Position position) {
		if (state == PlayerState.MY_TURN && attackBoard.getTileStateAt(position) == TileState.UNKNOWN) {
			new Thread(() -> {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				attackBoard.changeTileStateAt(position);
				controller.shootPlayer1At(position);
			}).start();
		}
	}

	private Position choosePosition() {
		int rand = (int) (Math.random() * possibleTile.size());
		Tile tile = possibleTile.get(rand);
		possibleTile.remove(rand);
		return tile.getPosition();
	}

	@Override
	protected String getMissOutput(Position position) {
		return "Player 1 : Missed at " + position.toStringLetterFormat();
	}

	@Override
	protected String getHitOutput(Position position) {
		return "Player 1 : Hit at " + position.toStringLetterFormat();
	}

	@Override
	protected String getLostOutput() {
		return "You win";
	}
}