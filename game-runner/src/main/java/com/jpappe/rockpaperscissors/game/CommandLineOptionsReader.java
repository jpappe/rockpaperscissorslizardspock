package com.jpappe.rockpaperscissors.game;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CommandLineOptionsReader {

	private final Options options;
	private String configFilePath;

	public CommandLineOptionsReader() {
		options = new Options();
		@SuppressWarnings( "static-access" )
		Option config = OptionBuilder.withLongOpt( "config" ).isRequired().hasArg().withArgName( "PATH" ).withDescription( "Path to configuration file" ).create( "c" );
		options.addOption( config );
	}

	public boolean readCommandLine( String [] args ) {
		CommandLineParser parser = new BasicParser();
		try {
			CommandLine cmd = parser.parse( options, args );
			configFilePath = cmd.getOptionValue( "c" );
		}
		catch ( ParseException e ) {
			System.err.println( e.getMessage() );
			return false;
		}

		return true;
	}

	public String getConfigFilePath() {
		return configFilePath;
	}

	public Options getOptions() {
		return options;
	}

}
