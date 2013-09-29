package ch.vorburger.leviator;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class World {

	Season season;
	
	// CopyOnWriteArrayList used here for in-game modification during main() loop
	
	List<Player> players = new CopyOnWriteArrayList<Player>();
	
	List<Place> places = new CopyOnWriteArrayList<Place>();
	
	boolean isRunning = true;

	Main ui;

	void playerInfo(Player player, String message) {
		ui.println(player.name() + ": " + message);
	}

	public World(Main main) {
		this.ui = main;
	}

	public Place getPlace(String name) throws IllegalArgumentException {
		return NamedUtil.get(places, name);
	}

	public Player getPlayer(String name) {
		return NamedUtil.get(players, name);
	}

}
