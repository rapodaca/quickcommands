package me.gamehack;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Checkpoint implements CommandExecutor {
	private Map<Player, Location> locations;
	
	public Checkpoint(QuickCommands plugin) {
		locations = new HashMap<Player, Location>();
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return false;
		}
		
		Player player = (Player) sender;
		
		if (args.length == 0) {
			Location location = locations.get(player);
			
			if (location == null) {
				player.sendMessage(ChatColor.RED + "You haven't set a checkpoint - use /cp set");
				
				return true;
			} else {
				player.teleport(location);
				player.sendMessage(ChatColor.BLUE + "You were teleported to your checkpoint.");
				
				return true;
			}
		} else if (args.length == 1 && "set".equals(args[0])) {
			locations.put(player, player.getLocation());
			player.sendMessage(ChatColor.BLUE + "Your checkpoint was changed.");
			
			return true;
		}
		
		return false;
	}

}
