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
	 * Represents which hands defeat each other
	 */
	private static final Map<Hand, List<Hand>> rules;
	static {
		rules = new HashMap<Hand, List<Hand>>();

		rules.put( Hand.ROCK, Arrays.asList( Hand.SCISSORS, Hand.LIZARD ) );
		rules.put( Hand.PAPER, Arrays.asList( Hand.ROCK, Hand.SPOCK ) );
		rules.put( Hand.SCISSORS, Arrays.asList( Hand.PAPER, Hand.LIZARD ) );
		rules.put( Hand.LIZARD, Arrays.asList( Hand.SPOCK, Hand.PAPER ) );
		rules.put( Hand.SPOCK, Arrays.asList( Hand.SCISSORS, Hand.ROCK ) );

	}

	public RoundOutcome determineOutcome( Map<String, Player> players, Map<String, Hand> hands ) {
		// TODO Auto-generated method stub
		return null;
	}

}
