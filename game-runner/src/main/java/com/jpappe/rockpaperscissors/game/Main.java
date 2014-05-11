
package com.jpappe.rockpaperscissors.game;

import java.io.File;

import javax.xml.bind.JAXB;

import org.apache.commons.cli.HelpFormatter;

import com.jpappe.rockpaperscissors.game.config.GameConfiguration;

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


	public static void main( String[] args ) {

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
		System.out.println( "Configuration includes " + gc.getGames().size() + " games." );
	}
}
