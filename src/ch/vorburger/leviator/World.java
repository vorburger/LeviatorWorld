package ch.vorburger.leviator;

import java.util.ArrayList;
import java.util.List;

public class World {

	Season season;
	
	List<Player> players = new ArrayList<Player>();
	
	List<Place> places = new ArrayList<Place>();
	
	boolean isRunning = true;

	Main ui;

	void playerInfo(Player player, String message) {
		ui.println(player.name + ": " + message);
	}

	public World(Main main) {
		this.ui = main;
	}

	// TODO better share this code with Things instead of copy/paste 
	public Place getPlace(String name) throws IllegalArgumentException {
		for (Place place : places) {
			if (place.name().equalsIgnoreCase(name))
				return place;
		}
		String availablePlaces = "";
		for (Place place : places) {
			availablePlaces += place.name() + ", ";
		}
		throw new IllegalArgumentException("No such place: " + name + ", only: " + availablePlaces);
	}

	// TODO re-use/share with above
	public Player getPlayer(String name) {
		for (Player player: players) {
			if (player.name.equalsIgnoreCase(name))
				return player;
		}
		String available = "";
		for (Player player : players) {
			available += player.name + ", ";
		}
		throw new IllegalArgumentException("No such player: " + name + ", only: " + available);
	}

}
