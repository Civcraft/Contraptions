package com.programmerdan.minecraft.contraptions;

import com.programmerdan.minecraft.contraptions.commands.CommandHandler;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import vg.civcraft.mc.civmodcore.ACivMod;

/**
 * <p>The server side technology mod to end all server side technology mods.</p>
 * <p>See the readme and documentation for more</p>
 *
 * @author ProgrammerDan <programmerdan@gmail.com>
 * @since 1.0.0
 */
public class Contraptions extends ACivMod {
	private static CommandHandler commandHandler;
	private static Logger logger;
	private static JavaPlugin plugin;
	private static ContraptionsConfiguration config;

	public static CommandHandler commandHandler() {
		return Contraptions.commandHandler;
	}
	
	protected String getPluginName() {
	    return "Contraptions";
	}

	public static Logger logger() {
		return Contraptions.logger;
	}

	public static JavaPlugin instance() {
		return Contraptions.plugin;
	}

	public static boolean isDebug() {
		return Contraptions.config.isDebug();
	}

	public static ContraptionsConfiguration config() {
		return Contraptions.config;
	}

	@Override
	public void onEnable() {
		super.onEnable();
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
