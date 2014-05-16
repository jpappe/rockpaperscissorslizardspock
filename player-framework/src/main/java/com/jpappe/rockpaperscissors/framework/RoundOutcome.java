package com.jpappe.rockpaperscissors.framework;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents the outcome of an individual round.
 * 
 * @author jacob
 * 
 */
public class RoundOutcome {

	// the hands played by each user
	private Map<String, Hand> handsPlayed;
	// was the round a tie?
	private boolean tie;
	// which player was the winner?
	private String winner;

	public Map<String, Hand> getHandsPlayed() {
		return handsPlayed;
	}

	public void setHandsPlayed( Map<String, Hand> handsPlayed ) {
		this.handsPlayed = handsPlayed;
	}

	public boolean isTie() {
		return tie;
	}

	public void setTie( boolean isTie ) {
		this.tie = isTie;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner( String winner ) {
		this.winner = winner;
	}

	/**
	 * Print a summary of the outcome of a single round
	 * 
	 * @return string
	 */
	@Override
	public String toString() {
		List<String> p = new ArrayList<String>();
		for ( String name : handsPlayed.keySet() ) {
			p.add( String.format( "%s played %s", name, handsPlayed.get( name ).name() ) );
		}

		return String.format( "%s, %s; Result: %s", p.get( 0 ), p.get( 1 ), (tie ? "TIE" : String.format( "%s wins", winner )) );
	}

}
