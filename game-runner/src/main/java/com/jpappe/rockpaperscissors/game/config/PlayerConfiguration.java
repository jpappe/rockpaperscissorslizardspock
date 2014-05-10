package com.jpappe.rockpaperscissors.game.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
public class PlayerConfiguration {

	@XmlAttribute( name = "name" )
	private String playerName;

	@XmlAttribute( name = "class" )
	private String className;

	public String getClassName() {
		return className;
	}

	public void setClassName( String className ) {
		this.className = className;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName( String playerName ) {
		this.playerName = playerName;
	}

}
