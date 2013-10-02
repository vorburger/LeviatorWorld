package ch.vorburger.leviator;

import ch.vorburger.worlds.Named;


public class Player implements Named {

	private String name;
	
	transient World world;

	Place inPlace;
	
	Things things = new Things();
	
	int energyBar = 20;
	
	void go(Place newPlace) {
		if (newPlace == inPlace)
			info("You're already in " + newPlace.name());
		else {
			inPlace = newPlace;
			info("OK, you're now in " + newPlace.name());
		}
	}
	
	void info(String message) {
		world.playerInfo(this, message);
	}

	public Player(String playerName, Place spawningPlace, World myWorld) {
		name = playerName;
		world = myWorld;
		inPlace = spawningPlace;
	}

	@Override
	public String name() {
		return name;
	}
	
}
