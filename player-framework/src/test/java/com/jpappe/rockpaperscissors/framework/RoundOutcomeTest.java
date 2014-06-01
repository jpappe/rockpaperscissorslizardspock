package com.jpappe.rockpaperscissors.framework;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Test;

public class RoundOutcomeTest extends TestCase {

	@Test
	public void testPrintWinner() {
		RoundOutcome r = new RoundOutcome();

		Map<String, Hand> played = new HashMap<String, Hand>();
		played.put( "andy", Hand.ROCK );
		played.put( "bob", Hand.PAPER );

		r.setTie( false );
		r.setHandsPlayed( played );
		r.setWinner( "bob" );
		r.setSentence( "PAPER covers ROCK" );

		assertEquals( "bob played PAPER\nandy played ROCK\nPAPER covers ROCK\nResult: bob wins!", r.toString() );
	}

	@Test
	public void testTied() {
		RoundOutcome r = new RoundOutcome();

		Map<String, Hand> played = new HashMap<String, Hand>();
		played.put( "charles", Hand.LIZARD );
		played.put( "dan", Hand.LIZARD );

		r.setHandsPlayed( played );
		r.setTie( true );
		r.setSentence( "It was a tie!" );
		// we'll set a winner even though that wouldn't happen in real life; we 
		// want to prove it gets ignored
		r.setWinner( "charles" );

		assertEquals( "dan played LIZARD\ncharles played LIZARD\nIt was a tie!\nResult: TIE", r.toString() );

	}

}
