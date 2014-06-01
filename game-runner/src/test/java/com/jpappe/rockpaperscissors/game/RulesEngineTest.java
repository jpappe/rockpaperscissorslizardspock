package com.jpappe.rockpaperscissors.game;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;

import com.jpappe.rockpaperscissors.framework.Hand;
import com.jpappe.rockpaperscissors.framework.Player;
import com.jpappe.rockpaperscissors.framework.RoundOutcome;

public class RulesEngineTest {

	private RulesEngine engine;
	private Map<String, Player> players;
	private Map<String, Hand> hands;
	private RoundOutcome expected;

	@Before
	public void setup() {
		engine = new RulesEngine();
		players = new HashMap<String, Player>();
		players.put( "alex", mock( Player.class ) );
		players.put( "bob", mock( Player.class ) );

		hands = new HashMap<String, Hand>();

		expected = new RoundOutcome();
		expected.setPlayers( players );
		expected.setHandsPlayed( hands );

		engine.setPlayers( players );
	}

	@Test
	public void testDetermineOutcome() {
		hands.put( "alex", Hand.ROCK );
		hands.put( "bob", Hand.LIZARD );

		RoundOutcome found = engine.determineOutcome( hands );
		expected.setWinner( "alex" );
		expected.setLoser( "bob" );
		expected.setTie( false );
		expected.setSentence( "ROCK squashes LIZARD" );
		
		assertThat( found, outcomeMatches( expected ) );
	}

	@Test
	public void testSecondPlayerWins() {
		hands.put( "alex", Hand.SPOCK );
		hands.put( "bob", Hand.PAPER );

		RoundOutcome found = engine.determineOutcome( hands );
		expected.setWinner( "bob" );
		expected.setLoser( "alex" );
		expected.setTie( false );
		expected.setSentence( "PAPER disproves SPOCK" );

		assertThat( found, outcomeMatches( expected ) );

	}

	@Test
	public void testTie() {
		hands.put( "alex", Hand.SPOCK );
		hands.put( "bob", Hand.SPOCK );

		RoundOutcome found = engine.determineOutcome( hands );
		expected.setTie( true );
		expected.setSentence( "It was a tie!" );

		assertThat( found, outcomeMatches( expected ) );
	}

	/**
	 * a custom matcher
	 * 
	 * @param expected
	 * @param found
	 */
	private static Matcher<RoundOutcome> outcomeMatches( final RoundOutcome expectedOutcome ) {
		return new TypeSafeMatcher<RoundOutcome>() {

			protected RoundOutcome expected = expectedOutcome;

			private List<String> descriptions;

			private List<String> compare( RoundOutcome foundOutcome ) {
				if ( descriptions == null ) {
					descriptions = new ArrayList<String>();

					if ( !StringUtils.equals( expected.getWinner(), foundOutcome.getWinner() ) ) {
						descriptions.add( String.format( "Winner was different; expected %s, found %s", expected.getWinner(), foundOutcome.getWinner() ) );
					}
					if ( !StringUtils.equals( expected.getLoser(), foundOutcome.getLoser() ) ) {
						descriptions.add( String.format( "Loser was different; expected %s, found %s", expected.getLoser(), foundOutcome.getLoser() ) );
					}
					if ( expected.isTie() != foundOutcome.isTie() ) {
						descriptions.add( String.format( "Tie was different; expected %s, found %s", Boolean.toString( expected.isTie() ), Boolean.toString( foundOutcome.isTie() ) ) );
					}
					if ( !expected.getSentence().equals( foundOutcome.getSentence() ) ) {
						descriptions.add( String.format( "Sentence was different; expected '%s', found '%s'", expected.getSentence(), foundOutcome.getSentence() ) );
					}
				}
				return descriptions;
			}

			@Override
			protected boolean matchesSafely( RoundOutcome foundOutcome ) {
				return compare( foundOutcome ).size() == 0;
			}


			@Override
			public void describeTo( Description description ) {
				description.appendText( "RoundOutcomes to be equal" );
			}

			@Override
			protected void describeMismatchSafely( RoundOutcome item, Description mismatchDescription ) {
				for ( String s : compare( item ) ) {
					mismatchDescription.appendText( s );
				}
			}

		};
	}

}
