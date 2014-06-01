package com.jpappe.rockpaperscissors.framework;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * Represents the outcome of an individual round.
 * 
 * @author jacob
 * 
 */
public class RoundOutcome {

	// who were the players?
	private Map<String, Player> players;
	// the hands played by each user
	private Map<String, Hand> handsPlayed;
	// was the round a tie?
	private boolean tie;
	// which player was the winner?
	private String winner;
	// may as well store the loser, too
	private String loser;
	// the sentence summary of the action (e.g., "rock crushes scissors")
	private String sentence;

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
		p.add( this.getSentence() );
		p.add( String.format( "Result: %s", (isTie() ? "TIE" : String.format( "%s wins!", getWinner() )) ) );

		return StringUtils.join( p, "\n" );
	}

	public Map<String, Player> getPlayers() {
		return players;
	}

	public void setPlayers( Map<String, Player> players ) {
		this.players = players;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence( String sentence ) {
		this.sentence = sentence;
	}

	public String getLoser() {
		return loser;
	}

	public void setLoser( String loser ) {
		this.loser = loser;
	}

}
