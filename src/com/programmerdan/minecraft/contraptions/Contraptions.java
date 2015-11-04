package com.programmerdan.minecraft.contraptions;

import com.programmerdan.minecraft.contraptions.commands.CommandHandler;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * <p>The server side technology mod to end all server side technology mods.</p>
 * <p>See the readme and documentation for more</p>
 *
 * @author ProgrammerDan <programmerdan@gmail.com>
 * @since 1.0.0
 */
public class Contraptions extends JavaPlugin {
	private static CommandHandler commandHandler;
	private static Logger logger;
	private static JavaPlugin plugin;
	private static boolean enabled = true;
	private static boolean debug = false;
	private static ContraptionsConfiguration config;

	public static CommandHandler commandHandler() {
		return Contraptions.commandHandler;
	}

	public static Logger logger() {
		return Contraptions.logger;
	}

	public static JavaPlugin instance() {
		return Contraptions.plugin;
	}

	public static boolean isEnabled() {
		return Contraptions.enabled;
	}

	public static boolean isDebug() {
		return Contraptions.debug;
	}

	public static ContraptionsConfiguration config() {
		return Contraptions.config;
	}

	public static void setEnabled(boolean status) {
		Contraptions.enabled = status;
	}

	public static void setDebug(boolean debug) {
		Contraptions.debug = debug;
	}

	@Override
	public void onEnable() {
		// setting a couple of static fields so that they are available elsewhere
		Contraptions.logger = getLogger();
		Contraptions.plugin = this;
		Contraptions.commandHandler = new CommandHandler(this);

		/* TODO:
		 *   1. Load general Contraptions configuration
		 *   2. Spin up Contraption monitor in startup mode
		 *   3. Load Gadget specifications
		 *   4. Load saved Contraptions (DB access necessary)
		 *       1. Load Gadget locations, types, status
		 *       2. Load Contraption State (connections, activity status, state machine)
		 *       3. Add Contraption to Contraption monitor
		 *   5. Enter Contraption monitor active mode
		 */

	}
}
