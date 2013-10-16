package ch.vorburger.leviator;

import ch.vorburger.worlds.naming.Named;


public class Player implements Named {

	final private String name;
	
	// TODO use Container/Contained?
	final public World world;

	// TODO use Container/Contained?
	final Things things = new Things();

	public Place inPlace;
	
	int energyBar = 20;
	
	// TODO some kind of @Command annotation..
	public void go(Place newPlace) {
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
