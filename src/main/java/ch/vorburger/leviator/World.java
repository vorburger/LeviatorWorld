package ch.vorburger.leviator;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import ch.vorburger.worlds.UI;

public class World {

	public Season season;
	
	// CopyOnWriteArrayList used here for in-game modification during main() loop
	
	List<Player> players = new CopyOnWriteArrayList<Player>();
	
	List<Place> places = new CopyOnWriteArrayList<Place>();

	// TODO These two don't really belong here.. factor out elsewhere, later
	transient boolean isRunning = true;
	transient UI ui;

	void playerInfo(Player player, String message) {
		if (ui == null)
			throw new IllegalStateException("UI not set");
		ui.println(player.name() + ": " + message);
	}

	public void setUI(UI ui) {
		this.ui = ui;
	}
	
	public Place getPlace(String name) throws IllegalArgumentException {
		return NamedUtil.get(places, name);
	}

	public Player getPlayer(String name) {
		return NamedUtil.get(players, name);
	}

	public List<Player> getPlayers() {
		return players;
	}

	public List<Place> getPlaces() {
		return places;
	}

}
