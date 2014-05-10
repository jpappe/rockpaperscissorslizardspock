package com.jpappe.rockpaperscissors.game.config;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * Represents the total configuration for the game runner. This will contain multiple "games", each
 * of which is a multi-round competition between two players.
 * 
 * @author jacob
 * 
 */
@XmlRootElement( name = "GameConfiguration" )
@XmlAccessorType( XmlAccessType.FIELD )
public class GameConfiguration {

	@XmlElementWrapper( name = "Games" )
	@XmlElement( name = "Game" )
	private List<Game> games;

	/**
	 * getter for Games list that initializes the list if it does not already exist
	 * 
	 * @return list of Game objects
	 */
	public List<Game> getGames() {
		if ( games == null ) {
			games = new ArrayList<Game>();
		}
		return games;
	}

}
