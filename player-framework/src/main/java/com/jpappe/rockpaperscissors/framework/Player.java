package com.jpappe.rockpaperscissors.framework;

/**
 * Defines the ways that the game interacts with players
 * 
 * @author jacob
 * 
 */
public interface Player {

	Hand getHand();

	void reportOutcome( RoundOutcome outcome );
}
