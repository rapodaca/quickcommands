package me.gamehack;
import org.bukkit.plugin.java.JavaPlugin;


public class QuickCommands extends JavaPlugin{
	private Teleporter teleporter;
	private Checkpoint checkpoint;
	
	public QuickCommands() {
		teleporter = new Teleporter(this);
		checkpoint = new Checkpoint(this);
	}
	
	@Override
	public void onDisable() {

	}

	@Override
	public void onEnable() {		
		getCommand("tp").setExecutor(teleporter);
		getCommand("cp").setExecutor(checkpoint);
	}
}
