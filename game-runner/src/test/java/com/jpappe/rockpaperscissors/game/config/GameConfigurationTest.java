package com.jpappe.rockpaperscissors.game.config;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

import javax.xml.bind.JAXB;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Unit tests for GameConfiguration objects that verify our annotations work the way we expect
 * 
 * @author jacob
 * 
 */
public class GameConfigurationTest extends TestCase {

	private static final String XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
	    "<GameConfiguration>\n" +
	    "    <Games>\n" +
	    "        <Game>\n" +
	    "            <Players>\n" +
	    "                <player name=\"bob\" class=\"java.lang.String\"/>\n" +
	    "                <player name=\"fred\" class=\"com.random.class\"/>\n" +
	    "            </Players>\n" +
	    "            <rounds>10</rounds>\n" +
	    "        </Game>\n" +
	    "        <Game>\n" +
	    "            <Players>\n" +
	    "                <player name=\"anne\" class=\"org.junit.Test\"/>\n" +
	    "                <player name=\"charles\" class=\"org.junit.Assert\"/>\n" +
	    "            </Players>\n" +
	    "            <rounds>20</rounds>\n" +
	    "        </Game>\n" +
	    "    </Games>\n" +
	    "</GameConfiguration>\n";

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMarshal() throws Exception {
		GameConfiguration gc = new GameConfiguration();

		// first game
		Game g = new Game();
		PlayerConfiguration pc = new PlayerConfiguration();
		pc.setPlayerName( "bob" );
		pc.setClassName( "java.lang.String" );
		g.getPlayers().add( pc );
		pc = new PlayerConfiguration();
		pc.setPlayerName( "fred" );
		pc.setClassName( "com.random.class" );
		g.getPlayers().add( pc );
		g.setRounds( 10 );

		gc.getGames().add( g );

		// second game
		g = new Game();
		pc = new PlayerConfiguration();
		pc.setPlayerName( "anne" );
		pc.setClassName( "org.junit.Test" );
		g.getPlayers().add( pc );
		pc = new PlayerConfiguration();
		pc.setPlayerName( "charles" );
		pc.setClassName( "org.junit.Assert" );
		g.getPlayers().add( pc );
		g.setRounds( 20 );

		gc.getGames().add( g );

		// now marshal it to XML
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		JAXB.marshal( gc, out );

		String xml = new String( out.toByteArray(), Charset.forName( "UTF8" ) );
		assertEquals( "Wrong XML generated", XML, xml );
	}
}
