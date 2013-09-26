package ch.vorburger.leviator;


public class Player {

	String name;
	
	World world;

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

	Player(String playerName, World myWorld) {
		name = playerName;
		world = myWorld;
		inPlace = world.places.get(0);
	}
	
}
