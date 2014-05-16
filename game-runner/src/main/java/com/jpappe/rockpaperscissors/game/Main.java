
package com.jpappe.rockpaperscissors.game;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXB;

import org.apache.commons.cli.HelpFormatter;

import com.jpappe.rockpaperscissors.framework.Hand;
import com.jpappe.rockpaperscissors.framework.Player;
import com.jpappe.rockpaperscissors.framework.RoundOutcome;
import com.jpappe.rockpaperscissors.game.config.Game;
import com.jpappe.rockpaperscissors.game.config.GameConfiguration;
import com.jpappe.rockpaperscissors.game.config.PlayerConfiguration;
import com.jpappe.rockpaperscissors.game.config.PlayerFactory;

/**
 * Main executable class for the application. This generally goes through the following process:
 * <ol>
 * <li>Read config file to determine game configuration</li>
 * <li>For each game defined in the config file:
 * <ol>
 * <li>Instantiate players</li>
 * <li>Run the specified number of rounds</li>
 * <li>Collect statistics</li>
 * </ol>
 * <li>
 * <li>Report the outcome of all games</li>
 * </ol>
 * 
 * @author jacob
 * 
 */
public class Main {

	private GameConfiguration gameConfiguration;
	private RulesEngine gameEngine;
	private PlayerFactory playerFactory;

	/**
	 * run all the games defined in the GameConfiguration assigned to this object.
	 * 
	 * @return list of GameSummary objects
	 */
	public List<GameSummary> runGames() {
		Map<String, Player> players = null;
		Map<String, Hand> hands = null;
		List<GameSummary> summaries = new ArrayList<GameSummary>();

		for ( Game g : gameConfiguration.getGames() ) {

			// here are our players for this game
			players = new HashMap<String, Player>();
			for ( PlayerConfiguration pc : g.getPlayers() ) {
				System.out.println( "Building player " + pc.getPlayerName() + " (" + pc.getClassName() + ")" );
				players.put( pc.getPlayerName(), playerFactory.buildPlayer( pc.getClassName() ) );
			}

			// setup the summary
			GameSummary summary = new GameSummary( players );

			// run the actual rounds
			for ( int i = 0 ; i < g.getRounds() ; i++ ) {
				hands = new HashMap<String, Hand>();
				for ( String name : players.keySet() ) {
					hands.put( name, players.get( name ).getHand() );
				}
				// record the results of the round
				RoundOutcome outcome = gameEngine.determineOutcome( players, hands );
				System.out.println( outcome );
				summary.addRoundOutcome( outcome );
			}

			// record the results of the game
			summaries.add( summary );
		}
		return summaries;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main( String[] args ) {

		Main main = new Main();
		
		// read command line options
		CommandLineOptionsReader cli = new CommandLineOptionsReader();
		if ( !cli.readCommandLine( args ) ) {
			// error reading options, so print usage and exit
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp( "whatever", cli.getOptions() );
			System.exit( 1 );
		}

		// read the config file
		File configFile = new File( cli.getConfigFilePath() );
		if ( !configFile.exists() ) {
			// file doesn't exist, so exit
			System.err.println( "For shame! File doesn't exist: " + configFile.getAbsolutePath() );
			System.exit( 1 );
		}

		GameConfiguration gc = JAXB.unmarshal( configFile, GameConfiguration.class );
		main.setGameConfiguration( gc );
		
		main.setGameEngine( new RulesEngine() );
		
		main.setPlayerFactory( new PlayerFactory() );
	}

	public GameConfiguration getGameConfiguration() {
		return gameConfiguration;
	}

	public void setGameConfiguration( GameConfiguration gameConfiguration ) {
		this.gameConfiguration = gameConfiguration;
	}

	public RulesEngine getGameEngine() {
		return gameEngine;
	}

	public void setGameEngine( RulesEngine gameEngine ) {
		this.gameEngine = gameEngine;
	}

	public PlayerFactory getPlayerFactory() {
		return playerFactory;
	}

	public void setPlayerFactory( PlayerFactory playerFactory ) {
		this.playerFactory = playerFactory;
	}
}
