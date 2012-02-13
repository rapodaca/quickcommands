package me.gamehack;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Teleporter implements CommandExecutor {
	private QuickCommands plugin;
	
	public Teleporter(QuickCommands plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {		
		if (!(sender instanceof Player)) {
			return false;
		}

		Player teleporter = (Player) sender;

		if (!teleporter.isOp()) {
			teleporter.sendMessage(ChatColor.RED
					+ "Only OP can quick teleport. :(");

			return true;
		}
		
		if (args.length != 2) {
			return false;
		}
		
		List<Player> players = plugin.getServer().getWorlds().get(0).getPlayers();
		Player target = null;
		Player source = null;
		
		for (Player test : players) {
			String name = test.getName().toLowerCase();
			
			if (name.startsWith(args[0])) {
				target = test;
			} else if (name.startsWith(args[1])) {
				source = test;
			}
		}
		
		if (target == null) {
			teleporter.sendMessage(ChatColor.RED + "Can't find player " + args[0]);
			
			return true;
		}
		
		if (source == null) {
			teleporter.sendMessage(ChatColor.RED + "Can't find player " + args[1]);
			
			return true;
		}
		
		Bukkit.getServer().broadcastMessage(ChatColor.BLUE + source.getDisplayName() + " was teleported to " + target.getDisplayName() + " by " + teleporter.getDisplayName());		
//		Bukkit.getServer().broadcastMessage(ChatColor.BLUE + "I teleport " + target.getDisplayName() + " to " + source.getDisplayName());
		
		target.teleport(source);
		
		return true;
	}

}
