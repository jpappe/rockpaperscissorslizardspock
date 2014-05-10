
package com.jpappe.rockpaperscissors.game.config;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Represents an individual multi-round competition between two players
 * 
 * @author jacob
 * 
 */
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( propOrder = { "players", "rounds" } )
public class Game {

	/**
	 * The list of players in the game. There really shouldn't be any more than 2
	 */
	@XmlElementWrapper( name = "Players" )
	@XmlElement( name = "player" )
	private List<PlayerConfiguration> players;

	/**
	 * The number of rounds in the game
	 */
	private int rounds;

	public List<PlayerConfiguration> getPlayers() {
		if ( players == null ) {
			players = new ArrayList<PlayerConfiguration>();
		}
		return players;
	}


	public int getRounds() {
		return rounds;
	}

	public void setRounds( int rounds ) {
		this.rounds = rounds;
	}

}
