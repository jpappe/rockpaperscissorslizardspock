package com.jpappe.rockpaperscissors.framework;

import java.util.Map;

/**
 * Represents the outcome of an individual round.
 * 
 * @author jacob
 * 
 */
public class RoundOutcome {

	// the hands played by each user
	private Map<Player, Hand> handsPlayed;
	// was the round a tie?
	private boolean tie;
	// which player was the winner?
	private Player winner;

	public Map<Player, Hand> getHandsPlayed() {
		return handsPlayed;
	}

	public void setHandsPlayed( Map<Player, Hand> handsPlayed ) {
		this.handsPlayed = handsPlayed;
	}

	public boolean isTie() {
		return tie;
	}

	public void setTie( boolean isTie ) {
		this.tie = isTie;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner( Player winner ) {
		this.winner = winner;
	}

}
