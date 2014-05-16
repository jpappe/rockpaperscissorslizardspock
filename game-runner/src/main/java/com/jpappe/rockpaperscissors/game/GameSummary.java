package com.jpappe.rockpaperscissors.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.jpappe.rockpaperscissors.framework.Player;
import com.jpappe.rockpaperscissors.framework.RoundOutcome;

/**
 * A compilation of all information generated during a game. This contains all information from the
 * individual rounds as well as the compiled score.
 * 
 * @author jacob
 * 
 */
public class GameSummary {

	private List<RoundOutcome> roundOutcomes;

	// use AtomicInteger to hold the score since Integer is immutable and can't be incremented
	private Map<String, AtomicInteger> score;
	private Map<String, Player> players;
	private int ties;

	public GameSummary( Map<String, Player> players ) {
		score = new HashMap<String, AtomicInteger>();

		for ( String s : players.keySet() ) {
			score.put( s, new AtomicInteger( 0 ) );
		}

		ties = 0;
	}

	/**
	 * Add the given RoundOutcome to the game summary. This operation also updates the compiled score.
	 * 
	 * @param outcome
	 */
	public void addRoundOutcome( RoundOutcome outcome ) {
		if ( outcome.isTie() ) {
			ties++;
		}
		else {
			score.get( outcome.getWinner() ).incrementAndGet();
		}
		roundOutcomes.add( outcome );
	}

	public List<RoundOutcome> getRoundOutcomes() {
		return roundOutcomes;
	}

	public void setRoundOutcomes( List<RoundOutcome> roundOutcomes ) {
		this.roundOutcomes = roundOutcomes;
	}

	public Map<String, AtomicInteger> getScore() {
		return score;
	}

	public void setScore( Map<String, AtomicInteger> score ) {
		this.score = score;
	}

	public int getTies() {
		return ties;
	}

	public void setTies( int ties ) {
		this.ties = ties;
	}

}
