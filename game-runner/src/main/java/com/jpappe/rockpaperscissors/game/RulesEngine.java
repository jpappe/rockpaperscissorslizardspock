package com.jpappe.rockpaperscissors.game;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jpappe.rockpaperscissors.framework.Hand;
import com.jpappe.rockpaperscissors.framework.Player;
import com.jpappe.rockpaperscissors.framework.RoundOutcome;

/**
 * Responsible for determing winner of an individual round
 * 
 * @author jacob
 * 
 */
public class RulesEngine {

	/**
	 * The players involved in the current game
	 */
	private Map<String, Player> players;

	/**
	 * Represents which hands defeat each other
	 */
	private static final Map<Hand, List<Hand>> rules;

	/**
	 * A lookup for choosing the correct verb to describe what has occurred in the round (e.g.,
	 * "paper wraps rock")
	 */
	private static final Map<Hand, Map<Hand, String>> verbs;
	static {
		rules = new HashMap<Hand, List<Hand>>();

		rules.put( Hand.ROCK, Arrays.asList( Hand.SCISSORS, Hand.LIZARD ) );
		rules.put( Hand.PAPER, Arrays.asList( Hand.ROCK, Hand.SPOCK ) );
		rules.put( Hand.SCISSORS, Arrays.asList( Hand.PAPER, Hand.LIZARD ) );
		rules.put( Hand.LIZARD, Arrays.asList( Hand.SPOCK, Hand.PAPER ) );
		rules.put( Hand.SPOCK, Arrays.asList( Hand.SCISSORS, Hand.ROCK ) );

		verbs = new HashMap<Hand, Map<Hand, String>>();
		verbs.put( Hand.ROCK, new HashMap<Hand, String>() );
		verbs.get( Hand.ROCK ).put( Hand.SCISSORS, "crushes" );
		verbs.get( Hand.ROCK ).put( Hand.LIZARD, "squashes" );

		verbs.put( Hand.PAPER, new HashMap<Hand, String>() );
		verbs.get( Hand.PAPER ).put( Hand.ROCK, "covers" );
		verbs.get( Hand.PAPER ).put( Hand.SPOCK, "disproves" );

		verbs.put( Hand.SCISSORS, new HashMap<Hand, String>() );
		verbs.get( Hand.SCISSORS ).put( Hand.PAPER, "cut" );
		verbs.get( Hand.SCISSORS ).put( Hand.LIZARD, "decapitate" );

		verbs.put( Hand.LIZARD, new HashMap<Hand, String>() );
		verbs.get( Hand.LIZARD ).put( Hand.SPOCK, "poisons" );
		verbs.get( Hand.LIZARD ).put( Hand.PAPER, "eats" );

		verbs.put( Hand.SPOCK, new HashMap<Hand, String>() );
		verbs.get( Hand.SPOCK ).put( Hand.SCISSORS, "smashes" );
		verbs.get( Hand.SPOCK ).put( Hand.ROCK, "vaporizes" );

	}

	/**
	 * set the players for the current game.
	 * 
	 * @param players
	 */
	public void setPlayers( Map<String, Player> players ) {
		this.players = players;
	}

	/**
	 * Based on the hands played by each player, figure out who won.
	 * 
	 * @param hands
	 * @return RoundOutcome summarizing the outcome of the round
	 */
	public RoundOutcome determineOutcome( Map<String, Hand> hands ) {
		RoundOutcome outcome = new RoundOutcome();
		outcome.setPlayers( players );
		outcome.setHandsPlayed( hands );
		// let's assume it's not a tie until we know it is
		outcome.setTie( false );

		String[] playerNames = hands.keySet().toArray( new String[]{} );
		if ( rules.get( hands.get( playerNames[0] ) ).contains( hands.get( playerNames[1] ) ) ) {
			outcome.setWinner( playerNames[0] );
			outcome.setLoser( playerNames[1] );
		}
		else if ( rules.get( hands.get( playerNames[1] ) ).contains( hands.get( playerNames[0] ) ) ) {
			outcome.setWinner( playerNames[1] );
			outcome.setLoser( playerNames[0] );
		}
		else {
			outcome.setTie( true );
		}

		outcome.setSentence( buildSentence( outcome, hands ) );
		return outcome;
	}

	/**
	 * Construct the sentence describing the hands played (e.g., "paper covers rock") based on the
	 * summary provided
	 * 
	 * @param outcome
	 * @param hands
	 * @return String
	 */
	private static String buildSentence( RoundOutcome outcome, Map<String, Hand> hands ) {
		if ( outcome.isTie() ) {
			return "It was a tie!";
		}

		Hand winning = outcome.getHandsPlayed().get( outcome.getWinner() );
		Hand losing = outcome.getHandsPlayed().get( outcome.getLoser() );

		return String.format( "%s %s %s", winning.name(), verbs.get( winning ).get( losing ), losing.name() );
	}

}
