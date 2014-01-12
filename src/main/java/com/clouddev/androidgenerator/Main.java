package com.clouddev.androidgenerator;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

import com.clouddev.androidgenerator.model.Database;
import com.clouddev.androidgenerator.model.Root;
import com.clouddev.androidgenerator.model.Table;
import com.clouddev.androidgenerator.reader.ModelReader;
import com.clouddev.androidgenerator.writer.BeanWriter;
import com.clouddev.androidgenerator.writer.DatabaseHelperWriter;
import com.clouddev.androidgenerator.writer.ParentBeanWriter;

public class Main {
	
	private static final String OPTION_INPUT = "i";
	private static final String OPTION_OUTPUT = "o";
	private static final String OPTION_HELP = "h";

	private static String inputFile = "./src/main/resources/Model.xml";
	private static String outputFolder = "./target/generated-sources";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Options options = createOptions();

			CommandLineParser parser = new BasicParser();
			CommandLine cmd = parser.parse(options, args);
			
			if (cmd.hasOption(OPTION_HELP)) {
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp("ang", options, true);
				System.exit(0);
			}
			
			if (cmd.hasOption(OPTION_INPUT)) {
				inputFile = cmd.getOptionValue(OPTION_INPUT);
			}
			
			if (cmd.hasOption(OPTION_OUTPUT)) {
				outputFolder = cmd.getOptionValue(OPTION_OUTPUT);
			}
			
			System.out.println("== ANDROID DATABASE GENERATOR ==");
			System.out.println("====== V1.0 by The Copycat =====");
			
			System.out.println("========= ARGUMENTS ============");
			System.out.println("Input XML File : "+inputFile);
			System.out.println("Output Folder  : "+outputFolder);
			System.out.println("================================");
			
			
			System.out.println("=== BEGIN PARSING INPUT FILE ===");
			Root root = ModelReader.readFile(inputFile);
			System.out.println("=== END PARSING INPUT FILE =====");
			
			System.out.println("=== BEGIN WRITING SOURCES ======");
			for (Database database : root.getDatabases()) {
				writeDatabase(database, outputFolder);
			}
			System.out.println("=== END WRITING SOURCES ========");
			
		} catch (Exception e) {
			System.err.println("=== ERROR ======================");
			e.printStackTrace();
			System.exit(-1);
		}
		
	}
	
	@SuppressWarnings("static-access")
	private static Options createOptions() {
		Options options = new Options();
		
		//Help Message
		Option help = new Option( OPTION_HELP, "print this message" );
		options.addOption(help);
		
		//Input Option
		Option input = OptionBuilder.withArgName("input xml")
				.hasArg()
                .withDescription("use given xml file for input")
                .create(OPTION_INPUT);
		options.addOption(input);
		
		//Output path
		Option output = OptionBuilder.withArgName("output folder")
				.hasArg()
				.withDescription("output folder for data generation")
				.create(OPTION_OUTPUT);
		options.addOption(output);
				
		return options;
	}
	
	private static boolean writeDatabase(Database database,String outputFolder) {
		try {
			System.out.println("Processing database : "+database.getName()+"; Package : "+database.getPackageName());
			
			System.out.println("   - Doing Helper file for database");
			//Creating Database Helper file
			DatabaseHelperWriter databaseWriter = new DatabaseHelperWriter(database);
			databaseWriter.write(outputFolder);
			System.out.println("   - Doing Bean files for database");
			//Creating Beans Files
			for (Table table : database.getTables()) {
				System.out.println("   - Doing bean file for table : "+table.getName());
				BeanWriter beanWriter = new BeanWriter(database, table);
				beanWriter.write(outputFolder);
			}
			
			System.out.println("   - Doing Parent Bean File");
			//Creating Parent Bean File
			ParentBeanWriter parentBeanWriter = new ParentBeanWriter(database);
			parentBeanWriter.write(outputFolder);
			
			System.out.println("End of database processing");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
