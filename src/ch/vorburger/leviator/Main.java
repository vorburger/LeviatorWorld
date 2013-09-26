package ch.vorburger.leviator;

import java.util.Map.Entry;

public class Main {

	CommandPrompt cmd = new CommandPrompt();
	
	World newWorld() {
		World world = new World(this);
		world.season = Season.Spring;
		Place plain = new Plain();
		plain.things.addThings(new Thing("Apple"), 50);
		plain.things.addThings(new Thing("dirt"),100);
		plain.things.addThings(new Thing("rose"), 20);
		plain.things.addThings(new Thing("medicalHerb"),10 );
		plain.things.addThings(new Thing("ash"), 10);
		
		world.places.add(plain);
		Forest forest = new Forest();

	
		forest.things.addThings(new Thing("birchWood"),20 );
		forest.things.addThings(new Thing("oakWood"),30 );
		forest.things.addThings(new Thing("mango"),50);
		forest.things.addThings(new Thing("stick"),25 );
		world.places.add(forest);
		world.players.add(new Player("Dév", world));
		world.players.add(new Player("Michael", world));
		return world;
	}

	void run() {
		World world = newWorld();
		println("It's " + world.season);
		long tour = 0;
		while (world.isRunning) {
			for (Player p : world.players) {
				println("Hello " + p.name + ", your energy bar is " + p.energyBar + ", and you're in " + p.inPlace.name() + " which has:");
				printThings(p.inPlace.things);
				if (!p.things.bag.isEmpty()) {
					print("; and you yourself on you have:");
					printThings(p.things);
				}
				cmd.waitForAndProcessCommand(p, Commands.available);
				if (tour % 3 == 0) {
					p.energyBar = p.energyBar-1;
					if( p.energyBar == 0) {
						p.things.bag.clear();
					}
				}
			}
			++tour;
		}
	}
	
	void printThings(Things things) {
		for (Entry<AbstractThing, Integer> entry : things.bag.entrySet()) {
			print(entry.getValue().toString());
			print("x ");
			print(entry.getKey().name());
			print("s ");
		}
	}

	void println(String string) {
		System.out.println(string);
	}

	void print(String string) {
		System.out.print(string);
	}

	public static void main(String[] args) {
		new Main().run();
	}

}