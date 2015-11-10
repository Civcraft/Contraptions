package com.programmerdan.minecraft.contraptions.commands;

import java.util.List;

import com.programmerdan.minecraft.contraptions.Contraptions;

import org.bukkit.command.CommandSender;

public abstract class AbstractCommand {

	protected final Contraptions plugin;
	protected final String name;

	public AbstractCommand(Contraptions instance, String commandName) {
		plugin = instance;
		name = commandName;
	}

	public abstract boolean onCommand(CommandSender sender, List<String> args);

	public boolean onConsoleCommand(CommandSender sender, List<String> args) {
		return onCommand(sender, args);
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		try {
			return plugin.getCommand("contraptions " + name).getDescription();
		} catch (NullPointerException e) {
			return null;
		}
	}

	public String getUsage() {
		try {
			return plugin.getCommand("contraptions " + name).getUsage();
		} catch (NullPointerException e) {
			return null;
		}
	}

	public String getPermission() {
		try {
			return plugin.getCommand("contraptions " + name).getPermission();
		} catch (NullPointerException e) {
			return null;
		}
	}

	public List<String> getAliases() {
		try {
			return plugin.getCommand("contraptions " + name).getAliases();
		} catch (NullPointerException e) {
			return null;
		}
	}
}
